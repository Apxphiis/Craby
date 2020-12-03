package com.example.crab.modele;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.crab.R;
import com.example.crab.controller.EndGameActivity;
import com.example.crab.controller.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class CrabGameLevel {

    public MainActivity context;
    public LevelDifficulties difficulties;
    public int lives;
    private Runnable onEnd;
    public ImageView[] hearts;
    public List<Couleur> listDeJeu;


    public  CrabGameLevel(MainActivity context, LevelDifficulties difficulties, LinearLayout livesLayout){
        this.context = context;
        this.difficulties = difficulties;
        this.lives = 3;
        this.hearts = new ImageView[lives];
        listDeJeu = new ArrayList<>();

        createHearths(livesLayout);
    }

    private void createHearths(LinearLayout layout){
        float dp = context.getResources().getDisplayMetrics().density;
        final int SIZE = 50;

        for(int i = 0; i < hearts.length; i++){
            ImageView heart = new ImageView(context);
            heart.setImageResource(R.drawable.heart);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (SIZE*dp), (int) (SIZE*dp));
            heart.setLayoutParams(params);
            layout.addView(heart);

            hearts[i] = heart;
        }
    }

    public int getLives() {
        return lives;
    }

    public List<Couleur> getListDeJeu() {
        return listDeJeu;
    }

    public void removeLife() {
        lives--;
        if (lives <= 0) {
            Intent intent = new Intent(context, EndGameActivity.class);
            intent.putExtra(EndGameActivity.INTENT_STAR_NUMBER, 0);

            context.startActivity(intent);

            return;
        }
        hearts[lives].setImageResource(R.drawable.heart_empty);
    }


    public void setOnEnd(Runnable onEnd){
        this.onEnd = onEnd;
    }

    public void initialise() {
    }

    public void end() {
        onEnd.run();
    }
}
