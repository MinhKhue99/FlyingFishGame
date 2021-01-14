package com.minhkhue.flyingfish;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SoundPool soundPool;
    private int sound;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button btnEasyLevel = findViewById(R.id.easy_level);
        Button btnMediumLevel = findViewById(R.id.medium_level);
        Button btnHardLevel = findViewById(R.id.hard_level);
        Button btnHelp = findViewById(R.id.btn_help);
        TextView tvHighScore = findViewById(R.id.tvHighScore);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);
        tvHighScore.setText("High Socre: " + prefs.getInt("HighScore", 0));

        btnEasyLevel.setOnClickListener(v -> {
            Intent intentEasyLevel = new Intent(MainActivity.this, EasyLevelActivity.class);
            startActivity(intentEasyLevel);
        });
        btnMediumLevel.setOnClickListener(v -> {
            Intent intentMediumLevel = new Intent(MainActivity.this, MediumLevelActivity.class);
            startActivity(intentMediumLevel);
        });
        btnHardLevel.setOnClickListener(v -> {
            Intent intentHardLevel = new Intent(MainActivity.this, HardLevelActivity.class);
            startActivity(intentHardLevel);
        });
        btnHelp.setOnClickListener(v -> {
            Intent intentHelp = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(intentHelp);
        });
    }
}