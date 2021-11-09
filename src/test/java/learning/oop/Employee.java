package learning.oop;

import java.time.LocalDate;
import java.util.Objects;


public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;
    private final StringBuilder evaluations;
    private static int nextId = 1;
    private int id = assignId();

    public Employee(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
        evaluations = new StringBuilder();
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public StringBuilder getEvaluations() {
        return evaluations;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public void giveGoldStar() {
        evaluations.append(LocalDate.now() + ": Gold star!\n");
    }

    public static int assignId() {
        int r = nextId;
        nextId++;
        return r;
    }

    public boolean equals(Object otherObject) {

        if (this == otherObject) return true;

        if (otherObject == null) return false;

        if (getClass() != otherObject.getClass())
            return false;

        Employee other = (Employee) otherObject;

        return Objects.equals(name, other.name)
                && salary == other.salary
                && Objects.equals(hireDay, other.hireDay);

    }

    public int hashCode() {
        return 7 * name.hashCode()
                + 11 * new Double(salary).hashCode()
                + 13 * hireDay.hashCode();
    }

    public String toString() {
        return getClass().getName()
                + "[name=" + name
                + ",salary=" + salary
                + ",hireDay=" + hireDay
                + "]";
    }


}
