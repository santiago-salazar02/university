package edu.university;

import edu.university.helpers.Format;
import edu.university.management.Class;
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
    private final static String incorrectDouble = "Your input is incorrect, only doubles are accepted as valid inputs";
    private final static String incorrectLong = "Your input is incorrect, only longs are accepted as valid inputs";
    private final static String incorrectOnlyPositive = "Your input is incorrect, only positive numbers are accepted as valid inputs";

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

    private static int getInt(String question,boolean onlyPositive){
        boolean inProcess = true;
        int number = 0;
        while(inProcess){
            System.out.println(question);
            String intScanner = scanner.nextLine();
            if(isInteger(intScanner)){
                number = Integer.parseInt(intScanner);
                if(number < 0 && onlyPositive){
                    System.out.println(incorrectOnlyPositive);
                }else{
                    inProcess = false;
                }
            }else{
                System.out.println(incorrectInt);
            }
        }
        return number;
    }

    private static double getDouble(String question,boolean onlyPositive){
        boolean inProcess = true;
        double number = 0;
        while(inProcess){
            System.out.println(question);
            String doubleScanner = scanner.nextLine();
            if(isDouble(doubleScanner)){
                number = Double.parseDouble(doubleScanner);
                if(number < 0 && onlyPositive){
                    System.out.println(incorrectOnlyPositive);
                }else{
                    inProcess = false;
                }
            }else{
                System.out.println(incorrectInt);
            }
        }
        return number;
    }

    private static long getLong(String question, boolean onlyPositive){
        boolean inProcess = true;
        long number = 0;
        while(inProcess){
            System.out.println(question);
            String longScanner = scanner.nextLine();
            if(!isLong(longScanner)){
                System.out.println(incorrectLong);
            }else{
                number = Long.parseLong(longScanner);
                if(number < 0 && onlyPositive){
                    System.out.println(incorrectOnlyPositive);
                }else{
                    inProcess = false;
                }
            }
        }
        return number;
    }

    private static String getName(){
        boolean inProcess = true;
        String name = "";
        while(inProcess){
            System.out.println("What is the name?");
            name = scanner.nextLine();
            if(!name.isBlank()){
                name = Format.formatName(name);
                inProcess = false;
            }else{
                System.out.println("Name cant be blank");
            }
        }
        return name;
    }

    private static void createStudent(){
        String name = getName();
        int age = getInt("How old is the student?",true);
        University.addStudent(new Student(name,age));
        System.out.println("Student added");
    }

    private static void deleteStudent(){
        long id = getLong("What is the student's id?",true);
        int studentIndex = University.searchByStudentId(id);
        if(studentIndex == -1){
            System.out.println("No student was found");
        }
        else{
            University.deleteStudent(University.getStudents().get(studentIndex));
            System.out.println("Student removed");
        }
    }

    private static void seeStudents(){
        System.out.println(University.getStudentsInfo());
    }

    private static void seeStudentClasses(){
        long id = getLong("What is the student's id?",true);
        System.out.println(University.getStudentClasses(id));
    }

    private static void askStudentActions(){
        boolean inProcess = true;
        while(inProcess){
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
                        break;
                    case 2:
                        deleteStudent();
                        break;
                    case 3:
                        seeStudents();
                        break;
                    case 4:
                        seeStudentClasses();
                        break;
                    case 5:
                        inProcess = false;
                        break;
                    default:
                        System.out.println(noScenario);
                        break;
                }
            }
            else{
                System.out.println(incorrectInt);
            }
        }
    }

    private static String getTeacherType(){
        boolean inProcess = true;
        String teacherType = "";
        while(inProcess){
            System.out.println("""
                Which teacher type?
                1. Full time.
                2. Part time
                """);
            String scannerOption = scanner.nextLine();
            if(!isInteger(scannerOption)){
                System.out.println(incorrectInt);
            }else{
                int teacherOption = Integer.parseInt(scannerOption);
                if(teacherOption == 1){
                    teacherType = "full";
                    inProcess = false;
                }else if(teacherOption == 2){
                    teacherType = "part";
                    inProcess = false;
                }else{
                    System.out.println(noScenario);
                }
            }
        }
        return teacherType;
    }

    private static void createTeacher(){
        String name = getName();
        String teacherType = getTeacherType();
        int searchTeacherIndex = University.searchByTeacherName(name);
        // No teacher was found, then can create it
        if(searchTeacherIndex == -1){
            Teacher teacher = null;
            if(Objects.equals(teacherType,"full")){
                int expYears = getInt("How many years of the experience does the teacher have?",true);
                teacher = new FullTimeTeacher(name,expYears);
            } else if (Objects.equals(teacherType,"part")) {
                int hours = getInt("How many hours does the teacher work?",true);
                if(hours < Teacher.getMaxLaborHours() && hours>0){
                    teacher = new PartTimeTeacher(name,hours);
                }else{
                    System.out.println("Error: Incorrect work hours");
                }
            } else{
                teacher = new Teacher(name);
            }
            if(teacher != null){
                University.addTeacher(teacher);
                System.out.println("Teacher added");
            }
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

    private static void updateBaseSalary(){
        double baseSalary = getDouble("How many for the new base salary?",true);
        University.updateTeachersSalary(baseSalary);
        System.out.println("Salary updated");
    }

    private static void updateLaborHours(){
        int hours = getInt("How many hours per week?",true);
        if(hours >= 2){
            University.updateLaborHours(hours);
            System.out.println("Labor time updated");
        }else{
            System.out.println("Error: Labor time should me greater or equal than 2");
        }

    }

    private static void askTeacherActions(){
        boolean inProcess = true;
        while(inProcess){
            System.out.println("""
                1. Create teacher.
                2. Delete teacher.
                3. See teachers.
                4. Update base salary.
                5. Update maximum labor time.
                6. Return to menu.
                """);
            String scannerOption = scanner.nextLine();
            if(isInteger(scannerOption)){
                option = Integer.parseInt(scannerOption);
                switch (option){
                    case 1:
                        createTeacher();
                        break;
                    case 2:
                        deleteTeacher();
                        break;
                    case 3:
                        seeTeachers();
                        break;
                    case 4:
                        updateBaseSalary();
                        break;
                    case 5:
                        updateLaborHours();
                        break;
                    case 6:
                        inProcess = false;
                        break;
                    default:
                        System.out.println(noScenario);
                        break;
                }
            }else{
                System.out.println(incorrectInt);
            }
        }
    }

    private static void createClass(){
        String name = getName();
        int classroom = getInt("What is it's classroom?",true);
        int indexClass = University.searchByClassRoom(classroom);
        // No class found -> Can create a class
        if(indexClass == -1){
            University.addClass(new Class(name,classroom,null));
            System.out.println("Class added");
        }else{
            System.out.println("Can't created repeated classes");
        }
    }

    private static void deleteClass(){
        int classroom = getInt("What is it's classroom?",true);
        int indexClass = University.searchByClassRoom(classroom);
        // No class was found -> Cant remove an imaginary class
        if(indexClass == -1){
            System.out.println("No class was found");
        }else{
            University.deleteClass(University.getClasses().get(indexClass));
            System.out.println("Class deleted");
        }
    }

    private static void seeClasses(){
        System.out.println(University.getClassesInfo());
    }

    private static void addStudent2Class(){
        int classroom = getInt("What is it's classroom?",true);
        int indexClass = University.searchByClassRoom(classroom);
        if(indexClass == -1){ // No class was found
            System.out.println("No class was found");
        }else{
            Class aclass = University.getClasses().get(indexClass);
            long idStudent = getLong("What is the student's id?",true);
            boolean isStudent = aclass.isStudentInClass(idStudent);
            if(isStudent){ // If the student is in the class -> Can't add repeated student
                System.out.println("Can't add repeated student");
            }else{
                int indexStudent = University.searchByStudentId(idStudent);
                if(indexStudent == -1){
                    System.out.println("No student was found");
                }else{
                    aclass.addStudent(University.getStudents().get(indexStudent));
                    System.out.println("Student added");
                }
            }
        }
    }

    private static void deleteStudent2Class(){
        int classroom = getInt("What is it's classroom?",true);
        int indexClass = University.searchByClassRoom(classroom);
        if(indexClass == -1){ // No class was found
            System.out.println("No class was found");
        }else{
            Class aclass = University.getClasses().get(indexClass);
            long idStudent = getLong("What is the student's id?",true);
            boolean isStudent = aclass.isStudentInClass(idStudent);
            if(isStudent){ // Can delete the student
                int indexStudent = University.searchByStudentId(idStudent);
                aclass.deleteStudent(University.getStudents().get(indexStudent));
                System.out.println("Student deleted");
            }else{
                System.out.println("No student was found");
            }
        }
    }

    private static void addTeacher2Class(){
        int classroom = getInt("What is it's classroom?",true);
        int indexClass = University.searchByClassRoom(classroom);
        if(indexClass == -1){ // No class was found
            System.out.println("No class was found");
        }else{
            Class aclass = University.getClasses().get(indexClass);
            System.out.println("Now give the teacher's info");
            String name = getName();
            int indexTeacher = University.searchByTeacherName(name);
            if(indexTeacher == -1){ // No teacher was found -> There is no teacher with that id, cant add an imaginary teacher
                System.out.println("No teacher was found");
            }else{
                aclass.setTeacher(University.getTeachers().get(indexTeacher));
                System.out.println("Teacher added");
            }
        }
    }

    private static void deleteTeacher2Class(){
        int classroom = getInt("What is it's classroom?",true);
        int indexClass = University.searchByClassRoom(classroom);
        if(indexClass == -1){ // No class was found
            System.out.println("No class was found");
        }else{
            Class aclass = University.getClasses().get(indexClass);
            if(aclass.getTeacher() == null){
                System.out.println("There was no teacher in this class");
            }else{
                aclass.setTeacher(null);
                System.out.println("Teacher deleted");
            }
        }
    }

    private static void askClassAction(){
        boolean inProcess = true;
        while(inProcess){
            System.out.println("""
                1. Create class.
                2. Delete class.
                3. See classes.
                4. Add student.
                5. Remove student.
                6. Add teacher.
                7. Remove teacher.
                8. Return to menu.
                """);
            String scannerOption = scanner.nextLine();
            if(isInteger(scannerOption)){
                option = Integer.parseInt(scannerOption);
                switch (option){
                    case 1:
                        createClass();
                        break;
                    case 2:
                        deleteClass();
                        break;
                    case 3:
                        seeClasses();
                        break;
                    case 4:
                        addStudent2Class();
                        break;
                    case 5:
                        deleteStudent2Class();
                        break;
                    case 6:
                        addTeacher2Class();
                        break;
                    case 7:
                        deleteTeacher2Class();
                        break;
                    case 8:
                        inProcess = false;
                        break;
                    default:
                        System.out.println(noScenario);
                        break;
                }
            }else{
                System.out.println(incorrectInt);
            }
        }
    }

    private static void exit(){
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public static void askActions(){
        boolean inProcess = true;
        while(inProcess){
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
                    case 3:
                        askClassAction();
                        break;
                    case 4:
                        inProcess = false;
                        exit();
                        break;
                    default:
                        System.out.println(noScenario);
                        break;
                }
            }else{
                System.out.println(incorrectInt);
            }
        }

    }

}
