import java.util.Random;   // Needed for the Random class

/**
 This class simulates rolling a pair of dice 10,000 times
 and counts the number of times doubles of are rolled for
 each different pair of doubles.
 */

public class DiceSimulation_Do   // 类名改为 DiceSimulation_For
{
    public static void main(String[] args)
    {
        final int NUMBER = 10000;  // Number of dice rolls

        // A random number generator used in
        // simulating the rolling of dice
        Random generator = new Random();

        int die1Value;       // Value of the first die
        int die2Value;       // Value of the second die
        int count = 0;       // Total number of dice rolls
        int snakeEyes = 0;   // Number of snake eyes rolls
        int twos = 0;        // Number of double two rolls
        int threes = 0;      // Number of double three rolls
        int fours = 0;       // Number of double four rolls
        int fives = 0;       // Number of double five rolls
        int sixes = 0;       // Number of double six rolls

        // TASK #2 Enter your code for the algorithm here
        for (count = 0; count < NUMBER; count++) {
            // 掷两颗骰子（生成 1-6 的随机数）
            die1Value = generator.nextInt(6) + 1;
            die2Value = generator.nextInt(6) + 1;

            // 判断是否为豹子（两个骰子点数相同）
            if (die1Value == die2Value) {
                // 根据点数累加对应的计数器
                switch (die1Value) {
                    case 1:
                        snakeEyes++;
                        break;
                    case 2:
                        twos++;
                        break;
                    case 3:
                        threes++;
                        break;
                    case 4:
                        fours++;
                        break;
                    case 5:
                        fives++;
                        break;
                    case 6:
                        sixes++;
                        break;
                }
            }
        }

        // Display the results
        System.out.println ("You rolled snake eyes " +
                snakeEyes + " out of " +
                count + " rolls.");
        System.out.println ("You rolled double twos " +
                twos + " out of " + count +
                " rolls.");
        System.out.println ("You rolled double threes " +
                threes + " out of " + count +
                " rolls.");
        System.out.println ("You rolled double fours " +
                fours + " out of " + count +
                " rolls.");
        System.out.println ("You rolled double fives " +
                fives + " out of " + count +
                " rolls.");
        System.out.println ("You rolled double sixes " +
                sixes + " out of " + count +
                " rolls.");
    }
}