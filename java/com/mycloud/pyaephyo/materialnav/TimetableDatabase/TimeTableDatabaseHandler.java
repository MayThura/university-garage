package com.mycloud.pyaephyo.materialnav.TimetableDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mycloud.pyaephyo.materialnav.Model.TimeTablePeriodModel;
import com.mycloud.pyaephyo.materialnav.Model.TimetableRowColHolder;

import java.util.ArrayList;

public class TimeTableDatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="timetableDB";
    public static final int DATABASE__VERSION=1;
    public static final String TABLE_NAME="timetable";
    public static final String ID="id";
    public static final String SUBJECT="subject";
    public static final String DAY="day";
    public static final String PERIOD="period";

    public TimeTableDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE__VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+ID+"INTEGER PRIMARY KEY," +SUBJECT+" TEXT,"+DAY+" TEXT,"+PERIOD+" INTEGER)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);

    }

    public TimeTablePeriodModel[] getAll(){
        String selectQuery="SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery(selectQuery,null);

        TimetableRowColHolder holder = TimetableRowColHolder.getInstance();


        ArrayList<TimeTablePeriodModel> periodArray= new ArrayList<TimeTablePeriodModel>();

          if (cursor.moveToFirst()) {

              do {

                  periodArray.add(new TimeTablePeriodModel(cursor.getString(1), cursor.getString(2), cursor.getInt(3)));

              }while (cursor.moveToNext());



          }

        for(int z=0; z<periodArray.size(); z++) {
            Log.i("FROM DB, GETTING OBJ", periodArray.get(z).toString());
        }

        return periodArray.toArray(new TimeTablePeriodModel[periodArray.size()]);
    }

    public void insertPeriods(TimeTablePeriodModel[] periodModels){

        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();

        for(int i=0; i<periodModels.length; i++){
            values.put(SUBJECT,periodModels[i].getSubject());
            values.put(DAY,periodModels[i].getDay());
            values.put(PERIOD,periodModels[i].getPeriod());
            db.insert(TABLE_NAME,null,values);

            Log.i("In Database", periodModels[i].toString());

        }

        db.close();

    }


    public void updatePeriods(TimeTablePeriodModel[] periodModels){

        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();

        db.execSQL("DELETE FROM "+TABLE_NAME);
        for(int i=0; i<periodModels.length; i++){
            values.put(SUBJECT,periodModels[i].getSubject());
            values.put(DAY,periodModels[i].getDay());
            values.put(PERIOD,periodModels[i].getPeriod());
            db.insert(TABLE_NAME,null,values);

            Log.i("In Database", periodModels[i].toString());

        }

        db.close();

    }


}
