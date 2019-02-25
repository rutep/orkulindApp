package Entity;

public class Session {

    private int id;
    private String name;
    private String type;
    private Exercise[] list;

    public Session() {

    }

    public Session(int id, String name, String type, Exercise[] list) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.list = list;
    }
}
