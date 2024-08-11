package edu.university;

import edu.university.helpers.Format;
import edu.university.management.University;
import edu.university.people.Student;
import edu.university.people.teacher.FullTimeTeacher;
import edu.university.people.teacher.PartTimeTeacher;
import edu.university.people.teacher.Teacher;

import java.util.Objects;
import java.util.Scanner;

public class Console {

    private final static Scanner scanner = new Scanner(System.in);
    private static int option;

    private final static String noScenario = "Your input is incorrect, no scenario was found.";
    private final static String incorrectInt = "Your input is incorrect, only integers are accepted as valid inputs";
    private final static String incorrectLong = "Your input is incorrect, only longs are accepted as valid inputs";

    public static boolean isInteger(String number){
        try{
            int num = Integer.parseInt(number);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isDouble(String number){
        try{
            double num = Double.parseDouble(number);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isLong(String number){
        try{
            long num = Long.parseLong(number);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    private static int getInt(String question){
        System.out.println(question);
        String intScanner = scanner.nextLine();
        if(!isInteger(intScanner)){
            System.out.println(incorrectInt);
            getInt(question);
        }
        return Integer.parseInt(intScanner);
    }

    private static long getLong(String question){
        System.out.println(question);
        String longScanner = scanner.nextLine();
        if(!isLong(longScanner)){
            System.out.println(incorrectLong);
            getLong(question);
        }
        return Long.parseLong(longScanner);
    }

    private static String getName(){
        System.out.println("What is his name?");
        String name = scanner.nextLine();
        return Format.formatName(name);
    }

    private static void createStudent(){
        String name = getName();
        int age = getInt("How old is the student?");
        University.addStudent(new Student(name,age));
    }

    private static void deleteStudent(){
        long id = getLong("What is the student's id?");
        int studentIndex = University.searchByStudentId(id);
        if(studentIndex == -1){
            System.out.println("No student was found");
        }
        else{
            University.deleteStudent(University.getStudents().get(studentIndex));
            System.out.println("Student was removed");
        }
    }

    private static void seeStudents(){
        System.out.println(University.getStudentsInfo());
    }

    private static void seeStudentClasses(){
        long id = getLong("What is the student's id?");
        System.out.println(University.getStudentClasses(id));
    }

    private static void askStudentActions(){
        System.out.println("""
                1. Create student.
                2. Delete student.
                3. See students.
                4. See classes of a student.
                5. Return to menu.
                """);
        String scannerOption = scanner.nextLine();
        if(isInteger(scannerOption)){
            option = Integer.parseInt(scannerOption);
            switch (option){
                case 1:
                    createStudent();
                    askStudentActions();
                    break;
                case 2:
                    deleteStudent();
                    askStudentActions();
                    break;
                case 3:
                    seeStudents();
                    askStudentActions();
                    break;
                case 4:
                    seeStudentClasses();
                    askStudentActions();
                    break;
                case 5:
                    askActions();
                    break;
                default:
                    System.out.println(noScenario);
                    askStudentActions();
                    break;
            }
        }
        else{
            System.out.println(incorrectInt);
            askStudentActions();
        }
    }

    private static String getTeacherType(){
        System.out.println("""
                Which teacher type?
                1. Full time.
                2. Part time
                """);
        String scannerOption = scanner.nextLine();
        if(!isInteger(scannerOption)){
            System.out.println(incorrectInt);
            getTeacherType();
        }
        int teacherOption = Integer.parseInt(scannerOption);
        if(teacherOption == 1){
            return "full";
        }else if(teacherOption == 2){
            return "part";
        }
        System.out.println(noScenario);
        getTeacherType();
        return "";
    }

    private static void createTeacher(){
        String name = getName();
        String teacherType = getTeacherType();
        int searchTeacherIndex = University.searchByTeacherName(name);
        // No teacher was found, then can create it
        if(searchTeacherIndex == -1){
            Teacher teacher;
            if(Objects.equals(teacherType,"full")){
                int expYears = getInt("How many years of the experience does the teacher have?");
                teacher = new FullTimeTeacher(name,expYears);
            } else if (Objects.equals(teacherType,"part")) {
                int hours = getInt("How many hours does the teacher work?");
                teacher = new PartTimeTeacher(name,hours);
            } else{
                teacher = new Teacher(name);
            }
            University.addTeacher(teacher);
        }else{
            System.out.println("Can't create repeated teachers");
        }
    }

    private static void deleteTeacher(){
        String name = getName();
        int teacherIndex = University.searchByTeacherName(name);
        // There is a teacher in the list
        if(teacherIndex != -1){
            University.deleteTeacher(University.getTeachers().get(teacherIndex));
            System.out.println("Teacher was removed");
        }else{
            System.out.println("No teacher was found");
        }
    }

    private static void seeTeachers(){
        System.out.println(University.getTeachersInfo());
    }

    private static void askTeacherActions(){
        System.out.println("""
                1. Create teacher.
                2. Delete teacher.
                3. See teachers.
                4. Return to menu.
                """);
        String scannerOption = scanner.nextLine();
        if(isInteger(scannerOption)){
            option = Integer.parseInt(scannerOption);
            switch (option){
                case 1:
                    createTeacher();
                    askTeacherActions();
                    break;
                case 2:
                    deleteTeacher();
                    askTeacherActions();
                    break;
                case 3:
                    seeTeachers();
                    askTeacherActions();
                    break;
                case 4:
                    askActions();
                    break;
                default:
                    System.out.println(noScenario);
                    askTeacherActions();
                    break;
            }
        }else{
            System.out.println(incorrectInt);
            askTeacherActions();
        }
    }

    private static void exit(){
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public static void askActions(){
        System.out.println("""
                What do you want to do?
                1. Check students.
                2. Check teachers.
                3. Check classes.
                4. Exit.
                """);
        String scannerOption = scanner.nextLine();
        if(isInteger(scannerOption)){
            option = Integer.parseInt(scannerOption);
            switch (option){
                case 1:
                    askStudentActions();
                    break;
                case 2:
                    askTeacherActions();
                    break;
                case 4:
                    exit();
                    break;
                default:
                    System.out.println(noScenario);
                    askActions();
                    break;
            }
        }else{
            System.out.println(incorrectInt);
            askActions();
        }
    }

}
