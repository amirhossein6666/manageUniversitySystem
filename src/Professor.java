import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Professor {
    private String name;
    private int id;
    private String facultyName;
    private String password;
    private int numOfGrades;
    private List<String> letters;
    private List<Course> profCourse;
    private  List<Professor> professors;
    public Professor(String name, String facultyName , int id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.facultyName = facultyName;
        this.letters = new ArrayList<>();
        this.profCourse = new ArrayList<>();
        this.professors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getNumOfGrades() {
        return numOfGrades;
    }

    public void setNumOfGrades(int numOfGrades) {
        this.numOfGrades = numOfGrades;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getLetters() {
        return letters;
    }

    public List<Course> getProfCourse() {
        return profCourse;
    }

    public List<Professor> getProfessors() {
        return professors;
    }


    public void writeToProf(Professor professor) {
        Scanner writeToProf = new Scanner(System.in);
        System.out.println("enter your letter to other professors");
        String letterToProfs = writeToProf.nextLine();
        for (Professor prof : professor.getProfessors()) {
            prof.getLetters().add(letterToProfs);
        }
    }
    public void writeToStu(Professor professor) {
        Scanner writeToStu = new Scanner(System.in);
        System.out.println("enter your letter to students");
        String letterToStudents = writeToStu.nextLine();
        for (Course course : professor.getProfCourse()) {
            for (Student student : course.getCourseStus()) {
                student.getLetters().add(letterToStudents);
            }
        }
    }
    public void writeToStaff() {
        Scanner writeToStaff = new Scanner(System.in);
        System.out.println("enter your letter to staff");
        String lettersTOStaff = writeToStaff.nextLine();
        Staff.getLetters().add(lettersTOStaff);
    }
    public void letters(Professor professor) {
        for (String letter: professor.getLetters()) {
            System.out.println(letter);
        }
    }
    public void setGrades(Professor professor){
        Scanner setGrades = new Scanner(System.in);
        for (Course course : professor.getProfCourse()) {
            for (Student student : course.getCourseStus()) {
                System.out.println("what is your desired grade of " + course.getCourseName() + " for " + student.getName());
                double grade = setGrades.nextDouble();
                student.getGrades().put(course , grade);
                System.out.println("the grades for this student set");
            }
        }
    }

    public void seeCourses(Professor professor) {
        for (Course course : professor.getProfCourse()) {
            System.out.println(course.getCourseName());
        }
    }
}
