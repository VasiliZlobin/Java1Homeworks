package lesson7;

public class Plate {
    private int foodQuantity;

    public Plate(int foodQuantity) {
        this.foodQuantity = Math.max(foodQuantity, 0);
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void decreaseFood(int foodQuantity) {
        this.foodQuantity -= Math.min(this.foodQuantity, foodQuantity);
    }

    public void addFood(int foodQuantity) {
        if (foodQuantity > 0) {
            this.foodQuantity += foodQuantity;
        }
    }

    public void showInfo() {
        System.out.printf("Еды в миске: %d.%n", foodQuantity);
    }
}