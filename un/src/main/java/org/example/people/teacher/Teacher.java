package org.example.people.teacher;

import org.example.people.Person;

public class Teacher extends Person {

    private double salary;
    private static double baseSalary;

    public Teacher(String name,double salary) {
        super(name);
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static double getBaseSalary() {
        return baseSalary;
    }

    public static void setBaseSalary(double baseSalary) {
        Teacher.baseSalary = baseSalary;
    }
    
}
