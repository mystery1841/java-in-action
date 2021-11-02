package learning.oop.object;

import java.time.LocalDate;

public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;
    private final StringBuilder evaluations;
    private static int nextId = 1;

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
}
