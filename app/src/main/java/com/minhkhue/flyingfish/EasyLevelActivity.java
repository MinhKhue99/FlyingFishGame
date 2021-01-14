package com.minhkhue.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class EasyLevelActivity extends AppCompatActivity {
    private  GameViewEasyLevel gameViewEasyLevel;
    private final Handler handler = new Handler();
    private final  static  long Interval = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameViewEasyLevel = new GameViewEasyLevel(this);
        setContentView(gameViewEasyLevel);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> gameViewEasyLevel.invalidate());
            }
        }, 0, Interval);
    }
}
