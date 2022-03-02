package lesson2;

import java.util.Scanner;

public class HomeWorkApp {
    public static void main(String[] args) {
        int number1 = InputValues.getIntegerNumber(-1000, 1000);
        int number2 = InputValues.getIntegerNumber(-1000, 1000);
        System.out.printf("%d + %d между 10 и 20: %b\n", number1, number2, CheckSumBetweenTenTwenty(number1, number2));
        PrintSignOfInteger(number1);
        System.out.printf("%d is negative: %b\n", number2, IsNegativeNumber(number2));
        PrintStringInCycle("Сообщение в цикле", 5);
        System.out.println("\nОпределим високосность года");
        int year = InputValues.getIntegerNumber(0, 10000);
        System.out.printf("Год %d %sявляется високосным", year, IsLeapYear(year) ? "" : "не ");
    }

    /*
        1.  Написать метод, принимающий на вход два целых числа и проверяющий,
         что их сумма лежит в пределах от 10 до 20 (включительно),
         если да – вернуть true, в противном случае – false.
     */
    private static boolean CheckSumBetweenTenTwenty(int firstNumber, int secondNumber) {
        int sum = firstNumber + secondNumber;
        boolean result = sum >= 10 && sum <= 20;
        return result;
    }

    /*
        2. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
         положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
     */
    private static void PrintSignOfInteger(int number) {
        System.out.printf("%d - %s число\n", number, number < 0 ? "отрицательное" : "положительное");
    }

    /*
        3. Написать метод, которому в качестве параметра передается целое число.
        Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
     */
    private static boolean IsNegativeNumber(int number) {
        return number < 0;
    }

    /*
        4. Написать метод, которому в качестве аргументов передается строка и число,
         метод должен отпечатать в консоль указанную строку, указанное количество раз;
     */
    private static void PrintStringInCycle(String message, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(message);
        }
    }

    /*
        5. * Написать метод, который определяет, является ли год високосным,
         и возвращает boolean (високосный - true, не високосный - false).
         Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
     */

    private static boolean IsLeapYear(int year) {
        return year % 400 == 0 || year % 4 == 0 && year % 100 != 0;
    }
}

class InputValues {
    protected static int getIntegerNumber(int min, int max) {
        boolean isError = true;
        int result = Integer.MIN_VALUE;
        while (isError)
        {
            System.out.print("Введите число от " + min + " до " + max + ": ");
            Scanner scanner = new Scanner(System.in);
            result = scanner.nextInt();
            isError = result < min || result > max;
        }
        return result;
    }
}