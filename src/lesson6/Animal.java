package lesson6;

public class Animal {
    private static int count;
    private String name;
    private Colors color;
    private int age;

    public static int getCount() {
        return count;
    }

    public Animal(String name, Colors color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = Math.max(age, 0);
    }

    @Override
    public String toString() {
        return name + ". Цвет: " + color + ". Возвраст: " + age + ".";
    }

    protected void swim(int distance) {
        if (distance > 0) {
            System.out.printf("%s проплыл %d метров.%n", name, distance);
        }
    }

    protected void run(int distance) {
        if (distance > 0) {
            System.out.printf("%s пробежал %d метров.%n", name, distance);
        }
    }
}
