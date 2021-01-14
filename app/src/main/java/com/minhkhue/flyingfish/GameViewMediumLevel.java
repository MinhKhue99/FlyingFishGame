package com.minhkhue.flyingfish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class GameViewMediumLevel extends View {
    private final SharedPreferences prefs;
    private final Bitmap[] fish = new Bitmap[2];
    private final int fishX = 10;
    private int fishY;
    private int fishSpeed;

    private int yellowX;
    private int yellowY;
    private final Paint yellowPaint = new Paint();

    private int greenX;
    private int greenY;
    private final Paint greenPaint = new Paint();

    private int redX;
    private int redY;
    private final Paint redPaint = new Paint();


    private int score, lifeCounterOfFish;


    private boolean touch = false;

    private final Bitmap backgroundImage;
    private final Paint scorePaint = new Paint();
    private final Bitmap[] life = new Bitmap[2];


    public GameViewMediumLevel(Context context) {
        super(context);

        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);

        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);


        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);

        fishY = 550;
        score = 0;
        lifeCounterOfFish = 3;
        prefs = context.getSharedPreferences("game", Context.MODE_PRIVATE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasWidth = getWidth();
        int canvasHeight = getHeight();


        canvas.drawBitmap(backgroundImage, 0, 0, null);
        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight() * 3;
        fishY = fishY + fishSpeed;

        if (fishY < minFishY) {
            fishY = minFishY;
        }

        if (fishY > maxFishY) {
            fishY = maxFishY;
        }
        fishSpeed = fishSpeed + 2;

        if (touch) {
            canvas.drawBitmap(fish[1], fishX, fishY, null);
            touch = false;
        } else {
            canvas.drawBitmap(fish[0], fishX, fishY, null);

        }

        int yellowSpeed = 21;
        yellowX = yellowX - yellowSpeed;
        if (hitBallChecker(yellowX, yellowY)) {
            score = score + 10;
            yellowX = -100;
        }
        if (yellowX < 0) {
            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(yellowX, yellowY, 25, yellowPaint);


        int greenSpeed = 25;
        greenX = greenX - greenSpeed;
        if (hitBallChecker(greenX, greenY)) {
            score = score + 20;
            greenX = -100;
        }
        if (greenX < 0) {
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(greenX, greenY, 25, greenPaint);


        int redSpeed = 30;
        redX = redX - redSpeed;
        if (hitBallChecker(redX, redY)) {

            redX = -100;
            lifeCounterOfFish--;

            if (lifeCounterOfFish == 0) {
                saveIfHighSocre();
                @SuppressLint("DrawAllocation") Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("score", score);
                getContext().startActivity(gameOverIntent);

            }
        }
        if (redX < 0) {
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(redX, redY, 30, redPaint);


        canvas.drawText("Score : " + score, 20, 60, scorePaint);
        for (int i = 0; i < 3; i++) {
            int x = (int) (580 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i < lifeCounterOfFish) {
                canvas.drawBitmap(life[0], x, y, null);
            } else {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }

    }

    public boolean hitBallChecker(int x, int y) {
        return fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch = true;
            fishSpeed = -22;
        }
        return true;
    }

    private void saveIfHighSocre() {
        if (prefs.getInt("HighScore", 0) < score) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("HighScore", score);
            editor.apply();
        }
    }
}
