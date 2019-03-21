package Entity;
import java.util.Date;

public class Training {

    private int id;
    private Date date;
    private Session session;
    private Exercise exercise;
    private int reps;

    public Training() {

    }

    public Training(int id, Date date, Session session, Exercise exercise, int reps) {
        this.id = id;
        this.date = date;
        this.session = session;
        this.exercise = exercise;
        this.reps = reps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
