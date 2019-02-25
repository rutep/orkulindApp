package Entity;

public class Exercise {

    private int id;
    private  String name;
    private String type;
    private int reps;
    private String repType;
    private String info;
    private String videoLink;

    public Exercise() {

    }

    public Exercise(int id, String name, String type,
                    int reps, String repType, String info, String videoLink) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.reps = reps;
        this.repType = repType;
        this.info = info;
        this.videoLink = videoLink;
    }
}
