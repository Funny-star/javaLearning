public class User {
    public static int totalUsers = 0;
    private String userName;

    public User(String name) {
        this.userName = name;
        totalUsers++;
    }

    public String getUserName() {
        return userName;
    }
}