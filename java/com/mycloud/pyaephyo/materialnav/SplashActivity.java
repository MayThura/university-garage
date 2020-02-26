package com.mycloud.pyaephyo.materialnav;

/**
 * Created by ucsm on 10/22/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends Activity {
    TextView textView;
    MediaPlayer music;

    @Override    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textView = (TextView) findViewById(R.id.text);
        Thread timer = new Thread() {
            public void run() {
                try {
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                    textView.startAnimation(animation);
                    music=MediaPlayer.create(SplashActivity.this, R.raw.theme2);
                    music.start();
                    sleep(4500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        };
        timer.start();
    }

    @Override    protected void onPause() {
        super.onPause();
        music.release();
        finish();

    }

}

