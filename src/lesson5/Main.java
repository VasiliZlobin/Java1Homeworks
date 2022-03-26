package lesson5;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = getArrayEmployee();
        for (Employee employee : employees) {
            if (employee.getAge() > 40) {
                employee.showInfo();
                System.out.println();
            }
        }
    }

    private static Employee[] getArrayEmployee() {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Иванов Петр Викторович", 40, "ceo@corporate.com",
                Positions.CEO, "89123456788", 270000);
        employees[1] = new Employee("Агафонова Ирина Александровна", 25, "hrm@corporate.com",
                Positions.HRM, "89123456712", 70000);
        employees[2] = new Employee("Петрова Дарья Валерьевна", 41, "acc@corporate.com",
                Positions.Accountant, "89123456716", 100000);
        employees[3] = new Employee("Панов Иван Петрович", 27, "man@corporate.com",
                Positions.Manager, "89123456718", 60000);
        employees[4] = new Employee("Сумкин Николай Иванович", 50, "",
                Positions.Driver, "89123456787", 55000);
        return employees;
    }
}
