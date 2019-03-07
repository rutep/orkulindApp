package Entity;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String name;
    private String password;
    private String errorMsg;

    public User () {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(int id, String name, String password, String errorMsg) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.errorMsg = errorMsg;
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

    public String getErrorMsg() { return this.errorMsg; }

    public void setErrorMsg(String errorMsg) { this.errorMsg = errorMsg; }
}
