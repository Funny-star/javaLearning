public class SwitchDemo {
    public static void main(String[] args) {
        int month = 8;
        int year = 2024;
        int numDays = 0;

        // 第 19 行左右：定义变量 two
        int two = 2;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numDays = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numDays = 30;
                break;
            case 2:  // 第 30 行左右：这里是 case 2
                if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)) {
                    numDays = 29;
                } else {
                    numDays = 28;
                }
                break;
            default:
                System.out.println("Invalid month.");
                break;
        }
        System.out.println("Number of days = " + numDays);
    }
}
