package com.example.himan.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingScreen extends AppCompatActivity {
    ProgressBar progressBar;
    TextView progressBarText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBarText = (TextView) findViewById(R.id.progressBarText);
        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
        for (int progress=0; progress<=100; progress+=1) {
            try {
                Thread.sleep(50);
                progressBar.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
                //Timber.e(e.getMessage());
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(LoadingScreen.this, MainActivity.class);
        startActivity(intent);
    }
}
