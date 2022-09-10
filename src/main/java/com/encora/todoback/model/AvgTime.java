package com.encora.todoback.model;

public class AvgTime {
    private Integer timeForAll;
    private Integer timeForLow;
    private Integer timeForMedium;
    private Integer timeForHigh;

    public AvgTime() {
    }

    public AvgTime(Integer timeForAll, Integer timeForLow, Integer timeForMedium, Integer timeForHigh) {
        this.timeForAll = timeForAll;
        this.timeForLow = timeForLow;
        this.timeForMedium = timeForMedium;
        this.timeForHigh = timeForHigh;
    }

    public Integer getTimeForAll() {
        return timeForAll;
    }

    public void setTimeForAll(Integer timeForAll) {
        this.timeForAll = timeForAll;
    }

    public Integer getTimeForLow() {
        return timeForLow;
    }

    public void setTimeForLow(Integer timeForLow) {
        this.timeForLow = timeForLow;
    }

    public Integer getTimeForMedium() {
        return timeForMedium;
    }

    public void setTimeForMedium(Integer timeForMedium) {
        this.timeForMedium = timeForMedium;
    }

    public Integer getTimeForHigh() {
        return timeForHigh;
    }

    public void setTimeForHigh(Integer timeForHigh) {
        this.timeForHigh = timeForHigh;
    }
}
