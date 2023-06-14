import java.util.*;

public class Student {
    private String name;

    private int id;
    private int totalCredit;

    private List<String> letters;
    private String password;
    private Double avgGrades;
    private Map<Course, Double> grades;
    private List<Course> StudentCourses;

    public Student(String name, int id, String password, int totalCredit) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.letters = new ArrayList<>();
        this.grades = new HashMap<>();
        this.StudentCourses = new ArrayList<>();
        this.totalCredit = totalCredit;
    }

    public String getName() {
        return name;
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

    public void enroll(Student student) {
        int flag1 = 0;
        int flag2 = 0;
        int flag3 = 0;
        Scanner enroll = new Scanner(System.in);
        do {
            System.out.println("enter your desired course ID");
            int courseId = enroll.nextInt();
            int indexOFCourseID = 0;
            for (int i = 0; i < Staff.getCourses().size(); i++) {
                if (courseId == Staff.getCourses().get(i).getId()) {
                    indexOFCourseID = i;
                    break;
                }
            }
            for (Course course : Staff.getCourses()) {
                if (course.getId() == courseId) {
                    flag1 = 1;
                    break;
                }
            }
            if (flag1 == 0)
                System.out.println("this course doesn't exists");
            if (flag1 == 1) {
                for (Course course : student.getStudentCourses()) {
                    flag2 = 0;
                    if (!(Staff.getCourses().get(indexOFCourseID).getClassDay().equals(course.getClassDay()))) {
                        if (!((Staff.getCourses().get(indexOFCourseID).getClassStart() > course.getClassStart() && Staff.getCourses().get(indexOFCourseID).getClassStart() <
                                course.getClassStart() + 200) || (Staff.getCourses().get(indexOFCourseID).getClassStart() + 200 > course.getClassStart() &&
                                Staff.getCourses().get(indexOFCourseID).getClassStart() + 200 < course.getClassStart() + 200))) {
                            if (!((Staff.getCourses().get(indexOFCourseID).getExamStart() > course.getExamStart() && Staff.getCourses().get(indexOFCourseID).getExamStart() < course.getExamEnd())
                                    || (Staff.getCourses().get(indexOFCourseID).getExamEnd() > course.getExamStart()) && Staff.getCourses().get(indexOFCourseID).getExamEnd() < course.getExamEnd())) {
                                flag2 = 1;
                            }
                        }
                    }
                    if (flag2 == 0) {
                        System.out.println("this course is conflicted with your other course");
                        break;
                    }
                }
            }
            if (student.getTotalCredit() < 20 && Staff.getCourses().get(indexOFCourseID).getCredit() < 20 - student.getTotalCredit()) {
                flag3 = 1;
            }
            if (flag3 == 0)
                System.out.println("you can't enroll in more than 20 credit");
        } while (flag1 == 1 && flag2 == 1 && flag3 == 1);

    }

    public void writeToProf() {
        Scanner writeToProf = new Scanner(System.in);
        System.out.println("enter your letter for professor");
        String letterToProf = writeToProf.nextLine();
        this.getGrades().keySet().forEach(course -> {
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

    public int calculateSumOfCradits(Student student) {
        return student.getGrades().keySet().stream().mapToInt(Course::getCredit).sum();
    }
}
