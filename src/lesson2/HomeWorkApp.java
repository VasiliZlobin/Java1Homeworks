package lesson2;

import java.util.Scanner;

public class HomeWorkApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number1 = InputValues.getIntegerNumber(scanner,-1000, 1000);
        int number2 = InputValues.getIntegerNumber(scanner, -1000, 1000);
        System.out.printf("%d + %d между 10 и 20: %b\n", number1, number2, checkSumBetweenTenTwenty(number1, number2));
        printSignOfInteger(number1);
        System.out.printf("%d is negative: %b\n", number2, isNegativeNumber(number2));
        printStringInCycle("Сообщение в цикле", 5);
        System.out.println("\nОпределим високосность года");
        int year = InputValues.getIntegerNumber(scanner,0, 10000);
        System.out.printf("Год %d %sявляется високосным", year, isLeapYear(year) ? "" : "не ");
        scanner.close();
    }

    /*
        1.  Написать метод, принимающий на вход два целых числа и проверяющий,
         что их сумма лежит в пределах от 10 до 20 (включительно),
         если да – вернуть true, в противном случае – false.
     */
    private static boolean checkSumBetweenTenTwenty(int firstNumber, int secondNumber) {
        int sum = firstNumber + secondNumber;
        boolean result = sum >= 10 && sum <= 20;
        return result;
    }

    /*
        2. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
         положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
     */
    private static void printSignOfInteger(int number) {
        System.out.printf("%d - %s число\n", number, number < 0 ? "отрицательное" : "положительное");
    }

    /*
        3. Написать метод, которому в качестве параметра передается целое число.
        Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
     */
    private static boolean isNegativeNumber(int number) {
        return number < 0;
    }

    /*
        4. Написать метод, которому в качестве аргументов передается строка и число,
         метод должен отпечатать в консоль указанную строку, указанное количество раз;
     */
    private static void printStringInCycle(String message, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(message);
        }
    }

    /*
        5. * Написать метод, который определяет, является ли год високосным,
         и возвращает boolean (високосный - true, не високосный - false).
         Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
     */

    private static boolean isLeapYear(int year) {
        return year % 400 == 0 || year % 4 == 0 && year % 100 != 0;
    }
}

class InputValues {
    protected static int getIntegerNumber(Scanner scanner, int min, int max) {
        boolean isError = true;
        int result = Integer.MIN_VALUE;
        while (isError)
        {
            System.out.print("Введите число от " + min + " до " + max + ": ");
            result = scanner.nextInt();
            isError = result < min || result > max;
        }
        return result;
    }
}