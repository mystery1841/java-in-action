package learning.oop.inherit;

import learning.oop.Employee;

public class Manager extends Employee {
    private double bonus;

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Manager(String n, double s, int year, int month, int day) {
        super(n, s, year, month, day);
    }

    public boolean equals(Object otherObject) {
        if (!super.equals(otherObject)) return false;
        Manager other = (Manager) otherObject;
        return bonus == other.bonus;
    }

    public String toString()
    {
        return super.toString()
                + "[bonus=" + bonus
                + "]";
    }

}
