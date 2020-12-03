package com.example.crab.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.crab.R;
import com.example.crab.modele.Crab;
import com.example.crab.modele.CrabGameLevel;
import com.example.crab.modele.GameCrab;
import com.example.crab.modele.LevelDifficulties;

import java.security.interfaces.DSAPrivateKey;

public class MainActivity extends AppCompatActivity {
    public Crab crabe;
    public LinearLayout heartLayout;
    public ConstraintLayout screen;
    public TextView couleurText;
    public GameCrab gameCrab;
    public CrabGameLevel crabGameLevel;
    public Handler myHandler;
    public boolean active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LevelDifficulties difficulties = (LevelDifficulties) getIntent().getSerializableExtra(MenuActivity.EXTRA_DIFFICULTY);

        screen = findViewById(R.id.mainFrame);
        heartLayout = findViewById(R.id.hearts_layout);
        crabGameLevel = new CrabGameLevel(this,difficulties,heartLayout);
        couleurText = findViewById(R.id.couleurText);
        crabGameLevel.setOnEnd(() -> {
            Intent intent = new Intent(this, EndGameActivity.class);
            intent.putExtra(EndGameActivity.INTENT_STAR_NUMBER, crabGameLevel.getLives());
            startActivity(intent);
        });
    }
    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            gameCrab.run();

            if(!gameCrab.isEndGame()){
                myHandler.postDelayed(this,10);
            }else{
                Intent back = new Intent(MainActivity.this, EndGameActivity.class);
                back.putExtra(EndGameActivity.INTENT_STAR_NUMBER, crabGameLevel.getLives());
//                startActivity(back);
            }
        }
    };
    public void onPause() {
        super.onPause();
        active = false;
        if(myHandler != null)
            myHandler.removeCallbacks(myRunnable); // On arrete le callback
    }
    @Override
    public void onResume(){
        super.onResume();
        active = true;
        if(gameCrab != null)
            myRunnable.run();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if(hasFocus){
            if(gameCrab == null) {
                gameCrab = new GameCrab(this, crabGameLevel);
                myHandler = new Handler();
                myRunnable.run();
            }
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }

    }
}