package Entity;

public class User {

    private int id;
    private String username;
    private String password;
    private String errorMsg;

    public User() {

    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password, String errorMsg) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.errorMsg = errorMsg;
    }
}
