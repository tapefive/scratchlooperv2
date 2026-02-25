package com.tapefive.tapefivelooperv1;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private int furysp, laptopsp, synthsp, loopfoursp, pubgsp, loopfivesp;

    private AudioManager audioManager;

    private static final int MAX_STREAMS = 1;

    private static final int streamType = AudioManager.STREAM_MUSIC;

    private float volume;
    
    private Chronometer simpleChronometer;
    private boolean timerReset = true;
    private boolean timerRunning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        simpleChronometer.stop();
        
        simpleChronometer.setOnClickListener(v -> {
            if (timerRunning) {
                simpleChronometer.stop();
                timerRunning = false;
            } else {
                simpleChronometer.start();
                timerRunning = true;
            }
        });

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);

        float maxVolumeIndex = (float) audioManager.getStreamMaxVolume(streamType);

        this.volume = currentVolumeIndex / maxVolumeIndex;

        this.setVolumeControlStream(streamType);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(MAX_STREAMS)
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else {

            soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);

        }

        furysp = soundPool.load(this, R.raw.fury22hz, 1);
        laptopsp = soundPool.load(this, R.raw.laptop22hz, 1);
        synthsp = soundPool.load(this, R.raw.synth22hz, 1);
        loopfoursp = soundPool.load(this, R.raw.loopfour22hz, 1);
        pubgsp = soundPool.load(this, R.raw.pubg22hz, 1);
        loopfivesp = soundPool.load(this, R.raw.loopfive22hz, 1);

    }

    public void Logo(View view) {
        soundPool.autoPause();
        Intent reDirect = new Intent(Intent.ACTION_VIEW, Uri.parse("https://soundcloud.com/tapefive"));
        startActivity(reDirect);
    }

    public void PlaySound(View view) {
        if (timerReset) {
            simpleChronometer.setBase(SystemClock.elapsedRealtime());
            simpleChronometer.start();
            timerRunning = true;
            timerReset = false;
        }
        
        int viewId = view.getId();
        if (viewId == R.id.button1) {
            soundPool.play(furysp, 1, 1, 1, -1, 1);
            soundPool.autoPause();
            soundPool.autoResume();
            showBPMToast("95");
        } else if (viewId == R.id.button2) {
            soundPool.play(laptopsp, 1, 1, 1, -1, 1);
            soundPool.autoPause();
            soundPool.autoResume();
            showBPMToast("85");
        } else if (viewId == R.id.button3) {
            soundPool.play(pubgsp, 1, 1, 1, -1, 1);
            soundPool.autoPause();
            soundPool.autoResume();
            showBPMToast("100");
        } else if (viewId == R.id.button4) {
            soundPool.play(loopfoursp, 1, 1, 1, -1, 1);
            soundPool.autoPause();
            soundPool.autoResume();
            showBPMToast("90");
        } else if (viewId == R.id.button5) {
            soundPool.play(loopfivesp, 1, 1, 1, -1, 1);
            soundPool.autoPause();
            soundPool.autoResume();
            showBPMToast("105");
        } else if (viewId == R.id.button6) {
            soundPool.play(synthsp, 1, 1, 1, -1, 1);
            soundPool.autoPause();
            soundPool.autoResume();
            showBPMToast("90");
        }
    }

    public void Stop(View view) {
        soundPool.autoPause();
        simpleChronometer.stop();
        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        timerReset = true;
        timerRunning = false;
        showCustomToast("Stopped");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        soundPool.autoPause();
    }

    private void showBPMToast(String bpm) {
        final Toast toast = Toast.makeText(MainActivity.this, bpm + " BPM", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 20);
        toast.show();
        
        new Handler(Looper.getMainLooper()).postDelayed(toast::cancel, 500);
    }

    private void showCustomToast(String message) {
        final Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 20);
        toast.show();
        
        new Handler(Looper.getMainLooper()).postDelayed(toast::cancel, 500);
    }


}




