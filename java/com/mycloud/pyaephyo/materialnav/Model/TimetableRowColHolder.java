package com.mycloud.pyaephyo.materialnav.Model;

/**
 * Created by Lullaby on 28-Oct-16.
 */

public class TimetableRowColHolder {

    private static TimetableRowColHolder instance;

    private int row, col;

    private TimetableRowColHolder() {
        row=0; col=0;
    }

    public static TimetableRowColHolder getInstance() {
        if(TimetableRowColHolder.instance == null) {
            instance = new TimetableRowColHolder();
        }
        return instance;
    }

    public void setRow(int r) {
        instance.row = r;
    }

    public void setCol(int c) {
        instance.col = c;
    }

    public int getRow() {
        return instance.row;
    }

    public int getCol () {
        return instance.col;
    }

}
