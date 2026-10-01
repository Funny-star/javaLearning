import java.util.Scanner;
import java.io.*;

public class Project2 {
    public static void main() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] questionTexts = new String[5];
        String[][] allAnswers = new String[5][3];
        int[][] allCoderWeights = new int[5][3];
        int[][] allGamerWeights = new int[5][3];

        File file = new File("question.txt");
        Scanner fileScanner = new Scanner(file);

        for (int i = 0; i < 5; i++) {
            // Read question
            questionTexts[i] = fileScanner.nextLine();

            // Read answers
            String answerLine = fileScanner.nextLine();
            allAnswers[i] = answerLine.split(",");

            // Read coder weights
            String coderLine = fileScanner.nextLine();
            String[] coderStr = coderLine.split(",");
            for (int j = 0; j < 3; j++) {
                allCoderWeights[i][j] = Integer.parseInt(coderStr[j]);
            }

            // Read gamer weights
            String gamerLine = fileScanner.nextLine();
            String[] gamerStr = gamerLine.split(",");
            for (int j = 0; j < 3; j++) {
                allGamerWeights[i][j] = Integer.parseInt(gamerStr[j]);
            }

            // Skip the separator line (if not the last group)
            if (i < 4) {
                fileScanner.nextLine();
            }
        }
        fileScanner.close();

//        // Prepare data
//        String[] questionTexts = {
//                "What do you prefer to do on weekends?",
//                "When a program crashes or game freezes, what is your first reaction?",
//                "What kind of sense of achievement do you enjoy more?",
//                "What is your desk / room usually like?",
//                "If you were given $1000 how would you spend it?"
//        };
//
//        String[][] allAnswers = {
//                {"Write code", "Play games", "Go out and socialize"},
//                {"Check error logs", "Restart the game", "Search online for solutions"},
//                {"Solving a complex bug", "Winning a tough match", "Completing a project"},
//                {"Neat and organized", "Covered in code", "Game merchandise"},
//                {"Buy courses", "Buy games", "Save it"}
//        };
//
//        int[][] allCoderWeights = {
//                {40, 0, 10},
//                {50, 0, 20},
//                {45, 0, 25},
//                {10, 35, 0},
//                {40, 0, 10}
//        };
//
//        int[][] allGamerWeights = {
//                {0, 40, 10},
//                {0, 30, 20},
//                {0, 45, 25},
//                {10, 0, 35},
//                {0, 40, 10}
//        };
//
        // Create Question object array
        Question[] questions = new Question[5];
        for (int i = 0; i < questions.length; i++) {
            questions[i] = new Question(
                    questionTexts[i],
                    allAnswers[i],
                    allCoderWeights[i],
                    allGamerWeights[i]
            );
        }


        // welcome message
        System.out.println("Welcome to \"Coder or Gamer – Object Edition\"!");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name + "! Let's find out which side you belong to.\n");

        // Create user object
        User user = new User(name);

        // Ask questions
        for (Question question : questions) {
            question.display();
            // Get answers and evaluate

            int[] weights = question.evaluate();
            user.updateScores(weights[0], weights[1]);

        }
        String result = user.toString();
        System.out.println(result);
        user.getConfidence();
        user.calculateFinalResult();
    }
}