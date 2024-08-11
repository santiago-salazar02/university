package edu.university.people.teacher;

public class FullTimeTeacher extends Teacher{

    private int expYears;

    public FullTimeTeacher(String name,int expYears) {
        super(name);
        this.expYears = expYears;
        updateSalary();
    }

    @Override
    public void updateSalary(){
        int expYears = this.expYears == 0 ? 1 : this.expYears;
        double salary = expYears*1.1*Teacher.getBaseSalary();
        setSalary(salary);
    }

    public int getExpYears() {
        return this.expYears;
    }

    public void setExpYears(int expYears) {
        this.expYears = expYears;
    }
}
