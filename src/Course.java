import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private int id;
    private int credit;
    private int profId;
    private List<Student> courseStus;
    private  String classDay;
    private int classStart;
    private int examStart;
    private  int examEnd;

    public Course(String courseName, int id , int profId , int credits , String classDay , int classStart , int examStart , int examEnd) {
        this.courseName = courseName;
        this.id = id;
        this.profId = profId;
        this.credit = credits;
        this.courseStus = new ArrayList<>();
        this.classDay = classDay;
        this.classStart = classStart;
        this.examStart = examStart;
        this.examEnd = examEnd;
    }

    public int getId() {
        return id;
    }

    public List<Student> getCourseStus() {
        return courseStus;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getProfId() {
        return profId;
    }

    public int getCredit() {
        return credit;
    }

    public String getClassDay() {
        return classDay;
    }

    public int getClassStart() {
        return classStart;
    }

    public int getExamStart() {
        return examStart;
    }

    public int getExamEnd() {
        return examEnd;
    }
}
