import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Homework_3 {

    public static Scanner scanner = new Scanner(System.in);

    /**
     * Задание 1
     * Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3
     * попытки угадать это число. При каждой попытке компьютер должен сообщить, больше ли
     * указанное пользователем число, чем загаданное, или меньше. После победы или проигрыша
     * выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
     */
    public static void findTheNumber0to9(){
        int[] enteredNums = new int[3];
        int n = 1;
        int num;
        boolean isPlayerWin = false;
        Random rand = new Random();
        while(n == 1 ) {
            int random = rand.nextInt(10);
            System.out.println("Компьютер загадал число!");
            for(int i = 1; i <= 3; i++){
                System.out.println("Попытка №" + i + " (Всего 3 попытки)");
                System.out.print("Для отгадки этого числа введите число от 0 до 9. ");
                if(i > 1){
                    System.out.print("(Числа, которые вы уже вводили: ");
                    for(int k = 0; k < i-1; k++){
                        System.out.print(enteredNums[k] + " ");
                    }
                    System.out.print(") ");
                }
                num = scanner.nextInt();
                enteredNums[i-1] = num;
                if(num >= 0 && num <= 9){
                    if(num > random)
                        System.out.println("Введенное вами число больше загаданного");
                    else if(num < random)
                        System.out.println("Введенное вами число меньше загаданного");
                    else {
                        System.out.printf("Поздравляю! Вы отгадали число! Было загаданно число: %d ",random);
                        isPlayerWin = true;
                        break;
                    }
                }
            }
            if(!isPlayerWin)
                System.out.println("К сожалению, вы исчерпали количество попыток но так и не отгадали число. " +
                        "Загаданное число было " + random);
            System.out.println("Хотите сыграть еще раз? Введите 1 для повторной игры и 0 - для выхода из игры.");
            n = scanner.nextInt();
        }
    }

    /**
     * Задание 2
     * Создать массив из слов
     * String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
     * "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
     * "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
     * При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
     * сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь. Если
     * слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
     * apple – загаданное
     * apricot - ответ игрока
     * ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
     * Для сравнения двух слов посимвольно можно пользоваться:
     * String str = "apple";
     * char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
     * Играем до тех пор, пока игрок не отгадает слово.
     * Используем только маленькие буквы.
     */

    public static void findTheWord(){
        Random rand = new Random();
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String userAnswer = "";
        String stopWord = "";
        boolean isPlayerWon = false;
        int min;
        char[] xWordCharArr;
        char[] userAnswerCharArr;
        char[] foundChars;
        int countOfFoundChars = 0;
        int maxLength = 0;

        for(String word : words)
            if(word.length() > maxLength)
                maxLength = word.length();
        foundChars = new char[maxLength+2];
        Arrays.fill(foundChars,'#');

        while(!stopWord.equals("stop") && !isPlayerWon){
            String xWord = words[rand.nextInt(words.length)];
            while(true) {
                System.out.println("Список слов:");
                for (int i = 0; i < words.length; i++) {
                    System.out.print(words[i]);
                    if (i > 0 && i % 9 == 0)
                        System.out.print(",\n");
                    else if (i != words.length - 1)
                        System.out.print(", ");
                    else
                        System.out.print("\n");
                }
                System.out.print("Отгадайте слово из списка. ");
                if(countOfFoundChars > 0) {
                    System.out.print("(Отгаданные буквы: ");
                    for(char c : foundChars)
                        System.out.print(c + " ");
                    System.out.println(") ");
                }
                System.out.print("Введите свой ответ здесь: ");
                userAnswer = scanner.next().toLowerCase();
                min = Math.min(xWord.length(), userAnswer.length());
                if (xWord.toLowerCase().equals(userAnswer)) {
                    System.out.println("Поздравляю! Вы выиграли!");
                    isPlayerWon = true;
                    break;
                } else {
                    System.out.println("Вы не отгадали! Попробуйте еще раз.");
                    System.out.print("\n");
                }
                xWordCharArr = xWord.toCharArray();
                userAnswerCharArr = userAnswer.toCharArray();
                for(int i = 0; i < min; i++)
                    if(xWordCharArr[i] == userAnswerCharArr[i]) {
                        foundChars[i] = xWordCharArr[i];
                        countOfFoundChars++;
                    }
            }
            if(!isPlayerWon) {
                System.out.println("Для выхода введите stop. Для продолжения - любой символ");
                stopWord = scanner.next();
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Для запуска игры в отгадывании числа введите 1");
        System.out.println("Для запуска игры в отгадывании слова введите 2");
        switch (scanner.nextInt()) {
            case 1: findTheNumber0to9();
            break;
            case 2: findTheWord();
            break;
        }


    }

}
