import java.util.*;

public class Student {
    private String name;

    private int id;
    private int totalCredit = 0;
    private boolean isPass;
    private List<String> letters;
    private String password;
    private Double avgGrades;
    private Map<Course, Double> grades;
    private List<Course> StudentCourses;

    public Student(String name, int id, String password, int totalCredit , Double avgGreades , boolean isPass) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.letters = new ArrayList<>();
        this.grades = new HashMap<>();
        this.StudentCourses = new ArrayList<>();
        this.totalCredit = totalCredit;
        this.avgGrades = avgGreades;
        this.isPass = isPass;
    }

    public String getName() {
        return name;
    }
    public boolean getIsPass() {
        return isPass;
    }
    public void setIsPass(boolean isPass) {
        this.isPass = isPass;
    }
    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public int getTotalCredit() {
        return totalCredit;
    }
    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }
    public List<String> getLetters() {
        return letters;
    }
    public List<Course> getStudentCourses() {
        return StudentCourses;
    }
    public Map<Course, Double> getGrades() {
        return grades;
    }
    public void setAvgGrades(Double avgGrades) {
        this.avgGrades = avgGrades;
    }

    public Double getAvgGrades() {
        return avgGrades;
    }

    public void enroll(Student student) {
        if (Staff.getCourses().size() == 0) {
            System.out.println("there is no course for enroll");
            Main.studentMenu(student);
        }
        int flag = 0;
        boolean temp = false;
        boolean temp2 = false;
        Staff.getCourses().forEach(course -> System.out.println(course.getCourseName() + "->" + course.getId()));
        Scanner enroll = new Scanner(System.in);
        System.out.println("please enter your desired course id ");
        int courseID = enroll.nextInt();
        for (Course Course : Staff.getCourses()) {
            if (courseID == Course.getId()) {
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("this course doesn't exist");
            enroll(student);
        }
        else {
            flag = 0;
            for(Course course : Staff.getCourses()) {
                if (course.getId() == courseID) {
                    temp = isConflict(course , student);
                }
            }
            if (temp) {
                System.out.println("this course has conflict with your other course ");
                enroll(student);
            }
            if (!temp) {
                flag = 1;
            }
            if (flag == 1) {
                flag = 0;
                for (Course course : Staff.getCourses()){
                    if (course.getId() == courseID) {
                        if (student.getTotalCredit() < 20 && 20 - student.getTotalCredit() >= course.getCredit()){
                            flag = 1;
                            break;
                        }
                        else {
                            System.out.println("you are enroll in maximum of Allowed credits");
                            Main.studentMenu(student);
                        }
                    }
                }
            }
        }
        for (Course course : Staff.getCourses()) {
            flag = 0;
            if (courseID == course.getId()) {
                flag = 1;
                student.getStudentCourses().add(course);
                student.getGrades().put(course , 0.00);
                student.setTotalCredit(student.getTotalCredit() + course.getCredit());
                course.getCourseStus().add(student);
                Main.studentMenu(student);
            }
        }
    }
    public void writeToProf(Student student) {
        Scanner writeToProf = new Scanner(System.in);
        System.out.println("enter your letter for professor");
        String letterToProf = writeToProf.nextLine();
        student.getGrades().keySet().forEach(course -> {
            for (Professor professor : Staff.getProfessors()) {
                if (professor.getId() == course.getProfId()) {
                    professor.getLetters().add(letterToProf);
                }
            }
        });
    }

    public void writeToStaff() {
        Scanner writeToStaff = new Scanner(System.in);
        System.out.println("enter your letter to staff");
        String letterToStaff = writeToStaff.nextLine();
        Staff.getLetters().add(letterToStaff);
    }

    public void writeToStu() {
        Scanner writeToStudents = new Scanner(System.in);
        System.out.println("what is your letter to students");
        String letterToStudents = writeToStudents.nextLine();
        Staff.getStudents().forEach(student -> {
            student.getLetters().add(letterToStudents);
        });
    }

    public void letters(Student student) {
        student.getLetters().forEach(System.out::println);
    }

    public boolean isConflict(Course course , Student student) {
        boolean conflict = false;
        for (Course course1 : student.getStudentCourses()) {
            if (course.getClassDay().equals(course1.getClassDay())) {
                if ((course.getClassStart() > course1.getClassStart() && course.getClassStart() < course1.getClassStart() + 200))
                    conflict = true;
                if (course.getClassStart() + 200 > course1.getClassStart() && course.getClassStart() +200 < course1.getClassStart() + 200)
                    conflict = true;
            }
            if (course.getExamStart() > course1.getExamStart() && course.getExamStart() < course1.getExamEnd())
                conflict = true;
            if (course.getExamEnd() > course1.getExamStart() && course.getExamEnd() < course1.getExamEnd())
                conflict = true;
        }
        return conflict;
    }
}
