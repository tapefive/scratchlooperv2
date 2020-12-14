package com.tapefive.tapefivelooperv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;


public class SplashClose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_close);

        Timer myTimer = new Timer();
        TimerTask close = new TimerTask() {
            @Override
            public void run() {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());

                System.exit(0);
            }
        };

        myTimer.schedule(close, 6000);
    }


}
