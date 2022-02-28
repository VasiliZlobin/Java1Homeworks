/*
   1. Создать пустой проект в IntelliJ IDEA, создать класс HomeWorkApp, и прописать в нем метод main().
 */
package lesson1;

public class HomeWorkApp {
    public static void main(String[] args) {
        /*
            6. Методы из пунктов 2-5 вызовите из метода main() и посмотрите корректно ли они работают.
         */
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
    }

    /*
        2. Создайте метод printThreeWords(),
           который при вызове должен отпечатать в столбец три слова: Orange, Banana, Apple.
     */
    private static void printThreeWords() {
        String[] words = {"Orange", "Banana", "Apple"};
        for (String word : words) {
            System.out.println(word);
        }
    }

    /*
        3. Создайте метод checkSumSign(), в теле которого объявите две int переменные a и b,
           и инициализируйте их любыми значениями, которыми захотите.
           Далее метод должен просуммировать эти переменные, и если их сумма больше или равна 0,
           то вывести в консоль сообщение “Сумма положительная”,
           в противном случае - “Сумма отрицательная”;
     */
    private static void checkSumSign() {
        int a = -7, b = 9;
        System.out.println("Для a = " + a + ", b = " + b);
        if (a + b < 0) {
            System.out.println("Сумма отрицательная");
        } else {
            System.out.println("Сумма положительная");
        }
    }

    /*
        4. Создайте метод printColor() в теле которого задайте int переменную value
           и инициализируйте ее любым значением. Если value меньше 0 (0 включительно),
           то в консоль метод должен вывести сообщение “Красный”,
           если лежит в пределах от 0 (0 исключительно) до 100 (100 включительно),
           то “Желтый”, если больше 100 (100 исключительно) - “Зеленый”;
     */
    private static void printColor() {
        int value = 35;
        System.out.println("value = " + value);
        if (value > 100) {
            System.out.println("Зеленый");
        } else if (value <= 0) {
            System.out.println("Красный");
        } else {
            System.out.println("Желтый");
        }
    }

    /*
        5. Создайте метод compareNumbers(), в теле которого объявите две int переменные a и b,
           и инициализируйте их любыми значениями, которыми захотите. Если a больше или равно b,
           то необходимо вывести в консоль сообщение “a >= b”,
           в противном случае “a < b”;
     */
    private static void compareNumbers() {
        int a = 7, b = -9;
        System.out.println("Для a = " + a + ", b = " + b);
        if (a < b) {
            System.out.println("a < b");
        } else {
            System.out.println("a >= b");
        }
    }
}
