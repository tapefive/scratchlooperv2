package com.tapefive.tapefivelooperv1;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;




public class TitleSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_splash);
    }


    public void TapeFive (View view) {
        Intent intent = new Intent(TitleSplash.this, MainActivity.class);
        startActivity(intent);
    }

    public void CutFast (View view) {
        Intent cutFast = new Intent(TitleSplash.this, CutFast.class);
        startActivity(cutFast);
    }
}


