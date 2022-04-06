package lesson7;

public class Main {
    public static void main(String[] args) {
        Cat[] cats = getCatArray();
        Plate plate = new Plate(12);
        feedCats(cats, plate);
        cats[1].setHungry();
        int needFood = 0;
        for (Cat cat : cats) {
            if (!cat.isFullness()) {
                needFood += cat.getAppetite();
            }
        }
        plate.addFood(needFood - plate.getFoodQuantity());
        feedCats(cats, plate);
    }

    private static void feedCats(Cat[] cats, Plate plate) {
        plate.showInfo();
        for (Cat cat : cats) {
            if (!cat.isFullness()) {
                System.out.printf("К миске подходит кот %s -> ", cat.getName());
                cat.eat(plate);
            }
            cat.showInfo();
        }
        plate.showInfo();
    }

    private static Cat[] getCatArray() {
        Cat[] cats = new Cat[5];
        cats[0] = new Cat("Барсик", 5);
        cats[1] = new Cat("Винни", 2);
        cats[2] = new Cat("Рыжик", 6);
        cats[3] = new Cat("Мурзик", 4);
        cats[4] = new Cat("Дуся", 3);
        return cats;
    }
}