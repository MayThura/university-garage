package com.mycloud.pyaephyo.materialnav.ApplicationService;

/**
 * Created by ucsm on 10/27/2016.
 */

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;

import android.widget.Toast;
import com.mycloud.pyaephyo.materialnav.R;
import com.mycloud.pyaephyo.materialnav.Recording.ViewRecord;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class RecordActivity extends AppCompatActivity {

    ImageButton btnList, btnRecord, btnPlay,btnPause;
    private MediaRecorder myRecorder;
    private String outputFile=null;
    String recordState;

    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
    Date currentLocalTime = cal.getTime();
    DateFormat date = new SimpleDateFormat("HH:mm:ss");
    String localTime = date.format(currentLocalTime);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_layout);

        File f = new File(Environment.getExternalStorageDirectory(), "recordingsOfProj");
        boolean state = false;
        if(!f.exists()) {
            state =f.mkdir();
        }

        if(state) {
            Toast.makeText(this, "ERROR!! Cannot create folder. Level: Fatal" , Toast.LENGTH_SHORT).show();
        }

        recordState="stop";
        btnList=(ImageButton)findViewById(R.id.btnList);
        btnRecord=(ImageButton)findViewById(R.id.btnRecord);
        btnPlay=(ImageButton)findViewById(R.id.btnPlay);
        btnPause=(ImageButton)findViewById(R.id.btnPause);

        String s = Environment.getExternalStorageDirectory().getAbsolutePath();

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RecordActivity.this,ViewRecord.class);
                startActivity(i);
                finish();
            }
        });

        btnRecord.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (recordState.equals("stop")) {
                    btnRecord.setImageResource(R.drawable.stop);

                    outputFile= Environment.getExternalStorageDirectory().getAbsolutePath()+"/recordingsOfProj/"+localTime+".mp3" ;

                    myRecorder=new MediaRecorder();
                    myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                    myRecorder.setOutputFile(outputFile);


                    try{

                        myRecorder.prepare();

                        myRecorder.start();

                        //Toast.makeText(RecordActivity.this, "Recording started", Toast.LENGTH_SHORT).show();

                    }catch (IllegalStateException e){
                        e.toString();
                    }catch (IOException e){
                        e.toString();
                    }
                    btnRecord.setImageResource(R.drawable.stop);
                    recordState="record";
                    //Toast.makeText(getApplicationContext(), "Recording started....", Toast.LENGTH_SHORT).show();
                }

                else if (recordState.equals("record")) {
                    try{
                        myRecorder.stop();
                        myRecorder.release();

                        Toast.makeText(RecordActivity.this, "Recording ended", Toast.LENGTH_SHORT).show();

                        btnRecord.setImageResource(R.drawable.record_button);
                        recordState="stop";

                        myRecorder = null;

                        System.gc();

                        Intent i=new Intent(RecordActivity.this,ViewRecord.class);
                        startActivity(i);
                        finish();

                    }catch (IllegalStateException e){
                        e.toString();
                    }
                }
            }
        });
    }
}

