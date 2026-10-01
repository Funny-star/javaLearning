public class Results {
    User user;
    double coderProbability;
    double gamerProbability;
    int confidence;
    String conclusion;

    public Results(User user, double cProb, double gProb, int conf, String concl) {
        this.user = user;
        this.coderProbability = cProb;
        this.gamerProbability = gProb;
        this.confidence = conf;
        this.conclusion = concl;
    }

    @Override
    public String toString() {
        return "User: " + user.getUserName() +
                " | Coder: " + String.format("%.1f", coderProbability) + "%" +
                " | Gamer: " + String.format("%.1f", gamerProbability) + "%" +
                " | Confidence: " + confidence +
                " | Verdict: " + conclusion;
    }
}