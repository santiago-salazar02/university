package edu.university;

import edu.university.management.Class;
import edu.university.management.University;
import edu.university.people.Student;
import edu.university.people.teacher.FullTimeTeacher;
import edu.university.people.teacher.PartTimeTeacher;
import edu.university.people.teacher.Teacher;

public class Main {
    private final static Teacher[] teachersDummy = {
            new FullTimeTeacher("Juan", 5),
            new FullTimeTeacher("Jose",3),
            new PartTimeTeacher("Andrew",20),
            new PartTimeTeacher("Richard",30)
    };
    private final static Student[] studentsDummy = {
            new Student("A",1),
            new Student("B",2),
            new Student("C",3),
            new Student("D",4),
            new Student("E",5),
            new Student("F",6)
    };

    public static void main(String[] args) {
        emulateTeachers();
        emulateStudents();
        emulateClass();
        Console.askActions();
    }

    private static void emulateTeachers(){

        for(Teacher teacher: teachersDummy){
            University.addTeacher(teacher);
        }
    }

    private static void emulateStudents(){
        for(Student student: studentsDummy){
            University.addStudent(student);
        }
    }

    private static void emulateClass(){
        String[] classesName = {
                "Class A",
                "Class B",
                "Class C",
                "Class D"
        };
        for(int i=0;i<teachersDummy.length;i++){
            Class aclass = new Class(classesName[i],i,teachersDummy[i]);
            aclass.addStudent(studentsDummy[i]);
            aclass.addStudent(studentsDummy[i+1]);
            University.addClass(aclass);
        }
    }

}