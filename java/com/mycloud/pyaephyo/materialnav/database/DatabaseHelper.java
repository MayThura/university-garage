package com.mycloud.pyaephyo.materialnav.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mycloud.pyaephyo.materialnav.Model.Admission;
import com.mycloud.pyaephyo.materialnav.Model.MajorDetail;
import com.mycloud.pyaephyo.materialnav.Model.Support;
import com.mycloud.pyaephyo.materialnav.Model.UniversityInfo;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBName="group4.sqlite";
    public static final String DBLocatoin= "/data/data/com.mycloud.pyaephyo.materialnav/databases/";
    private static final int DATABASEVERSION = 1;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context){
        super(context,DBName,null,1);
        this.mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void openDatabase() {
        String dbPath=mContext.getDatabasePath(DBName).getPath();
        Log.i("DB Path: ",dbPath);
        if(mDatabase != null && mDatabase.isOpen()){
            return;
        }
        mDatabase=SQLiteDatabase.openDatabase(dbPath,null, SQLiteDatabase.OPEN_READWRITE);
    }


    public void closeDatabase() {
        if(mDatabase!=null){
            mDatabase.close();
        }
    }
    public int getID(String uni_id) {
        int id=0;
        String buildSQL = "university,new String[] {_id, uniId}, uniId +'=?',new String[]{uni_id},null,null,null,null";

        Cursor cursor = mDatabase.rawQuery(buildSQL, null);

        if (cursor.moveToFirst()) {

            id = cursor.getInt(0);
        }
        return id;
    }

    public List<UniversityInfo> getAllUniData(String type) {
        UniversityInfo uniInfo=null;
        List<UniversityInfo> uniInfoList=new ArrayList<>();
        openDatabase();
        Cursor cursor=mDatabase.rawQuery("SELECT * FROM university WHERE uniType='"+type+"'",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            uniInfo=new UniversityInfo(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getBlob(14),cursor.getInt(15),cursor.getInt(16),cursor.getString(17));
            uniInfoList.add(uniInfo);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();;
        return uniInfoList;
    }
    public Cursor getData() {
        openDatabase();
        Cursor cursor=mDatabase.rawQuery("SELECT * from university",null);
        return cursor;
    }
    public Cursor getDataByRegion(String region) {
        openDatabase();
        Cursor cursor=mDatabase.rawQuery("SELECT * from university where uniRegion='"+region+"'",null);
        return cursor;
    }
    public void insertData(String uid) {
        openDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("uni_id", uid);

        mDatabase.insert("favourite",null,contentValues);
        closeDatabase();
    }


    public UniversityInfo getUData(String uni_id) {
	openDatabase();
        String buildSQL="SELECT * FROM university where uniId='"+uni_id+"'";

        Cursor cursor=mDatabase.rawQuery(buildSQL, null);
        cursor.moveToFirst();
        int _id = cursor.getInt(cursor.getColumnIndex("_id"));
        String uniId=cursor.getString(cursor.getColumnIndex("uniId"));
        String uniName = cursor.getString(cursor.getColumnIndex("uniName"));
        String uniLocation=cursor.getString(cursor.getColumnIndex("uniLocation"));
        String uniCost=cursor.getString(cursor.getColumnIndex("uniCost"));
        String uniUniform=cursor.getString(cursor.getColumnIndex("uniUniform"));
        int boyMark=cursor.getInt(cursor.getColumnIndex("boyMark"));
        int girlMark=cursor.getInt(cursor.getColumnIndex("girlMark"));
        String isFavorite=cursor.getString(cursor.getColumnIndex("isFavorite"));
        String uniPhone=cursor.getString(cursor.getColumnIndex("uniPhone"));
        String uniEmail=cursor.getString(cursor.getColumnIndex("uniEmail"));
        String uniType=cursor.getString(cursor.getColumnIndex("uniType"));
        String uniWebsite=cursor.getString(cursor.getColumnIndex("uniWebsite"));
        String uniMark=cursor.getString(cursor.getColumnIndex("uniMark"));
        String uniRegion=cursor.getString(cursor.getColumnIndex("uniRegion"));
        String uniLat=cursor.getString(cursor.getColumnIndex("uniLat"));
        String uniLng=cursor.getString(cursor.getColumnIndex("uniLng"));
        byte[] uniPhoto=cursor.getBlob(cursor.getColumnIndex("uniPhoto"));
        UniversityInfo university=new UniversityInfo(_id, uniId, uniName, uniLocation, uniCost, uniUniform, uniPhone, uniEmail, uniType, uniWebsite, uniMark, uniRegion, uniLat, uniLng, uniPhoto,boyMark,girlMark,isFavorite);
        return university;

    }

    public MajorDetail getMajorData(String uid) {   
	openDatabase();
        String buildSQL="SELECT * FROM majorDetail where uniId='"+uid+"'";

        Cursor cursor=mDatabase.rawQuery(buildSQL, null);
        cursor.moveToFirst();
        int _id = cursor.getInt(cursor.getColumnIndex("_id"));
        String uniId=cursor.getString(cursor.getColumnIndex("uniId"));
        String major=cursor.getString(cursor.getColumnIndex("major"));
        String degree=cursor.getString(cursor.getColumnIndex("degree"));
        String duration=cursor.getString(cursor.getColumnIndex("duration"));
        String description=cursor.getString(cursor.getColumnIndex("description"));
        MajorDetail majorDetail=new MajorDetail(_id,uniId,major,degree,duration,description);
        return majorDetail;
    }


    public Admission getAdmissionData(String uid) {   
	openDatabase();
        String buildSQL="SELECT * FROM admission where uniId='"+uid+"'";

        Cursor cursor=mDatabase.rawQuery(buildSQL, null);
        cursor.moveToFirst();
        int _id = cursor.getInt(cursor.getColumnIndex("_id"));
        String uniId=cursor.getString(cursor.getColumnIndex("uniId"));
        String admissionType=cursor.getString(cursor.getColumnIndex("admissionType"));
        String testDescription=cursor.getString(cursor.getColumnIndex("testDescription"));
        Admission admission=new Admission(_id,uniId,admissionType,testDescription);
        return admission;

    }

    public Support getSupportData(String uid) {   
	openDatabase();
        String buildSQL="SELECT * FROM support where uniId='"+uid+"'";

        Cursor cursor=mDatabase.rawQuery(buildSQL, null);
        cursor.moveToFirst();
        int _id = cursor.getInt(cursor.getColumnIndex("_id"));
        String uniId=cursor.getString(cursor.getColumnIndex("uniId"));
        String hostelSupport=cursor.getString(cursor.getColumnIndex("hostelSupport"));
        String ferrySupport=cursor.getString(cursor.getColumnIndex("ferrySupport"));
        Support support=new Support(_id,uniId,hostelSupport,ferrySupport);
        return support;

    }

    public List<UniversityInfo> getFilterResult(String region,String gender,int min,int max) {
	openDatabase();
        Log.i("Region",region);
        Log.i("Gender",gender);
        String buildSQL;
        if(gender=="boy") {
            buildSQL = "SELECT * FROM university where uniRegion='" + region + "' and boyMark>=" + min + " and boyMark<=" + max;
        }
        else {
           buildSQL = "SELECT * FROM university where uniRegion='" + region + "' and girlMark>=" + min + " and girlMark<=" + max;

        }
        UniversityInfo uniInfo=null;
        List<UniversityInfo> uniInfoList=new ArrayList<>();
        openDatabase();
        Cursor cursor=mDatabase.rawQuery(buildSQL,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            uniInfo=new UniversityInfo(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getBlob(14),cursor.getInt(15),cursor.getInt(16),cursor.getString(17));
            uniInfoList.add(uniInfo);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return uniInfoList;

    }

}
