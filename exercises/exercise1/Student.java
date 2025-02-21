import java.util.*;

// Base class
class Student {
    protected String name;
    protected List<Integer> quizScores;

    public Student(String name) {
        this.name = name;
        this.quizScores = new ArrayList<>();
    }

    public void addQuizScore(int score) {
        if (quizScores.size() < 15) {
            quizScores.add(score);
        }
    }

    public double getAverageQuizScore() {
        return quizScores.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public List<Integer> getQuizScores() {
        return new ArrayList<>(quizScores);
    }
    
    public String getName() {
        return name;
    }
}