package com.acdq.performance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TimeRun {
    private long startTime;
    private long stopTime;
    private ArrayList<Long> listOfTime = new ArrayList<Long>();

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }

    private long getElapsedTime() {
        return this.stopTime - this.startTime;
    }

    public void add() {
        this.listOfTime.add(this.getElapsedTime());
        this.startTime = 0;
        this.stopTime = 0;
    }

    public String getTotal() {
        long res = 0;
        for (Long i : listOfTime) {
            res += i;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("mm'ms':ss's'.S'ms'");
        return sdf.format(res);
    }

    public double getAverage() {
        long res = 0;
        for (Long i : listOfTime) {
            res += i;
        }
        return res / listOfTime.size();
    }

    public double getStandardDeviation() {
        return Math.sqrt(variance());
    }

    private double variance() {
        double m = getAverage();
        double v = 0;
        for (int i = 0; i < listOfTime.size(); i++) {
            v += Math.pow(listOfTime.get(i) - m, 2);
        }
        return v / listOfTime.size();
    }

    public long getListOfTime() {
        long res = 0;
        for (Long i : listOfTime) {
            res += i;
        }
        return res;
    }
}