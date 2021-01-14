package com.minhkhue.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MediumLevelActivity extends AppCompatActivity {
    private GameViewMediumLevel gameViewMediumLevel;
    private final Handler handler  = new Handler();
    private final  static  long Interval = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameViewMediumLevel = new GameViewMediumLevel(this);
        setContentView(gameViewMediumLevel);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> gameViewMediumLevel.invalidate());
            }
        }, 0, Interval);
    }
}