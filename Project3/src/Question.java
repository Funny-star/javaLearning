import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Abstract base class for all question types
public abstract class Question {
    // Static fields to track maximum possible scores (for probability calculation)
    public static int maxCoderScore = 0;
    public static int maxGamerScore = 0;

    // Common instance fields
    protected String questionType;
    protected String questionText;
    protected String userAnswer;

    // Abstract methods that subclasses must implement
    public abstract void showQuestion();
    public abstract int[] getScoreDelta(String userAnswer);

    // Getter and Setter
    public String getQuestionText() {
        return questionText;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}