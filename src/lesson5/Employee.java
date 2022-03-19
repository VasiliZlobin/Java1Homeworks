package lesson5;

enum Positions {
    CEO, HRM, Accountant, Manager, Driver
}

public class Employee {
    private static final int MIN_AGE = 18;
    private String fullName;
    private int age;
    private String email;
    private Positions position;
    private String telephone;
    private int salary;

    public Employee(String fullName, int age, String email, Positions position, String telephone, int salary) {
        this.fullName = fullName;
        this.age = Math.max(age, MIN_AGE);
        this.email = email;
        this.position = position;
        this.telephone = telephone;
        this.salary = Math.max(salary, 0);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = Math.max(age, MIN_AGE);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = Math.min(salary, 0);
    }

    public void showInfo() {
        System.out.printf("ФИО: %s%n", fullName);
        System.out.printf("Возраст: %d%n", age);
        System.out.printf("Должность: %s%n", position);
        if (salary == 0) {
            System.out.println("Стажируется");
        } else {
            System.out.printf("Зарплата: %s%n", salary);
        }
        if (email.length() > 0) {
            System.out.printf("Email: %s%n", email);
        }
        if (telephone.length() > 0) {
            System.out.printf("Телефон: %s%n", telephone);
        }
    }
}


