import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        enter();
    }

    private static void enter() {
        Staff staff = new Staff();
        printMainMenu();
        Scanner mainMenu = new Scanner(System.in);
        int mainChoice = mainMenu.nextInt();
        switch (mainChoice) {
            case 1 -> studentSignIn();
            case 2 -> professorSignIn();
            case 3 -> signIn();
            case 0 -> System.exit(0);
            default -> {
                System.out.println("wrong choice");
                enter();
            }
        }
    }

    private static void signIn() {
        Scanner signIn = new Scanner(System.in);
        System.out.println("please enter your id");
        int id = signIn.nextInt();
        System.out.println("please enter your password");
        String password = signIn.next();
        if (password.equals("Staff") && id == 1111)
            staff();
        else {
            System.out.println("wrong sign in information");
            signIn();
        }

    }

    public static void staff() {
        Scanner staff = new Scanner(System.in);
        try {
            RandomAccessFile rfile = new RandomAccessFile("data.txt", "rw");
            printStaffMenu();
            int staffChoice = staff.nextInt();
            switch (staffChoice) {
                case 1 -> {
                    Staff.addProfessor();
                    staff();
                }
                case 2 -> {
                    Staff.addFaculty();
                    staff();
                }
                case 3 -> {
                    Staff.addStudent();
                    staff();
                }
                case 4 -> {
                    Staff.addStudentFile(rfile);
                    staff();
                }
                case 5 -> {
                    Staff.writeToStu();
                    staff();
                }
                case 6 -> {
                    Staff.writeToProf();
                    staff();
                }
                case 7 -> {
                    Staff.seeLetters();
                    staff();
                }
                case 8 -> {
                    Staff.addSemester();
                    staff();
                }
                case 9 -> {
                    Staff.addCourse();
                    staff();
                }
                case 10 -> {
                    Staff.semesterEnd();
                    staff();
                }
                case 0 -> enter();
                default -> {
                    System.out.println("wrong input");
                    staff();
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printStaffMenu() {
        System.out.println("Staff Menu");
        System.out.println("1 :  add Professors");
        System.out.println("2 :  add Faculties");
        System.out.println("3 :  add Students");
        System.out.println("4 :  add Students from rFile");
        System.out.println("5 :  letter to students");
        System.out.println("6 :  letter to professors");
        System.out.println("7 :  see letters");
        System.out.println("8 :  add semester");
        System.out.println("9 :  add course");
        System.out.println("10 : end of semester");
        System.out.println("0 : Come Back To Main Menu");
    }

    public static void professorSignIn() {
        int flag = 0;
        Scanner profSignIn = new Scanner(System.in);
        System.out.println("please enter your id");
        int profID = profSignIn.nextInt();
        System.out.println("please enter your password");
        String profPass = profSignIn.next();
        for (Professor professor : Staff.getProfessors()) {
            if (profID == professor.getId() && profPass.equals(professor.getPassword())) {
                flag = 1;
                professorMenu(professor);
            }
        }
        if (flag == 0) {
            System.out.println("your id and password doesn't match");
            professorSignIn();
        }
    }

    public static void professorMenu(Professor professor) {
        Scanner profMenu = new Scanner(System.in);
        printProfMenu();
        int profchoice = profMenu.nextInt();
        switch (profchoice) {
            case 1 -> {
                professor.writeToProf(professor);
                professorMenu(professor);
            }
            case 2 -> {
                professor.writeToStu(professor);
                professorMenu(professor);
            }
            case 3 -> {
                professor.writeToStaff();
                professorMenu(professor);
            }
            case 4 -> {
                professor.letters(professor);
                professorMenu(professor);
            }
            case 5 -> {
                professor.setGrades(professor);
                professorMenu(professor);
            }
            case 6 -> {
                professor.seeCourses(professor);
                professorMenu(professor);
            }
            case 0 -> enter();
            default -> {
                System.out.println("wrong input");
                professorMenu(professor);
            }
        }
    }

    public static void printProfMenu() {
        System.out.println("professor menu");
        System.out.println("1 : letter to other professors");
        System.out.println("2 : letter to Students");
        System.out.println("3 : letter to Staff");
        System.out.println("4 : see letters");
        System.out.println("5 : set grades of Students");
        System.out.println("6 : see your courses");
        System.out.println("0 : come Back to Main Menu");
    }

    private static void studentSignIn() {
        int flag = 0;
        Scanner stuSignIn = new Scanner(System.in);
        while (flag == 0) {
            System.out.println("please enter your id");
            int stuID = stuSignIn.nextInt();
            System.out.println("please enter your password");
            String stuPass = stuSignIn.next();
            for (Student student : Staff.getStudents()) {
                if (student.getId() == stuID && student.getPassword().equals(stuPass)) {
                    flag = 1;
                    studentMenu(student);
                    break;
                }
            }
            if (flag == 0)
                System.out.println("id and password doesn't match");
        }
    }

    public static void studentMenu(Student student) {
        printStuMenu();
        Scanner stuMenu = new Scanner(System.in);
        int stuMeuChoice = stuMenu.nextInt();
        switch (stuMeuChoice) {
            case 1 -> {
                student.enroll(student);
                studentMenu(student);
            }
            case 2 -> {
                student.writeToStu();
                studentMenu(student);
            }
            case 3 -> {
                student.writeToProf(student);
                studentMenu(student);
            }
            case 4 -> {
                student.writeToStaff();
                studentMenu(student);
            }
            case 5 -> {
                student.letters(student);
                studentMenu(student);
            }
            case 6 -> {
                student.getStudentCourses().forEach(course -> System.out.println(course.getCourseName()));
                studentMenu(student);
            }
            case 0 -> enter();
            default -> {
                System.out.println("wrong choice try again");
                studentMenu(student);
            }
        }
    }

    private static void printStuMenu() {
        System.out.println("Student Menu");
        System.out.println("1 : enroll in Courses");
        System.out.println("2 : letter to other Students");
        System.out.println("3 : letter to professors");
        System.out.println("4 : letter to Staff");
        System.out.println("5 : see letters");
        System.out.println("6 :  see list of enrolled course");
        System.out.println("0 : Come Back to Main Menu");
    }

    public static void printMainMenu() {
        System.out.println("-----------------------------------------------------");
        System.out.println("---------------------  welcome  ---------------------");
        System.out.println("--------------------  Main menu  ---------------------");
        System.out.println("enter your role");
        System.out.println("1 : Student");
        System.out.println("2 : Professor");
        System.out.println("3 : Staff");
        System.out.println("0 : Exit");
    }
}