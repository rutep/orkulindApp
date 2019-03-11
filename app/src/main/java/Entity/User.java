package Entity;

import java.io.Serializable;

public class User implements Serializable {

    public static User user;

    private int id;
    private String name;
    private String password;
    private String error;

    public User () {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(int id, String name, String password, String error) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.error = error;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getError() { return this.error; }

    public void setError(String error) { this.error = error; }
}
