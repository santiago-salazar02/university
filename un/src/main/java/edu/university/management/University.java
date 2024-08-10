package edu.university.management;

import edu.university.people.Student;
import edu.university.people.teacher.Teacher;

import java.util.ArrayList;
import java.util.List;

public class University {
    private static List<Teacher> teachers = new ArrayList<Teacher>();
    private static List<Student> students = new ArrayList<Student>();
    private static List<Class> classes = new ArrayList<Class>();

    public static List<Teacher> getTeachers() {
        return University.teachers;
    }

    public static void setTeachers(List<Teacher> teachers) {
        University.teachers = teachers;
    }

    public static List<Student> getStudents() {
        return University.students;
    }

    public static void setStudents(List<Student> students) {
        University.students = students;
    }

    public static List<Class> getClasses() {
        return University.classes;
    }

    public static void setClasses(List<Class> classes) {
        University.classes = classes;
    }
}
