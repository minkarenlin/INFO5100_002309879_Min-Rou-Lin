import java.util.Random;

public class Main {
     public static void main(String[] args) {
        Session session = new Session();
        Random rand = new Random();

        // Populate session with students
        for (int i = 1; i <= 10; i++) {
            FullTimeStudent ftStudent = new FullTimeStudent("FT_Student_" + i, rand.nextInt(101), rand.nextInt(101));
            for (int j = 0; j < 15; j++) {
                ftStudent.addQuizScore(rand.nextInt(101));
            }
            session.addStudent(ftStudent);
        }
        for (int i = 1; i <= 10; i++) {
            PartTimeStudent ptStudent = new PartTimeStudent("PT_Student_" + i);
            for (int j = 0; j < 15; j++) {
                ptStudent.addQuizScore(rand.nextInt(101));
            }
            session.addStudent(ptStudent);
        }

        System.out.println("Average Quiz Score: " + session.getAverageQuizScorePerStudent());
        System.out.println("--------------------------------------------------------------");
        session.printSortedQuizScores();
        System.out.println("--------------------------------------------------------------");
        session.printPartTimeStudents();
        System.out.println("--------------------------------------------------------------");
        session.printFullTimeExamScores();
    }
}
