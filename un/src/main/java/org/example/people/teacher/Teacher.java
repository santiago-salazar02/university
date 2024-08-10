package org.example.people.teacher;

import org.example.people.Person;

public class Teacher extends Person {

    private double salary;
    private static double baseSalary;

    public Teacher(String name) {
        super(name);
    }

    public void updateSalary(){

    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static double getBaseSalary() {
        return Teacher.baseSalary;
    }

    public static void setBaseSalary(double baseSalary) {
        Teacher.baseSalary = baseSalary;
    }

}
