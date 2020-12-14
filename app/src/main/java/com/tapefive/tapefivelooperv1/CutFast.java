package com.tapefive.tapefivelooperv1;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut_fast);

        Chronometer simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        simpleChronometer.start();

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
        switch (view.getId()) {
            case R.id.button1:
                soundPool.play(beatOne, 1, 1, 1, -1, 1);
                soundPool.autoPause();
                soundPool.autoResume();
                Toast toastOne = Toast.makeText(CutFast.this, "97 BPM", Toast.LENGTH_SHORT);
                toastOne.setGravity(Gravity.TOP, 0,20);
                toastOne.show();
                break;
            case R.id.button2:
                soundPool.play(beatTwo, 1, 1, 1, -1, 1);
                soundPool.autoPause();
                soundPool.autoResume();
                Toast toastTwo = Toast.makeText(CutFast.this, "84 BPM", Toast.LENGTH_SHORT);
                toastTwo.setGravity(Gravity.TOP, 0,20);
                toastTwo.show();
                break;
            case R.id.button3:
                soundPool.play(beatThree, 1, 1, 1, -1, 1);
                soundPool.autoPause();
                soundPool.autoResume();
                Toast toastThree = Toast.makeText(CutFast.this, "95 BPM", Toast.LENGTH_SHORT);
                toastThree.setGravity(Gravity.TOP, 0,20);
                toastThree.show();
                break;
            case R.id.button4:
                soundPool.play(beatFour, 1, 1, 1, -1, 1);
                soundPool.autoPause();
                soundPool.autoResume();
                Toast toastFour = Toast.makeText(CutFast.this, "95 BPM", Toast.LENGTH_SHORT);
                toastFour.setGravity(Gravity.TOP, 0,20);
                toastFour.show();
                break;
            case R.id.button5:
                soundPool.play(beatFive, 1, 1, 1, -1, 1);
                soundPool.autoPause();
                soundPool.autoResume();
                Toast toastFive = Toast.makeText(CutFast.this, "94 BPM", Toast.LENGTH_SHORT);
                toastFive.setGravity(Gravity.TOP, 0,20);
                toastFive.show();
                break;
            case R.id.button6:
                soundPool.play(beatSix, 1, 1, 1, -1, 1);
                soundPool.autoPause();
                soundPool.autoResume();
                Toast toastSix = Toast.makeText(CutFast.this, "92 BPM", Toast.LENGTH_SHORT);
                toastSix.setGravity(Gravity.TOP, 0,20);
                toastSix.show();
                break;
        }


    }

    public void Stop(View view) {
        soundPool.autoPause();
        Toast toastSeven = Toast.makeText(CutFast.this, "Stopped", Toast.LENGTH_SHORT);
        toastSeven.setGravity(Gravity.TOP, 0,20);
        toastSeven.show();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        soundPool.autoPause();

    }

}

