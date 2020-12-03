package com.example.crab.controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crab.R;
import com.example.crab.modele.LevelDifficulties;


public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_DIFFICULTY = "EXTRA_DIFFICULTY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageView easy = findViewById(R.id.easy);
        ImageView normal = findViewById(R.id.norm);
        ImageView hard = findViewById(R.id.hard);

        easy.setTag(LevelDifficulties.FACILE);
        normal.setTag(LevelDifficulties.NORMAL);
        hard.setTag(LevelDifficulties.DIFFICLE);

        easy.setOnClickListener(this);
        normal.setOnClickListener(this);
        hard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getTag() instanceof LevelDifficulties){
            startGame((LevelDifficulties) v.getTag());
        }
    }

    private void startGame(LevelDifficulties difficulties){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_DIFFICULTY, difficulties);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if(hasFocus){
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

    @Override
    public void onBackPressed() { }
}