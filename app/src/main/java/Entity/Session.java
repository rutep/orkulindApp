package Entity;

import java.io.Serializable;
import java.util.List;

public class Session implements Serializable {

    private int id;
    private String name;
    private String type;
    private int userID;
    private List<Exercise> exercises;

    public Session() {

    }

    public Session(int id, String name, String type, int userID, List<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.userID = userID;
        this.exercises = exercises;
    }

    public String toString() {
        return this.name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }


}
