package edu.university.people.teacher;

public class PartTimeTeacher extends Teacher {

    private int workedHours;

    public PartTimeTeacher(String name,int workedHours) {
        super(name);
        this.workedHours = workedHours;
        updateSalary();
    }

    @Override
    public void updateSalary(){
        double salary = this.workedHours* getBaseSalary();
        setSalary(salary);
    }

    public int getWorkedHours() {
        return this.workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }
}
