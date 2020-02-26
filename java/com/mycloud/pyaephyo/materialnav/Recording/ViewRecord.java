package com.mycloud.pyaephyo.materialnav.Recording;

import android.app.ListActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


import com.mycloud.pyaephyo.materialnav.Adapter.RecordAdapter;
import com.mycloud.pyaephyo.materialnav.ApplicationService.RecordActivity;
import com.mycloud.pyaephyo.materialnav.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by ucsm on 10/26/2016.
 */

public class ViewRecord extends ListActivity {
    RecordedFile recFile;
    RecordedFileList fileList;
    File[] files;
    ImageButton btnPause;
    MediaPlayer player;
    double len;
    int currentLocation = 0;
    String pauseState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_list_view);

        btnPause=(ImageButton)findViewById(R.id.btnPause);


        recFile = new RecordedFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/recordingsOfProj"));

        fileList = RecordedFileList.getInstance();

        files=fileList.getFiles();

        RecordAdapter adapter;

        player = new MediaPlayer();

        adapter=new RecordAdapter(this,R.layout.record_list_view);
        setListAdapter(adapter);

        for(File f:files){
            adapter.add(f);
        }
        pauseState="resume";
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         //       player=new MediaPlayer();

                if(pauseState.equals("resume")) {

                    if (player.isPlaying()) {
                        btnPause.setImageResource(R.drawable.resume);
                        pauseState="pause";
                        player.pause();
                        len = player.getCurrentPosition();
                    }
                }
                else if(pauseState.equals("pause")){
                    if(!player.isPlaying()){
                        player.seekTo((int)len);
                        player.start();
                        btnPause.setImageResource(R.drawable.pause);
                        pauseState="resume";
                    }
                }
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        currentLocation = position;

        if(player.isPlaying()) {
            player.stop();
        }

        try{
            player=new MediaPlayer();

            player.setDataSource(fileList.getAbsolutePathOfFile(currentLocation));
        }catch (IOException e){
            e.toString();
        }
        try{
            player.prepare();
        }catch (IOException e){
            e.toString();
        }
        player.start();
        btnPause.setImageResource(R.drawable.pause);

        pauseState = "resume";
    }

    public void nextClick (View view) {

        currentLocation++;

        if(currentLocation == fileList.size()){
            currentLocation = 0;
        }


        if(player.isPlaying()) {
            player.stop();
        }

         try{

             player=new MediaPlayer();


                player.setDataSource(fileList.getAbsolutePathOfFile(currentLocation));
                player.prepare();
            player.start();
        }
        catch (Exception e) {
            Toast.makeText(this, "Error Encountered." + e.toString(), Toast.LENGTH_SHORT).show();
        }

            Toast.makeText(this, "Current file  " + fileList.getAbsolutePathOfFile(currentLocation) , Toast.LENGTH_SHORT).show();
        btnPause.setImageResource(R.drawable.pause);

        pauseState = "resume";
    }

    public void previousClick(View view) {

        currentLocation--;

        if(currentLocation < 0) {
            currentLocation = fileList.size()-1;
        }


        if(player.isPlaying()) {
            player.stop();
        }

        try{

            player = new MediaPlayer();

            player.setDataSource(fileList.getAbsolutePathOfFile(currentLocation));
            player.prepare();
            player.start();

        }
        catch (Exception e) {
            Toast.makeText(this, "Error Encountered " + e.toString(), Toast.LENGTH_SHORT).show();
        }


        Toast.makeText(this, "current file " + fileList.getAbsolutePathOfFile(currentLocation) , Toast.LENGTH_SHORT).show();
        btnPause.setImageResource(R.drawable.pause);
        pauseState = "resume";
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i=new Intent(ViewRecord.this,RecordActivity.class);
        startActivity(i);
        finish();
    }
}
