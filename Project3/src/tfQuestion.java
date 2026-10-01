public class tfQuestion extends mcQuestion {
    // Constructor: Hardcodes True/False options
    public tfQuestion(String text, int coderTrue, int gamerTrue, int coderFalse, int gamerFalse) {
        super(text,
                new String[]{"True", "False"},
                new int[]{coderTrue, coderFalse},
                new int[]{gamerTrue, gamerFalse});
        this.questionType = "TF";
    }
}