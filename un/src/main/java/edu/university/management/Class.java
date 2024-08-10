package edu.university.management;

import edu.university.people.Student;
import edu.university.people.teacher.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Class {
    private String name;
    private int classroom;
    private List<Student> students;
    private Teacher teacher;

    public Class(String name, int classroom,Teacher teacher) {
        this.name = name;
        this.classroom = classroom;
        this.teacher = teacher;
        this.students = new ArrayList<Student>();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString(){
        String classData = "Class: " + this.name + " Classroom: " + this.classroom +
                "Teacher: " + this.teacher.getName() + "\n";
        StringBuilder studentData = new StringBuilder();
        for(Student student: students){
            studentData.append(student.toString()).append("\n");
        }
        return classData+studentData.toString();
    }

    public boolean isStudentInClass(long id){
        for(Student student:students){
            if(student.getId() == id){
                return true;
            }
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassroom() {
        return this.classroom;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
