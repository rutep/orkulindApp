package Entity;

import java.util.ArrayList;
import java.util.List;

public class dataPoints {

    public List<dataPoints> getDataPoints() {
        return dataPoints;
    }

    public dataPoints(ArrayList<dataPoints> dataPoints, int x, int y) {
        this.dataPoints = dataPoints;
        this.x = x;
        this.y = y;
    }

    public void setDataPoints(ArrayList<dataPoints> dataPoints) {
        this.dataPoints = dataPoints;
    }

    private ArrayList<dataPoints> dataPoints;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public dataPoints(int x, int y) {
        this.y = y;
        this.x = x;
    }

    private int x;
    private int y;
}
