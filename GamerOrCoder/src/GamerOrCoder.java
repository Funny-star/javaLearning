import java.util.Scanner;
//import random
import java.util.Random;

public class GamerOrCoder {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        boolean playAgain = true;//the factor to decided if try again

        while (playAgain) {
            // Welcome message
            System.out.println("Welcome to \"Coder or Gamer?\"");
            System.out.println("Let's find out which one you are!");
            int coderScore = 0;
            int gamerScore = 0;
            boolean validInput = false;
            String answer;

            // Question 1
            while (!validInput) {
                System.out.println("1. What do you prefer to do on weekends?");
                System.out.println("Options: Write code / Play games / Go out and socialize");
                System.out.print("Your answer: ");
                answer = input.nextLine();

                switch (answer) {
                    case "Write code" -> {
                        coderScore = coderScore + 40;
                        validInput = true;
                    }
                    case "Play games" -> {
                        gamerScore = gamerScore + 40;
                        validInput = true;
                    }
                    case "Go out and socialize" -> {
                        coderScore = coderScore + 10;
                        gamerScore = gamerScore + 10;
                        validInput = true;
                    }
                    default -> System.out.println("Invalid input! Please enter exactly one of the options shown.");
                }
            }
            validInput = false;

            // Question 2
            while (!validInput) {
                System.out.println("2. When a program crashes / game freezes, what is your first reaction?");
                System.out.println("Options: Check error logs / Restart the game / Search online for solutions");
                System.out.print("Your answer: ");
                answer = input.nextLine();

                switch (answer) {
                    case "Check error logs" -> {
                        coderScore = coderScore + 50;
                        validInput = true;
                    }
                    case "Restart the game" -> {
                        gamerScore = gamerScore + 30;
                        validInput = true;
                    }
                    case "Search online for solutions" -> {
                        coderScore = coderScore + 20;
                        gamerScore = gamerScore + 20;
                        validInput = true;
                    }
                    default -> System.out.println("Invalid input! Please enter exactly one of the options shown.");
                }
            }
            validInput = false;

            // Question 3
            while (!validInput) {
                System.out.println("3. What kind of sense of achievement do you enjoy more?");
                System.out.println("Options: Solving a complex bug / Winning a tough match / Completing a project");
                System.out.print("Your answer: ");
                answer = input.nextLine();

                switch (answer) {
                    case "Solving a complex bug" -> {
                        coderScore = coderScore + 45;
                        validInput = true;
                    }
                    case "Winning a tough match" -> {
                        gamerScore = gamerScore + 45;
                        validInput = true;
                    }
                    case "Completing a project" -> {
                        coderScore = coderScore + 25;
                        gamerScore = gamerScore + 20;
                        validInput = true;
                    }
                    default -> System.out.println("Invalid input! Please enter exactly one of the options shown.");
                }
            }
            validInput = false;

            // Question 4
            while (!validInput) {
                System.out.println("4. What is your desk / room usually like?");
                System.out.println("Options: Neat and organized / Covered in code / Game merchandise");
                System.out.print("Your answer: ");
                answer = input.nextLine();

                switch (answer) {
                    case "Neat and organized" -> {
                        coderScore = coderScore + 30;
                        gamerScore = gamerScore + 30;
                        validInput = true;
                    }
                    case "Covered in code" -> {
                        coderScore = coderScore + 35;
                        validInput = true;
                    }
                    case "Game merchandise" -> {
                        gamerScore = gamerScore + 35;
                        validInput = true;
                    }
                    default -> System.out.println("Invalid input! Please enter exactly one of the options shown.");
                }
            }
            validInput = false;

            // Question 5
            while (!validInput) {
                System.out.println("5.If you were given $1000, how would you spend it?");
                System.out.println("Options: Buy courses / Buy games / Save it");
                System.out.print("Your answer: ");
                answer = input.nextLine();

                switch (answer) {
                    case "Buy courses" -> {
                        coderScore = coderScore + 40;
                        validInput = true;
                    }
                    case "Buy games" -> {
                        gamerScore = gamerScore + 40;
                        validInput = true;
                    }
                    case "Save it" -> {
                        coderScore = coderScore + 20;
                        gamerScore = gamerScore + 20;
                        validInput = true;
                    }
                    default -> System.out.println("Invalid input! Please enter exactly one of the options shown.");
                }
            }
            validInput = false;

            // Display raw scores
            System.out.println("Raw Scores:");
            System.out.println("Coder Score: " + coderScore);
            System.out.println("Gamer Score: " + gamerScore);
            System.out.println("Maximum Possible Score: Coder 210, Gamer 190");

            // Get user confidence - with input validation
            int confidence = 0;
            while (true) {
                System.out.println("\nHow confident are you in your answers (0-100)?");
                System.out.print("Your confidence: ");
                if (input.hasNextInt()) {
                    confidence = input.nextInt();
                    if (confidence >= 0 && confidence <= 100) {
                        break;
                    } else {
                        System.out.println("Invalid input! Please enter a number between 0 and 100.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                }
            }

            int maxCoderScore = 210;
            int maxGamerScore = 190;

            double coderProbability = ((double) coderScore / maxCoderScore) * 100;
            double gamerProbability = ((double) gamerScore / maxGamerScore) * 100;

            // Add random factor
            int uncertainty = 100 - confidence;
            double maxFluctuation = uncertainty / 2.0;
            double randomFactor = (random.nextDouble() * 2 * maxFluctuation) - maxFluctuation;
            coderProbability = coderProbability + randomFactor;
            randomFactor = (random.nextDouble() * 2 * maxFluctuation) - maxFluctuation;
            gamerProbability = gamerProbability + randomFactor;

            // Ensure probabilities are in 0-100
            if (coderProbability < 0) {
                coderProbability = 0;
            }
            if (coderProbability > 100) {
                coderProbability = 100;
            }
            if (gamerProbability < 0) {
                gamerProbability = 0;
            }
            if (gamerProbability > 100) {
                gamerProbability = 100;
            }

            // Display result
            System.out.println("Calculating with randomness factor...");
            System.out.printf("You are %.1f%% coder and %.1f%% gamer", coderProbability, gamerProbability);
            System.out.println();

            // Classification decision
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

            // Ask to replay
            String replayChoice = "";
            while (true) {
                System.out.print("Try again? (Y/N): ");
                replayChoice = input.nextLine();
                if (replayChoice.equalsIgnoreCase("Y") || replayChoice.equalsIgnoreCase("N")) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter Y or N.");
                }
            }

            if (replayChoice.equalsIgnoreCase("N")) {
                playAgain = false;
                System.out.println("\nThank you for playing! Goodbye!");
            }
        }

        input.close();
    }
}