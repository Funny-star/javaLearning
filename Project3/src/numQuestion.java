import java.util.Scanner;

public class numQuestion extends Question {
    // 2D array to store ranges: {lower, upper, coderWeight, gamerWeight}
    private int[][] ranges;

    public numQuestion(String text, int[][] rangeData) {
        this.questionType = "NUM";
        this.questionText = text;
        this.ranges = rangeData;

        // Update static max scores
        for (int[] range : rangeData) {
            if (range[2] > 0) Question.maxCoderScore += range[2];
            if (range[3] > 0) Question.maxGamerScore += range[3];
        }
    }

    @Override
    public void showQuestion() {
        System.out.println(questionText);
        System.out.print("Enter number: ");
    }

    @Override
    public int[] getScoreDelta(String userAnswer) {
        int[] delta = {0, 0};
        try {
            int input = Integer.parseInt(userAnswer.trim());
            for (int[] range : ranges) {
                if (input >= range[0] && input <= range[1]) {
                    delta[0] = range[2];
                    delta[1] = range[3];
                    this.userAnswer = String.valueOf(input);
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. No points awarded.");
        }
        return delta;
    }
}