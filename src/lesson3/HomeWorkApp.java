package lesson3;

import java.util.Arrays;
import java.util.Random;

public class HomeWorkApp {
    public static void main(String[] args) {
        int[] bitArray = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        revertBitArray(bitArray);
        System.out.println(Arrays.toString(bitArray));

        System.out.println(Arrays.toString(getArrayFromOneToHundred()));

        printArrayForThirdTask();

        printSquareArrayWithDiagonals(5);

        System.out.println(Arrays.toString(getArrayWithIntegerValues(10, -7)));

        checkMinMaxInRandomArray(10, 51);

        int[] numbers = new int[]{5, 1, 1, 3, -3, 5};
        System.out.println(isExistsEqualsLeftRightSums(numbers));

        numbers = new int[]{3, 5, 6, 1, 4, 10};
        shiftElementsOfArray(numbers, 3);
        System.out.println(Arrays.toString(numbers));
    }

    /*
        1. Задать целочисленный массив, состоящий из элементов 0 и 1.
        Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
     */
    private static void revertBitArray(int[] bitArray) {
        for (int i = 0; i < bitArray.length; i++) {
            if (bitArray[i] == 0) {
                bitArray[i] = 1;
            } else {
                bitArray[i] = 0;
            }
        }
    }

    /*
        2. Задать пустой целочисленный массив длиной 100.
        С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;
     */
    private static int[] getArrayFromOneToHundred() {
        int[] result = new int[100];
        for (int i = 0; i < result.length; i++) {
            result[i] = i + 1;
        }
        return result;
    }

    /*
        3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
     */
    private static void printArrayForThirdTask() {
        int[] numbers = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 6) {
                numbers[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(numbers));
    }

    /*
        4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
         и с помощью цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей, если обе сложно).
         Определить элементы одной из диагоналей можно по следующему принципу:
         индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];
     */
    private static void printSquareArrayWithDiagonals(int size) {
        int[][] squareArray = new int[size][size];
        int secondPosition;
        for (int i = 0; i < size; i++) {
            squareArray[i][i] = 1;
            secondPosition = size - i - 1;
            squareArray[i][secondPosition] = 1;
            for (int j = 0; j < size; j++) {
                System.out.printf("%d ", squareArray[i][j]);
            }
            System.out.println();
        }

    }

    /*
        5. Написать метод, принимающий на вход два аргумента: len и initialValue,
        и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue;
     */
    private static int[] getArrayWithIntegerValues(int len, int initialValue) {
        int[] result = new int[len];
        for (int i = 0; i < result.length; i++) {
            result[i] = initialValue;
        }
        return result;
    }

    /*
        6. * Задать одномерный массив и найти в нем минимальный и максимальный элементы ;
     */
    private static void checkMinMaxInRandomArray(int size, int bound) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(bound);
        }
        int min = array[0];
        int max = min;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            } else if (array[i] < min) {
                min = array[i];
            }
        }
        System.out.println("В массиве:");
        System.out.println(Arrays.toString(array));
        System.out.printf("Минимум: %d, Максимум: %d %n", min, max);
    }

    /*
        7. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
         если в массиве есть место, в котором сумма левой и правой части массива равны.
     */
    private static boolean isExistsEqualsLeftRightSums(int[] numbers) {
        boolean result = false;
        // в массиве длинной меньше 2 таких позиций нет
        if (numbers.length > 1) {
            // рассчитать сумму элементов, исключая последний
            int sumLeft = 0;
            for (int i = 0; i < numbers.length - 1; i++) {
                sumLeft += numbers[i];
            }
            // начиная с предпоследней позиции, проверять сумму левой и правой частей,
            // сдвигаясь затем к началу массива
            int sumRight = numbers[numbers.length - 1];
            int position = numbers.length - 2;
            while (position > -1) {
                if (sumLeft == sumRight) {
                    result = true;
                    break;
                }
                sumLeft -= numbers[position];
                sumRight += numbers[position];
                position--;
            }
        }
        return result;
    }

    /*
        8. *** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
         при этом метод должен сместить все элементы массива на n позиций. Элементы смещаются циклично.
         Для усложнения задачи нельзя пользоваться вспомогательными массивами.
         Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
         При каком n в какую сторону сдвиг можете выбирать сами.
     */
    private static void shiftElementsOfArray(int[] array, int step) {
        // сдвигать значения на одну позицию влево или вправо, изменяя значение остатка шагов (+ или -)
        int nextMove;
        if (step < 0) {
            nextMove = array[0];
            for (int i = array.length - 1; i > -1; i--) {
                nextMove = changeNumberInPosition(array, i, nextMove);
            }
            step++;
        } else if (step > 0) {
            nextMove = array[array.length - 1];
            for (int i = 0; i < array.length; i++) {
                nextMove = changeNumberInPosition(array, i, nextMove);
            }
            step--;
        }
        if (step != 0) {
            // вызывать смещение на один шаг рекурсивно до достижения нулевого значения остатка шагов
            shiftElementsOfArray(array, step);
        }
    }

    // метод рокировки значения позиции массива и вспомогательной переменной
    private static int changeNumberInPosition(int[] array, int position, int newValue) {
        int temp = array[position];
        array[position] = newValue;
        return temp;
    }
}