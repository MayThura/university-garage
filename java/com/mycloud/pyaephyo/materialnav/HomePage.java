package com.mycloud.pyaephyo.materialnav;

/**
 * Created by ucsm on 10/22/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mycloud.pyaephyo.materialnav.database.DatabaseHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HomePage extends Activity
{
    float x1,x2;
    DatabaseHelper mdbHelper;
    float y1, y2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView myImage= (ImageView) findViewById(R.id.imgLogo);
        myImage.setImageAlpha(127);
        mdbHelper=new DatabaseHelper(this);
        File database=getApplicationContext().getDatabasePath(mdbHelper.DBName);
        if(false==database.exists()) {
            Log.i("Success","success");
            mdbHelper.getReadableDatabase();
            if (copyDatabase(this)) {
                Log.i("Success","success");
                Toast.makeText(this, "Copy success", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("Success","success");
                Toast.makeText(this, "Copy denined", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

   //
    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if (y1 > y2)
                {
                    Intent i=new Intent(HomePage.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                break;
            }
        }
        return false;
    }
    private boolean copyDatabase(Context context) {
        try {
            InputStream myInput = context.getAssets().open(mdbHelper.DBName);
            String outFileName = mdbHelper.DBLocatoin + mdbHelper.DBName;
            Log.i("Before file out","Succes...");
            OutputStream myOutput = new FileOutputStream(outFileName);
            Log.i("After file out","Succes...");
            byte[] buffer = new byte[1024];
            int length = 0;

            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
            Log.i("Copy Database","Copy Success....");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}