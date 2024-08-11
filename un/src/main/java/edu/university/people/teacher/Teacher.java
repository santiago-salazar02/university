package edu.university.people.teacher;

import edu.university.people.Person;

public class Teacher extends Person {

    private double salary;
    private static double baseSalary = 100;
    private static int maxLaborHours = 40;

    public Teacher(String name) {
        super(name);
    }

    public void updateSalary(){

    }

    public void updateWorkedHours(){

    }

    @Override
    public String toString(){
        return "Name: " + this.getName() + " Salary: " + this.salary;
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

    public static int getMaxLaborHours() {
        return maxLaborHours;
    }

    public static void setMaxLaborHours(int maxLaborHours) {
        Teacher.maxLaborHours = maxLaborHours;
    }
}
