package com.example.crab.modele;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.crab.controller.MainActivity;

public class Ballon {
    public Couleur ballonCouleur;
    public ImageView imageBallonView;
    public MainActivity context;
    public float dp;


    public Ballon(MainActivity qContext) {
        this.context = qContext;

        if(context.gameCrab.level.difficulties.toString().equals("FACILE")) {
            setCouleurFacile();
        }else {
            setCouleurDifficile();
        }

         dp = context.getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int) (40 * dp), (int) (40 * dp));
        imageBallonView.setLayoutParams(params);


        Randomposition(imageBallonView);
        context.screen.addView(imageBallonView);

    }

  /*  public Ballon(MainActivity qContext, Couleur couleur) {
        this.context = qContext;
        this.ballonCouleur = couleur;
        setCouleur();
        Randomposition();


    }*/



    public void setCouleurFacile(){
        imageBallonView = new ImageView(context);

        ballonCouleur = ballonCouleur.getRandomEasyColor();
        imageBallonView.setBackgroundResource(ballonCouleur.getBallonImage());

    }

    public void setCouleurDifficile(){
        imageBallonView = new ImageView(context);

        ballonCouleur = ballonCouleur.getRandomHardColor();
        imageBallonView.setBackgroundResource(ballonCouleur.getBallonImage());
    }

    public void Randomposition(ImageView qimageBallonView) {
        int randomX = (int) (Math.random() * (context.screen.getWidth() - 500/dp));
        int randomY = (int) (Math.random() * (context.screen.getHeight() - 500/dp));
        qimageBallonView.setX(randomX);
        qimageBallonView.setY(randomY);
        Log.d("posballon", "x = " + qimageBallonView.getX() + " y = "+ qimageBallonView.getY() + " yeet the screen = " +context.screen.getWidth() + " dp = " +qimageBallonView.getHeight()*dp + " yeet 2 " + context.screen.getHeight());
    }



    public Couleur getCouleur() {
        return ballonCouleur;
    }

    public ImageView getBallon() {
        return imageBallonView;
    }

}
