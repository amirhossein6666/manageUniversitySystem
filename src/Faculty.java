import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String name;
    private int id;
    private Professor chairProf;
    private List<Professor> facultyProffs;

    public Faculty(String name , int id , Professor prof) {
        this.name = name;
        this.id = id;
        this.chairProf = prof;
        this.facultyProffs = new ArrayList<>();
    }

    public List<Professor> getFacultyProffs() {
        return facultyProffs;
    }

    public String getName() {
        return name;
    }
}
