package com.tapefive.tapefivelooperv1;

import android.content.Intent;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class OpenSplash extends AppCompatActivity {

    MediaPlayer fresh;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_splash);

        Thread timer=new Thread(){

            @Override

            public void run(){

                try{

                fresh=MediaPlayer.create(OpenSplash.this, R.raw.scratch);
                fresh.start();
                sleep(5000);


            }
            catch(InterruptedException e)
            {

            }finally {
                    Intent intent=new Intent(OpenSplash.this, TitleSplash.class);
                    startActivity(intent);
                }
                }
        };
        timer.start();
    }

    @Override   protected void onPause(){

        super.onPause();
        if (fresh != null) {
            fresh.release();
        }
    }



}




