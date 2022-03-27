package lesson6;

public class Dog extends Animal {
    private static final int MAX_RUN = 500;
    private static final int MAX_SWIM = 10;
    private static int count;
    private DogBreeds breed;

    public static int getCount() {
        return count;
    }

    public Dog(String name, Colors color, DogBreeds breed, int age) {
        super(name, color, age);
        this.breed = breed;
        count++;
    }

    public DogBreeds getBreed() {
        return breed;
    }

    public void setBreed(DogBreeds breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Собака " + super.toString() + " Порода: " + breed + ".";
    }

    @Override
    public void swim(int distance) {
        if (distance > 0) {
            System.out.print("Собака ");
            super.swim(Math.min(MAX_SWIM, distance));
        }
    }

    @Override
    public void run(int distance) {
        if (distance > 0) {
            System.out.print("Собака ");
            super.run(Math.min(MAX_RUN, distance));
        }
    }
}