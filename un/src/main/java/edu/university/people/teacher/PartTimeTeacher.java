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
        double salary = (double) 4*this.workedHours /Teacher.getMaxLaborHours()*Teacher.getBaseSalary();
        setSalary(salary);
    }

    @Override
    public void updateWorkedHours(){
        if(this.workedHours >= Teacher.getMaxLaborHours()){
            this.workedHours = Teacher.getMaxLaborHours()-1;
        }
    }

    public int getWorkedHours() {
        return this.workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }
}
