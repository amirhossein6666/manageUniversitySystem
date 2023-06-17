import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Staff {
    private static ArrayList<Professor> professors = new ArrayList<>();
    private static ArrayList<Faculty> faculties = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<String> letters = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Semester> semesters = new ArrayList<>();

//    public Staff() {
//        professors = new ArrayList<>();
//        faculties = new ArrayList<>();
////        students = new ArrayList<>();
//        letters = new ArrayList<>();
//        courses = new ArrayList<>();
//        semesters = new ArrayList<>();
//    }

    public static List<Course> getCourses() {
        return courses;
    }

    public static List<Semester> getSemesters() {
        return semesters;
    }

    public static List<Faculty> getFaculties() {
        return faculties;
    }

    public static List<Professor> getProfessors() {
        return professors;
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static List<String> getLetters() {return letters;}

    public static void addProfessor() {
        if (getFaculties().size() == 0) {
            System.out.println("please add a faculty first");
            addFaculty();
        }
        if (getSemesters().size() == 0){
            System.out.println("please add semester first");
            addSemester();
        }
        int flag = 0;
        int flag2 = 1;
        int flag3 = 0;
        String facultyName = "";
        int profId = 0;
        int profSemesterId = 0;
        Scanner addProfessor = new Scanner(System.in);
        while (flag == 0) {
            System.out.println("please enter the name of faculty of this professor");
            facultyName = addProfessor.next();
            for (Faculty faculty : getFaculties()) {
                if (faculty.getName().equals(facultyName)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                System.out.println("the faculty name is invalid");
            }
        }
        System.out.println("enter the name of professor");
        String profName = addProfessor.next();
        do {
            System.out.println("enter the id of professor");
            profId = addProfessor.nextInt();
            for (Professor professor : getProfessors()) {
                if (professor.getId() == profId) {
                    System.out.println("this professor id is already taken");
                    flag2 = 0;
                    break;
                }
            }
        } while (flag2 != 1);
        System.out.println("enter the password of professor");
        String profPassword = addProfessor.next();
        Professor professor = new Professor(profName, facultyName, profId, profPassword);
        while (flag3 == 0) {
            System.out.println("enter the id of semester that you want to this professor join");
            profSemesterId = addProfessor.nextInt();
            for (Semester semester :getSemesters()) {
                if(semester.getId() == profSemesterId) {
                    semester.getProfList().add(professor);
                    flag3 = 1;
                    break;
                }
            }
            if (flag3 == 0) {
                System.out.println("this semester id is invalid");
            }
        }
        for (Professor professor1 : getProfessors()) {
            professor1.getProfessors().add(professor1);
        }
        getProfessors().add(professor);
    }


    public static void addFaculty() {
        int flag = 0;
        Professor chairProf = new Professor("", "", 0, "");
        Scanner addFaculty = new Scanner(System.in);
        System.out.println("enter this faculty name");
        String facultyName = addFaculty.next();
        System.out.println("enter the faculty ID");
        int facultyId = addFaculty.nextInt();
        System.out.println("what is the name of chair professor of this faculty");
        String chairProfName = addFaculty.next();
//        while (flag == 0) {
//            for (int i = 0; i < getProfessors().size(); i++) {
//                if (getProfessors().get(i).getName().equals(chairProfName)) {
//                    chairProf = professors.get(i);
//                    int chairProfId = getProfessors().get(i).getId();
//                    flag = 1;
//                }
//                if (flag == 0) {
//                    System.out.println("this professor doesn't exist");
//                }
//            }
//            Faculty faculty = new Faculty(facultyName, facultyId, chairProf);
//            getFaculties().add(faculty);
//        }
        Faculty faculty = new Faculty(facultyName, facultyId, chairProf);
        getFaculties().add(faculty);
    }

    public static void addStudent() {
        int flag = 1;
        int studentId = 0;
        Scanner addStudent = new Scanner(System.in);
        System.out.println("enter the Student name");
        String studentName = addStudent.next();
        do {
            System.out.println("enter the student id");
            studentId = addStudent.nextInt();
            for (Student student : getStudents()) {
                if (student.getId() == studentId) {
                    System.out.println("this student id is already taken ");
                    flag = 0;
                    break;
                }
            }
        } while (flag != 1);

        System.out.println("enter the Student pass");
        String stupass = addStudent.next();
        Student student = new Student(studentName, studentId, stupass , 0);
        getStudents().add(student);
        for (Semester semester: getSemesters()) {
            if (semester.getStatus().equals("ongoing"))
                semester.getStudentList().add(student);
        }
    }

    public static void addStudentFile(RandomAccessFile rfile) {
        System.out.println("soon");
//        Main.staff();
    }

    public static void writeToStu() {
        Scanner writeToStu = new Scanner(System.in);
        System.out.println("enter your letter for Students");
        String letter = writeToStu.nextLine();
        for (int i = 0; i < getStudents().size(); i++) {
            getStudents().get(i).getLetters().add(letter);
        }
    }

    public static void writeToProf() {
        Scanner writeToProffs = new Scanner(System.in);
        System.out.println("enter your letter for professors");
        String letter = writeToProffs.nextLine();
        for (int i = 0; i < getProfessors().size(); i++) {
            getProfessors().get(i).getLetters().add(letter);
        }
    }

    public static void seeLetters() {
        for (int i = 0; i < getLetters().size(); i++) {
            System.out.println(getLetters().get(i));
        }
    }

    public static void addSemester() {
        int flag = 0;
        Scanner addSemester = new Scanner(System.in);
        System.out.println("please enter the id of semester");
        int id = addSemester.nextInt();
        String semesterStatus = "";
        while (flag == 0) {
            System.out.println("what is the semester Status");
            System.out.println("1 : completed");
            System.out.println("2 : upcoming");
            System.out.println("3 : ongoing");
            int IntsemesterStatus = addSemester.nextInt();
            switch (IntsemesterStatus) {
                case 1 -> {
                    semesterStatus = "completed";
                    flag = 1;
                }
                case 2 -> {
                    semesterStatus = "upcoming";
                    flag = 1;
                }
                case 3 -> {
                    int flag2 = 1;
                    for (int i = 0; i < getSemesters().size(); i++) {
                        if (getSemesters().get(i).getStatus().equals("ongoing")) {
                            System.out.println("ongoing semester already exits and can not be increased");
                            flag2 = 0;
                            break;
                        }
                    }
                    if (flag2 == 1) {
                        flag = 1;
                        semesterStatus = "ongoing";
                    }
                }
                default -> {
                    System.out.println("wrong input");
                }
            }
        }

        Semester semester = new Semester(id, semesterStatus);
        getSemesters().add(semester);
        System.out.println("add professors and Students and courses to this semester");
    }

    public static void addCourse() {
        if (Staff.getProfessors().size() == 0){
            System.out.println("add a professor first ");
            addProfessor();
        }
        int flag = 1;
        Scanner addCourse = new Scanner(System.in);
        System.out.println("enter the course name");
        String courseName = addCourse.next();
        int courseId = 0;
        int profId = 0;
        System.out.println("enter the course id");
        do {
            flag = 1;
            courseId = addCourse.nextInt();
            for (int i = 0; i < getCourses().size(); i++) {
                if (courseId == getCourses().get(i).getId()) {
                    System.out.println("this course id already exist");
                    flag = 0;
                    break;
                }
            }
        } while (flag != 1);
        flag = 0;
        while (flag == 0) {
            System.out.println("enter the course professor id");
            profId = addCourse.nextInt();
            for (int i = 0; i < getProfessors().size(); i++) {
                if (profId == getProfessors().get(i).getId()) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0)
                System.out.println("this professor doesn't exist");

        }
        System.out.println("enter the credit of this course for example 2 or 3");
        int courseCredit = addCourse.nextInt();
        System.out.println("enter the name of the week that course start on that");
        String courseDay = addCourse.next();
        System.out.println("enter the time that class start in 4 digit inteeger format ");
        int startClass = addCourse.nextInt();
        System.out.println("enter the time of start final exam in 4 digit format inteeger");
        int examStart = addCourse.nextInt();
        System.out.println("enter the time of end final exam in 4 digit format inteeger");
        int examEnd = addCourse.nextInt();
        Course course = new Course(courseName, courseId, profId , courseCredit , courseDay , startClass , examStart , examEnd);
        getCourses().add(course);
        for (int i = 0; i < getSemesters().size(); i++) {
            if (getSemesters().get(i).getStatus().equals("ongoing")) {
                getSemesters().get(i).getCourseList().add(course);
            }
        }
        for (Professor professor : getProfessors()) {
            if (professor.getId() == profId) {
                professor.getProfCourse().add(course);
                break;
            }
        }
    }

    public static void semesterEnd() {
        int flag = 0;
        int flag2 = 0;
        Scanner semesterEnd = new Scanner(System.in);
        if (checkAllGrades()) {
            System.out.println("Are you sure to finish this ongoing semester");
            System.out.println("1 : Yes");
            System.out.println("2 : NO");
            int isSure = semesterEnd.nextInt();
            if (isSure == 0) {
                System.out.println("this semester don't finish");
            } else if (isSure == 1) {
                calculateAvgGrades();
                for (int i = 0; i < getSemesters().size(); i++) {
                    if (getSemesters().get(i).getStatus().equals("ongoing")) {
                        getSemesters().get(i).setStatus("completed");
                        while (flag2 == 0) {
                            System.out.println("enter the id of your desired next semester");
                            int nextSemesterId = semesterEnd.nextInt();
                            for (Semester semester : getSemesters()) {
                                if (semester.getStatus().equals("upcoming") && semester.getId() == nextSemesterId) {
                                    semester.setStatus("ongoing");
                                    flag2 = 1;
                                    break;
                                }
                            }
                        }
                        flag = 1;
                    }
                    if (flag2 == 1)
                        break;
                }
                if (flag == 0) {
                    System.out.println("there is no ongoing semester to end");
                }
            } else {
                System.out.println("wrong input");
            }

        }
        else {
            System.out.println("all of the grades of the Students that have been in this semester don't enter");
            System.out.println("please tell the professors to enter the grades first");
        }
    }

    public static void calculateAvgGrades() {
        double sum = 0;
        for (Student student : Staff.getStudents()) {
            for (Course course: student.getGrades().keySet()) {
                sum += course.getCredit() * student.getGrades().get(course);
            }
            student.setAvgGrades(sum / student.getTotalCredit());
        }
    }

    public static boolean checkAllGrades() {
        int sum = 0;
        for (Professor professor : Staff.getProfessors()) {
            sum += professor.getNumOfGrades();
        }
        return sum == Staff.getStudents().size();
    }
}