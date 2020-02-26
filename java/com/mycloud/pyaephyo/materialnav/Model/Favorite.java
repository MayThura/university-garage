package com.mycloud.pyaephyo.materialnav.Model;

/**
 * Created by BarbieStyles on 10/27/2016.
 */

public class Favorite
{
    int _id;
    String uni_id;

    public Favorite(int _id, String uni_id) {
        this._id = _id;
        this.uni_id = uni_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUni_id() {
        return uni_id;
    }

    public void setUni_id(String uni_id) {
        this.uni_id = uni_id;
    }
}
