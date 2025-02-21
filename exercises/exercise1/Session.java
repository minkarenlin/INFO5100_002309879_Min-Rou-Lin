import java.util.*;

class Session {
    private List<Student> students;

    public Session() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (students.size() < 20) {
            students.add(student);
        }
    }

    public double getAverageQuizScorePerStudent() {
        return students.stream().mapToDouble(Student::getAverageQuizScore).average().orElse(0.0);
    }

    public void printSortedQuizScores() {
        List<Integer> allScores = new ArrayList<>();
        for (Student student : students) {
            allScores.addAll(student.getQuizScores());
        }
        Collections.sort(allScores);
        System.out.println("Sorted Quiz Scores: " + allScores);
    }

    public void printPartTimeStudents() {
        System.out.println("Part-Time Students:");
        for (Student student : students) {
            if (student instanceof PartTimeStudent) {
                System.out.println(student.getName());
            }
        }
    }

    public void printFullTimeExamScores() {
        System.out.println("Full-Time Students Exam Scores:");
        for (Student student : students) {
            if (student instanceof FullTimeStudent ftStudent) {
                System.out.println(ftStudent.getName() + " - Exam1: " + ftStudent.getExam1() + ", Exam2: " + ftStudent.getExam2());
            }
        }
    }
}