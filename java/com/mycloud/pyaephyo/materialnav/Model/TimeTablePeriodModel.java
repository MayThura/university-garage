package com.mycloud.pyaephyo.materialnav.Model;

/**
 * Created by Lullaby on 27-Oct-16.
 */

public class TimeTablePeriodModel {

    String subject;
    String day;
    int period;

    public TimeTablePeriodModel() {

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public TimeTablePeriodModel(String subject, String day, int period) {

        this.subject = subject;
        this.day = day;
        this.period = period;
    }

    public String toString(){
        return "Subject:" + this.subject + ", Period:" + this.period + ", Day:" + this.day;
    }

}
