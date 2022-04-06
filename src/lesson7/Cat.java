package lesson7;

public class Cat {
    private static final int MIN_APPETITE = 1;
    private String name;
    private int appetite;
    private boolean fullness;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = Math.max(appetite, MIN_APPETITE);
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean isFullness() {
        return fullness;
    }

    public void setHungry() {
        fullness = false;
    }

    public void showInfo() {
        String aboutFullness = fullness ? "Сытый" : "Голодный";
        System.out.printf("Кот %s. %s. Аппетит: %d.%n", name, aboutFullness, appetite);
    }

    public void eat(Plate plate) {
        if (plate.getFoodQuantity() >= appetite) {
            plate.decreaseFood(appetite);
            fullness = true;
        }
    }

}
