package com.example.hw1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Activity_Winner extends Activity {
    public static final String SCORE = "SCORE";
    public static final String PLAYER = "PLAYER";
    TextView Winner_LBL;
    int player = 0;
    int score = 0;
    ImageView winner_IMG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        findViews();
        initViews();
    }

    private void findViews() {
        winner_IMG = findViewById(R.id.winner_IMG);
        Winner_LBL = findViewById(R.id.Winner_LBL);
    }

    private void initViews() {
        player = getIntent().getIntExtra(PLAYER, -1);
        if(player == 1) {
            winner_IMG.setImageResource(R.drawable.player1);
            Winner_LBL.setText("The Winner is: \n       Player "+ player);
        }else if (player == 2){
            winner_IMG.setImageResource(R.drawable.player2);
            Winner_LBL.setText("The Winner is: \n       Player "+ player);
        }
        else {
            Winner_LBL.setText("     It's A Tie!!");
            winner_IMG.setImageResource(R.drawable.win);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
