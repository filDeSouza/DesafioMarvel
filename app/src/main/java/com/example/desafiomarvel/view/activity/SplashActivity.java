package com.example.desafiomarvel.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.desafiomarvel.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private ImageView imgSplash;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgSplash = findViewById(R.id.imageViewSplash);

        imgSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextScreen();
            }
        });
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                nextScreen();
            }
        }, 2000);
    }

    public void nextScreen(){
        timer.cancel();
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

}
