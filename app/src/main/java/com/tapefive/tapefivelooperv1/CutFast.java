package com.tapefive.tapefivelooperv1;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
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

public class CutFast extends AppCompatActivity {

    private SoundPool soundPool;
    private int beatOne, beatTwo, beatThree, beatFour, beatFive, beatSix;

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
        setContentView(R.layout.activity_cut_fast);

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


        beatOne = soundPool.load(this, R.raw.beatone22hz, 1);
        beatTwo = soundPool.load(this, R.raw.beattwo22hz, 1);
        beatThree = soundPool.load(this, R.raw.beatthree22hz, 1);
        beatFour = soundPool.load(this, R.raw.beatfour22hz, 1);
        beatFive = soundPool.load(this, R.raw.beatfive22hz, 1);
        beatSix = soundPool.load(this, R.raw.beatsix22hz, 1);

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
            soundPool.play(beatOne, 1, 1, 1, -1, 1);
            soundPool.autoPause();
            soundPool.autoResume();
            showBPMToast("97");
        } else if (viewId == R.id.button2) {
            soundPool.play(beatTwo, 1, 1, 1, -1, 1);
            soundPool.autoPause();
            soundPool.autoResume();
            showBPMToast("84");
        } else if (viewId == R.id.button3) {
            soundPool.play(beatThree, 1, 1, 1, -1, 1);
            soundPool.autoPause();
            soundPool.autoResume();
            showBPMToast("95");
        } else if (viewId == R.id.button4) {
            soundPool.play(beatFour, 1, 1, 1, -1, 1);
            soundPool.autoPause();
            soundPool.autoResume();
            showBPMToast("95");
        } else if (viewId == R.id.button5) {
            soundPool.play(beatFive, 1, 1, 1, -1, 1);
            soundPool.autoPause();
            soundPool.autoResume();
            showBPMToast("94");
        } else if (viewId == R.id.button6) {
            soundPool.play(beatSix, 1, 1, 1, -1, 1);
            soundPool.autoPause();
            soundPool.autoResume();
            showBPMToast("92");
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
        final Toast toast = Toast.makeText(CutFast.this, bpm + " BPM", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 20);
        toast.show();
        
        new Handler(Looper.getMainLooper()).postDelayed(toast::cancel, 500);
    }

    private void showCustomToast(String message) {
        final Toast toast = Toast.makeText(CutFast.this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 20);
        toast.show();
        
        new Handler(Looper.getMainLooper()).postDelayed(toast::cancel, 500);
    }

}

