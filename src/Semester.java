import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Semester {
    private int id;
    private String status;
    private List<Student> studentList;
    private List<Course> courseList;
    private List<Professor> profList;

    public Semester(int id, String status) {
        this.id = id;
        this.status = status;
        this.studentList = new ArrayList<>();
        this.courseList = new ArrayList<>();
        this.profList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public List<Professor> getProfList() {
        return profList;
    }
}
