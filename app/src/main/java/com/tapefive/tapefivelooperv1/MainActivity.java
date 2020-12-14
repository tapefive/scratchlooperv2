package com.tapefive.tapefivelooperv1;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        switch (view.getId()) {
            case R.id.button1:
                soundPool.play(furysp, 1, 1, 1, -1, 1);
                soundPool.autoPause();
                soundPool.autoResume();
                Toast toastOne = Toast.makeText(MainActivity.this, "95 BPM", Toast.LENGTH_SHORT);
                toastOne.setGravity(Gravity.TOP, 0,20);
                toastOne.show();
                break;
            case R.id.button2:
                soundPool.play(laptopsp, 1, 1, 1, -1, 1);
                soundPool.autoPause();
                soundPool.autoResume();
                Toast toastTwo = Toast.makeText(MainActivity.this, "85 BPM", Toast.LENGTH_SHORT);
                toastTwo.setGravity(Gravity.TOP, 0,20);
                toastTwo.show();
                break;
            case R.id.button3:
                soundPool.play(pubgsp, 1, 1, 1, -1, 1);
                soundPool.autoPause();
                soundPool.autoResume();
                Toast toastThree = Toast.makeText(MainActivity.this, "100 BPM", Toast.LENGTH_SHORT);
                toastThree.setGravity(Gravity.TOP, 0,20);
                toastThree.show();
                break;
            case R.id.button4:
                soundPool.play(loopfoursp, 1, 1, 1, -1, 1);
                soundPool.autoPause();
                soundPool.autoResume();
                Toast toastFour = Toast.makeText(MainActivity.this, "90 BPM", Toast.LENGTH_SHORT);
                toastFour.setGravity(Gravity.TOP, 0,20);
                toastFour.show();
                break;
            case R.id.button5:
                soundPool.play(loopfivesp, 1, 1, 1, -1, 1);
                soundPool.autoPause();
                soundPool.autoResume();
                Toast toastFive = Toast.makeText(MainActivity.this, "105 BPM", Toast.LENGTH_SHORT);
                toastFive.setGravity(Gravity.TOP, 0,20);
                toastFive.show();
                break;
            case R.id.button6:
                soundPool.play(synthsp, 1, 1, 1, -1, 1);
                soundPool.autoPause();
                soundPool.autoResume();
                Toast toastSix = Toast.makeText(MainActivity.this, "90 BPM", Toast.LENGTH_SHORT);
                toastSix.setGravity(Gravity.TOP, 0,20);
                toastSix.show();
                break;
        }


    }

    public void Stop(View view) {
        soundPool.autoPause();
        Toast toastSeven = Toast.makeText(MainActivity.this, "Stopped", Toast.LENGTH_SHORT);
        toastSeven.setGravity(Gravity.TOP, 0,20);
        toastSeven.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        soundPool.autoPause();

    }


}




