package com.mycloud.pyaephyo.materialnav.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import com.mycloud.pyaephyo.materialnav.Model.Favorite;

import java.io.ByteArrayOutputStream;

public class MyDatabase {

    private static final int DATABASEVERSION = 1;
    public static final String DBName="universityInformationDb.sqlite";

    private static  final String TABLE_NAME = "favourite";
    private static final String TABLE_COLUMN_ID = "_id";
    private static final String TABLE_COLUMN_UID = "uniId";
    private static final String KEY_NAME= "uniName";
    private static final String KEY_PHOTO="uniPhoto";

    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;


    public MyDatabase(Context aContext) {
        openHelper = new DatabaseOpenHelper(aContext);
        database = openHelper.getWritableDatabase();
    }


    public Favorite getFavoriteData(int id) {
        String buildSQL="SELECT * FROM "+TABLE_NAME;

        Cursor cursor=database.rawQuery(buildSQL, null);

        if(cursor.moveToPosition(id)) {

            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String uniId=cursor.getString(cursor.getColumnIndex("uniId"));
            Favorite favorite=new Favorite(_id, uniId);
            return favorite;

        }
        return null;
    }


    public void insertData(String uni_id,String name,Bitmap bm) { 
	ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,bos);
        byte[] buffer=bos.toByteArray();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COLUMN_UID, uni_id);
        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_PHOTO,buffer);
        database.insert(TABLE_NAME,null,contentValues);
    }

    public void deleteData(String uni_id) {
        database.delete(TABLE_NAME, TABLE_COLUMN_UID + " = ? ",new String[] { uni_id });
    }



    public Cursor getAllData() {
        String buildSQL = "SELECT * FROM "+TABLE_NAME;
        return database.rawQuery(buildSQL,null);
    }


   /* public String isExist(String uni_id,String name,Bitmap bm)
    {
        Cursor cursor=database.query(TABLE_NAME,new String[] {TABLE_COLUMN_ID, TABLE_COLUMN_UID,KEY_NAME,KEY_PHOTO}, TABLE_COLUMN_UID +"=?",KEY_NAME+"=?"+KEY_PHOTO+"=?",new String[]{uni_id,name,bm},null,null,null,null);
        if(cursor.moveToFirst())
        {
            deleteData(uni_id);
            return "Deleted";
        }
        else
        {
            insertData(uni_id,name,bm);
            return "Inserted";
        }

    }*/

    private class DatabaseOpenHelper extends SQLiteOpenHelper {
        public DatabaseOpenHelper(Context context ) {
            super(context, DBName, null, DATABASEVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String buildSQL = "CREATE TABLE "+ TABLE_NAME + "(" +
                    TABLE_COLUMN_ID + " INTEGER PRIMARY KEY," +
                    TABLE_COLUMN_UID + " TEXT,"+KEY_NAME+" TEXT,"+KEY_PHOTO+" BLOB ) ";

            db.execSQL(buildSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String buildSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(buildSQL);
            onCreate(db);
        }

    }
}

