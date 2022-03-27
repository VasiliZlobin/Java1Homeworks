package lesson6;

public class Cat extends Animal {
    private static final int MAX_RUN = 200;
    private static int count;
    private CatBreeds breed;

    public static int getCount() {
        return count;
    }

    public Cat(String name, Colors color, CatBreeds breed, int age) {
        super(name, color, age);
        this.breed = breed;
        count++;
    }

    public CatBreeds getBreed() {
        return breed;
    }

    public void setBreed(CatBreeds breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Кот " + super.toString() + " Порода: " + breed + ".";
    }

    @Override
    public void swim(int distance) {
        System.out.printf("Кот %s не умеет плавать.%n", super.getName());
    }

    @Override
    public void run(int distance) {
        if (distance > 0) {
            System.out.print("Кот ");
            super.run(Math.min(MAX_RUN, distance));
        }
    }

}
