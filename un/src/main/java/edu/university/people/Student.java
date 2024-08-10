package edu.university.people;

public class Student extends Person{

    private static long uId;
    private long id;
    private int age;

    public Student(String name,int age) {
        super(name);
        this.age = age;
        this.id = Student.uId;
        Student.uId++;
    }

    public static long getuId() {
        return Student.uId;
    }

    public static void setuId(long uId) {
        Student.uId = uId;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
