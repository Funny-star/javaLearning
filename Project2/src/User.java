import java.util.Scanner;
import java.util.Random;

public class User {
    Scanner scanner = new Scanner(System.in);
    // Attributes
    private final String name;
    private int coderScore;
    private int gamerScore;
    private int confidence;

    // Constructor
    public User(String name) {
        this.name = name;
        this.coderScore = 0;
        this.gamerScore = 0;
    }

    // Update scores
    public void updateScores(int coderDelta, int gamerDelta) {
        this.coderScore += coderDelta;
        this.gamerScore += gamerDelta;
    }

    // toString method
    public String toString() {
        return "User: " + this.name + " | Coder Score: " + this.coderScore + " | Gamer Score: " + this.gamerScore;
    }

    // Confidence level
    public void getConfidence(){
        while (true) {
            System.out.println("\nHow confident are you in your answers (0-100)?");
            System.out.print("Your confidence: ");
            if (scanner.hasNextInt()) {
                confidence = scanner.nextInt();
                if (confidence >= 0 && confidence <= 100) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a number between 0 and 100.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
            }

        }
    }

    // Calculate final result
    public void calculateFinalResult() {
        int maxCoderScore = 210;
        int maxGamerScore = 190;
        Random random = new Random();

        double coderProbability = ((double) coderScore / maxCoderScore) * 100;
        double gamerProbability = ((double) gamerScore / maxGamerScore) * 100;

        // Add randomness factor based on confidence
        int uncertainty = 100 - confidence;
        double maxFluctuation = uncertainty / 2.0;
        double randomFactor = (random.nextDouble() * 2 * maxFluctuation) - maxFluctuation;
        coderProbability = coderProbability + randomFactor;
        randomFactor = (random.nextDouble() * 2 * maxFluctuation) - maxFluctuation;
        gamerProbability = gamerProbability + randomFactor;

        // Ensure probability is within 0-100 range
        coderProbability = Math.max(0, Math.min(100, coderProbability));
        gamerProbability = Math.max(0, Math.min(100, gamerProbability));

        // Display result
        System.out.println("Calculating with randomness factor...");
        System.out.printf("You are %.1f%% coder and %.1f%% gamer\n", coderProbability, gamerProbability);

        // Classification
        double difference = coderProbability - gamerProbability;
        if (difference > 5) {
            System.out.println("Looks like you're more of a problem-solver than a player!");
            System.out.println("You are definitely a CODER!");
        } else if (difference < -5) {
            System.out.println("Looks like you enjoy gaming and competition!");
            System.out.println("You are definitely a GAMER!");
        } else {
            System.out.println("You have qualities of both!");
            System.out.println("Ambiguous — maybe both a coder and a gamer!");
        }
    }
}