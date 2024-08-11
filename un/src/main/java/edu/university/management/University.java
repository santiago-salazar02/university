package edu.university.management;

import edu.university.helpers.Format;
import edu.university.people.Student;
import edu.university.people.teacher.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class University {
    private static List<Teacher> teachers = new ArrayList<Teacher>();
    private static List<Student> students = new ArrayList<Student>();
    private static List<Class> classes = new ArrayList<Class>();

    public static List<Teacher> getTeachers() {
        return University.teachers;
    }

    // Teachers
    public static String getTeachersInfo(){
        StringBuilder teacherData = new StringBuilder();
        teacherData.append("Teachers:\n");
        for(Teacher teacher: University.teachers){
            teacherData.append(teacher.toString()).append("\n");
        }
        return teacherData.toString();
    }

    public static int searchByTeacherName(String name){
        for(int i = 0; i < University.teachers.size();i++){
            if(Objects.equals(University.teachers.get(i).getName(), Format.formatName(name))){
                return i;
            }
        }
        return -1;
    }

    public static void addTeacher(Teacher teacher){
        University.teachers.add(teacher);
    }

    public static void deleteTeacher(Teacher teacher){
        University.teachers.remove(teacher);
        for(Class aclass: classes){
            if(Objects.equals(teacher.getName(),aclass.getTeacher().getName())){
                aclass.setTeacher(null);
            }
        }
    }

    // Students
    public static String getStudentsInfo(){
        StringBuilder studentData = new StringBuilder();
        studentData.append("Students:\n");
        for(Student student: University.students){
            studentData.append(student.toString()).append("\n");
        }
        return studentData.toString();
    }

    public static int searchByStudentId(long id){
        for(int i = 0; i < University.students.size(); i++){
            if(University.students.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public static void addStudent(Student student){
        University.students.add(student);
    }

    public static void deleteStudent(Student student){
        University.students.remove(student);
        for(Class aclass: classes){
            if(aclass.isStudentInClass(student.getId())){
                aclass.deleteStudent(student);
            }
        }
    }

    public static String getStudentClasses(long id){
        StringBuilder studentData = new StringBuilder();
        studentData.append("Classes:\n");
        for(Class aclass: classes){
            if(aclass.isStudentInClass(id)){
                studentData.append(aclass.toString());
            }
        }
        return studentData.toString();
    }

    // Classes
    public static String getClassesInfo(){
        StringBuilder classesData = new StringBuilder();
        classesData.append("Classes:\n");
        for(Class aclass: University.classes){
            classesData.append(aclass.toString()).append("\n");
        }
        return classesData.toString();
    }

    public static void addClass(Class aclass){
        University.classes.add(aclass);
    }

    public static void deleteClass(Class aclass){
        University.classes.remove(aclass);
    }


    public static String getUniversityInfo(){

        return University.getTeachersInfo()+
                University.getStudentsInfo()+
                University.getClassesInfo();
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
