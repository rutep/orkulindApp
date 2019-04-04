package Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
public class Stats {

    private String startDate;
    private String endDate;
    private Training[] result;

    @JsonIgnore
    public Stats(List<Entity.dataPoints> dataPoints) {
        this.dataPoints = dataPoints;
    }
    @JsonIgnore
    public List<Entity.dataPoints> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<Entity.dataPoints> dataPoints) {
        this.dataPoints = dataPoints;
    }


    private List<Entity.dataPoints> dataPoints;


    private int userId;
    private Exercise exercise;
    private Training[] trainings;
    private Double totalReps;
    private Double averageReps;

    public Stats(String startDate, String endDate, Training[] result, int userId, Exercise exercise, Training[] trainings, Double totalReps, Double averageReps) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.result = result;
        this.userId = userId;
        this.exercise = exercise;
        this.trainings = trainings;
        this.totalReps = totalReps;
        this.averageReps = averageReps;
    }



    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Training[] getResult() {
        return result;
    }

    public void setResult(Training[] result) {
        this.result = result;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Training[] getTrainings() {
        return trainings;
    }

    public void setTrainings(Training[] trainings) {
        this.trainings = trainings;
    }

    public Double getTotalReps() {
        return totalReps;
    }

    public void setTotalReps(Double totalReps) {
        this.totalReps = totalReps;
    }

    public Double getAverageReps() {
        return averageReps;
    }

    public void setAverageReps(Double averageReps) {
        this.averageReps = averageReps;
    }



    Stats() {

    }

    public Stats(String startDate, String endDate, int userId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
