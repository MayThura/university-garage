package com.mycloud.pyaephyo.materialnav.Model;

/**
 * Created by BarbieStyles on 10/28/2016.
 */

public class Support
{
    int _id;
    String uniId, hostelSupport, ferrySupport;

    public Support(int _id, String uniId, String hostelSupport, String ferrySupport) {
        this._id = _id;
        this.uniId = uniId;
        this.hostelSupport = hostelSupport;
        this.ferrySupport = ferrySupport;
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

    public String getHostelSupport() {
        return hostelSupport;
    }

    public void setHostelSupport(String hostelSupport) {
        this.hostelSupport = hostelSupport;
    }

    public String getFerrySupport() {
        return ferrySupport;
    }

    public void setFerrySupport(String ferrySupport) {
        this.ferrySupport = ferrySupport;
    }
}
