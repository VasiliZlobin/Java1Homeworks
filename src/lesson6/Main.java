package lesson6;

public class Main {
    public static void main(String[] args) {
        Cat[] cats = getCatArray();
        Dog[] dogs = getDogArray();
        int minCount = Math.min(cats.length, dogs.length);
        System.out.printf("Всего животных: %d%n", Animal.getCount());
        System.out.printf("Всего котов: %d%n", Cat.getCount());
        System.out.printf("Всего собак: %d%n%n", Dog.getCount());
        for (int i = 0; i < minCount; i++) {
            runActions(cats[i]);
            runActions(dogs[i]);
        }
    }

    private static Cat[] getCatArray() {
        Cat[] result = new Cat[3];
        result[0] = new Cat("Барсик", Colors.Spotted, CatBreeds.Bengal, 2);
        result[1] = new Cat("Рыжик", Colors.Ginger, CatBreeds.Other, 3);
        result[2] = new Cat("Симба", Colors.Striped, CatBreeds.Siamese, 1);
        return result;
    }

    private static Dog[] getDogArray() {
        Dog[] result = new Dog[2];
        result[0] = new Dog("Марлоу", Colors.White, DogBreeds.Terrier, 4);
        result[1] = new Dog("Атос", Colors.Black, DogBreeds.Hound, 2);
        return result;
    }

    private static void runActions(Animal animal) {
        System.out.println(animal);
        animal.run(600);
        animal.swim(100);
        System.out.println();
    }
}
