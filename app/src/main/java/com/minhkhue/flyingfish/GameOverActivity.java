package com.minhkhue.flyingfish;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        String score = getIntent().getExtras().get("score").toString();

        Button startGameAgain = findViewById(R.id.play_again_btn);
        TextView displayScore = findViewById(R.id.displayScore);


        startGameAgain.setOnClickListener(view -> {

            Intent mainIntent = new Intent(GameOverActivity.this, MainActivity.class);
            startActivity(mainIntent);
        });
        displayScore.setText(score + " scores" );
    }
}