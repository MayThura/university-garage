package com.mycloud.pyaephyo.materialnav.Model;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by BarbieStyles on 10/28/2016.
 */

public class MajorDetail extends AppCompatActivity
{
    int _id;
    String uniId, major, degree, duration, description;

    public MajorDetail(int _id, String uniId, String major, String degree, String duration, String description) {
        this._id = _id;
        this.uniId = uniId;
        this.major = major;
        this.degree = degree;
        this.duration = duration;
        this.description = description;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUniId() {
        return uniId;
    }

    public void setUniId(String uniId) {
        this.uniId = uniId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
