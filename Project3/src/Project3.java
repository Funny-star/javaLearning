import java.util.*;
import java.io.*;

public class Project3 {
    public static void main(String[] args) throws Exception {
        // --- Initialization ---
        ArrayList<Question> questionsList = new ArrayList<>();
        ArrayList<Results> resultsList = new ArrayList<>();
        Scanner consoleScanner = new Scanner(System.in);

        // --- 1. Load Questions from File ---
        File file = new File("question.txt");
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String type = fileScanner.nextLine().trim();
            if (type.isEmpty()) continue;
            String questionText = fileScanner.nextLine().trim();

            if (type.equals("MC")) {
                ArrayList<String> opts = new ArrayList<>();
                ArrayList<Integer> cWeights = new ArrayList<>();
                ArrayList<Integer> gWeights = new ArrayList<>();

                while (fileScanner.hasNextLine()) {
                    String nextLine = fileScanner.nextLine();
                    if (nextLine.trim().isEmpty() || nextLine.trim().startsWith("TF") || nextLine.trim().startsWith("NUM")) {
                        break;
                    }
                    String[] parts = nextLine.split(",");
                    if (parts.length == 3) {
                        opts.add(parts[0]);
                        cWeights.add(Integer.parseInt(parts[1]));
                        gWeights.add(Integer.parseInt(parts[2]));
                    }
                }
                String[] options = opts.toArray(new String[0]);
                int[] cWeightsArr = cWeights.stream().mapToInt(i -> i).toArray();
                int[] gWeightsArr = gWeights.stream().mapToInt(i -> i).toArray();
                questionsList.add(new mcQuestion(questionText, options, cWeightsArr, gWeightsArr));
            }
            else if (type.equals("TF")) {
                String trueLine = fileScanner.nextLine().trim();
                String falseLine = fileScanner.nextLine().trim();
                String[] tVals = trueLine.split(",");
                String[] fVals = falseLine.split(",");
                int ct = Integer.parseInt(tVals[0]);
                int gt = Integer.parseInt(tVals[1]);
                int cf = Integer.parseInt(fVals[0]);
                int gf = Integer.parseInt(fVals[1]);
                questionsList.add(new tfQuestion(questionText, ct, gt, cf, gf));
            }
            else if (type.equals("NUM")) {
                ArrayList<int[]> ranges = new ArrayList<>();
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine().trim();
                    if (line.isEmpty()) break;
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        int[] range = { Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]) };
                        ranges.add(range);
                    }
                }
                int[][] rangesArr = ranges.toArray(new int[0][]);
                questionsList.add(new numQuestion(questionText, rangesArr));
            }
        }
        fileScanner.close();
        System.out.println("Welcome to Coder or Gamer – Advanced OOP Edition!\n");

        // --- 2. Main Loop (Multiple Users/Sessions) ---
        boolean newSession = true;
        while (newSession) {
            System.out.print("Enter your name (or 'quit' to exit): ");
            String name = consoleScanner.nextLine();
            if (name.equalsIgnoreCase("quit")) break;

            User currentUser = new User(name);



                int sessionCoderScore = 0;
                int sessionGamerScore = 0;

                // Shuffle questions
                Collections.shuffle(questionsList);
                Question[] questionsArray = questionsList.toArray(new Question[0]);

                System.out.println("\n--- New Session ---");

                // 遍历问题数组
                for (Question q : questionsArray) {
                    int[] delta = {0, 0};
                    q.showQuestion();

                    // 区分题型处理
                    if (q instanceof numQuestion) {
                        // 数字题保持原样：读取一次，无效则跳过
                        String answer = consoleScanner.nextLine();
                        delta = q.getScoreDelta(answer);
                        sessionCoderScore += delta[0];
                        sessionGamerScore += delta[1];
                    } else {
                        // MC 和 TF 题型：强制有效输入
                        while (delta[0] == 0 && delta[1] == 0) {
                            String answer = consoleScanner.nextLine();
                            delta = q.getScoreDelta(answer);

                            if (delta[0] == 0 && delta[1] == 0) {
                                // 输入无效，提示重新输入
                                System.out.print("Invalid input. Please type the exact option text (e.g., 'Write code', 'True'): ");
                            } else {
                                // 输入有效，累加分数
                                sessionCoderScore += delta[0];
                                sessionGamerScore += delta[1];
                            }
                        }
                    }
                }

                // Get Confidence
                System.out.print("\nHow confident are you (0-100)? ");
                int confidence = Integer.parseInt(consoleScanner.nextLine());

                // Calculate Probability
                double cProb = Question.maxCoderScore > 0 ? ((double) sessionCoderScore / Question.maxCoderScore) * 100 : 0;
                double gProb = Question.maxGamerScore > 0 ? ((double) sessionGamerScore / Question.maxGamerScore) * 100 : 0;

                // Add randomness based on confidence
                Random rand = new Random();
                double uncertainty = 100 - confidence;
                double fluctuation = uncertainty * 0.5;
                cProb += (rand.nextDouble() * 2 * fluctuation) - fluctuation;
                gProb += (rand.nextDouble() * 2 * fluctuation) - fluctuation;

                // Clamp values
                cProb = Math.max(0, Math.min(100, cProb));
                gProb = Math.max(0, Math.min(100, gProb));

                // Determine Conclusion
                String conclusion;
                if (cProb > gProb + 5) conclusion = "Definitely a CODER";
                else if (gProb > cProb + 5) conclusion = "Definitely a GAMER";
                else conclusion = "A balanced hybrid";

                // Display Result
                System.out.printf("\nResult: %.1f%% Coder, %.1f%% Gamer\n", cProb, gProb);
                System.out.println("Verdict: " + conclusion + "\n");

                // Save Result
                Results result = new Results(currentUser, cProb, gProb, confidence, conclusion);
                resultsList.add(result);

                // Ask to repeat
                System.out.print("Play again (same user)? (Y/N): ");
                String replay = consoleScanner.nextLine();
                if (replay.equalsIgnoreCase("N")) {
                    newSession = false;
                }
            }


        // --- 3. Save Results to File ---
        PrintWriter writer = new PrintWriter(new FileWriter("Results.txt"));
        for (Results res : resultsList) {
            writer.println(res.toString());
        }
        writer.close();
        System.out.println("\nResults saved to Results.txt");
        consoleScanner.close();
    }

    // Fisher-Yates Shuffle (备用)
    private static void shuffleArray(Question[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            Question temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}