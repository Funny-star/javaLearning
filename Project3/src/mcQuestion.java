import java.util.Scanner;

public class mcQuestion extends Question {
    private String[] options;
    private int[] coderWeights;
    private int[] gamerWeights;

    public mcQuestion(String text, String[] opts, int[] cWeights, int[] gWeights) {
        this.questionType = "MC";
        this.questionText = text;
        this.options = opts;
        this.coderWeights = cWeights;
        this.gamerWeights = gWeights;

        // Update static max scores during object creation
        for (int c : cWeights) {
            if (c > 0) Question.maxCoderScore += c;
        }
        for (int g : gWeights) {
            if (g > 0) Question.maxGamerScore += g;
        }
    }

    @Override
    public void showQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println(" " + (i + 1) + ". " + options[i]);
        }
        System.out.print("Your choice: ");
    }

    @Override
    public int[] getScoreDelta(String userAnswer) {
        int[] delta = {0, 0};

        // 只进行字符串匹配，移除了数字解析逻辑
        for (int i = 0; i < options.length; i++) {
            if (options[i].trim().equalsIgnoreCase(userAnswer.trim())) {
                delta[0] = coderWeights[i];
                delta[1] = gamerWeights[i];
                this.userAnswer = options[i];
                return delta;
            }
        }

        // 如果没有找到匹配项，返回 0 分
        // 错误提示和重试逻辑由 Project3 处理
        return delta;
    }
}