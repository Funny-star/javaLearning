import java.util.Objects;
import java.util.Scanner;

public class Question {
    Scanner scanner = new Scanner(System.in);
    // Attributes
    private final String text;
    private final String[] answers;
    private final int[] coderWeights;
    private final int[] gamerWeights;

    // Constructor
    public Question(String text, String[] answers, int[] coderWeights, int[] gamerWeights) {
        this.text = text;
        this.answers = answers;
        this.coderWeights = coderWeights;
        this.gamerWeights = gamerWeights;
    }

    // Display question
    public void display() {
        System.out.println(this.text);
        for (int i = 0; i < this.answers.length; i++) {
            System.out.println("  " + (i + 1) + ". " + this.answers[i]);
        }
    }


    // Evaluate answer and returns {coderWeight, gamerWeight}
    public int[] evaluate() {
        String answer = scanner.nextLine();
        int[] weights = new int[2];
        boolean isValid = false;// if exist this answer
        for (int i = 0; i < this.answers.length; i++) {
            if (Objects.equals(answer, this.answers[i])) {
                weights[0] = this.coderWeights[i];
                weights[1] = this.gamerWeights[i];
                isValid = true;
            }
        }
        if (!isValid) {
            System.out.println("This answer does not exist");
            System.out.print("Please answer again: ");
            return this.evaluate();
        }
        return weights;
    }

}