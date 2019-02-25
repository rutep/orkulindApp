package Entity;
import java.util.Date;

public class Training {

    private int id;
    private Date date;
    private Session session;

    Training() {

    }

    Training(int id, Date date, Session session) {
        this.id = id;
        this.date = date;
        this.session = session;
    }

}
