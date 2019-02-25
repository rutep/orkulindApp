package Entity;

public class Stats {

    private String monthBefore;
    private String getMonthAfter;
    private Training[] result;

    Stats() {

    }

    Stats(String monthBefore, String getMonthAfter, Training[] result) {
        this.monthBefore = monthBefore;
        this.getMonthAfter = getMonthAfter;
        this.result = result;
    }
}
