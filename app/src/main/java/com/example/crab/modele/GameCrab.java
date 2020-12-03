package com.example.crab.modele;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.crab.controller.EndGameActivity;
import com.example.crab.controller.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCrab extends AppCompatActivity {

    public MainActivity context;
    public CrabGameLevel level;
    public Crab crabe;
    public boolean init, endGame;

    public static List<Ballon> listBallon;
    public static List<Oiseau> listOiseau;
    int step = 0;
    int nextStep;
    public int count;

    List<Oiseau> toRemove = new ArrayList<>();
    List<Oiseau> toAdd = new ArrayList<>();

    public GameCrab(MainActivity qContext, CrabGameLevel crabGameLevel) {
        this.context = qContext;
        this.level = crabGameLevel;
        listBallon = new ArrayList<>();
        listOiseau = new ArrayList<>();
        init = false;
        endGame = false;

    }

    public void run() {
        if (!init) {
            generationBallon();
            crabe = new Crab(context);

            NewColor();

            //context.couleurText.setColor(Color.parseColor("#000"));
            level.initialise();
            init = true;

            hardGame();
        }
        if (level.getLives() == 0 || listBallon.size() == 0 || listBallon.isEmpty()) {
            level.end();
            endGame();
        }

    }

    private void generationBallon() {
        Ballon ballon;
        for (int i = 0; i <= 10; i++) {
            Log.d("intballon", "nbbbb=" + i);
//            Couleur couleur = level.getListDeJeu().get(i);
            ballon = new Ballon(context);
            listBallon.add(ballon);
            Log.d("intballon", "size=" + i);
        }

    }

    public void RemoveBallon(Ballon qballon) {
        context.screen.removeView(qballon.imageBallonView);
        context.gameCrab.listBallon.remove(qballon);
        if (!qballon.getCouleur().getName().equals(context.gameCrab.crabe.crabColor.getName())) {
            level.removeLife();

        }
        NewColor();

    }

    public void hardGame() {
        if (level.difficulties.toString().equals("DIFFICLE")) {
            new Thread(this::OiseauAI).start();

        }
    }

    public void NewColor() {
        for (int i = 0; i < listBallon.size(); i++) {
            if (crabe.crabColor.getName().equals(listBallon.get(i).getCouleur().getName())) {
                count += 1;
            }
        }
        if (count == 0) {
            context.screen.removeView(crabe.imageCrabView);
            crabe.setNewCouleur();

        }
        count = 0;
        context.couleurText.setText(crabe.crabColor.getName());
    }

    public void OiseauAI() {
        while (context.active) {

            if (step >= nextStep) {
                nextStep = new Random().nextInt(50)+150;
                step = 0;
            }

            if (step == 0) {
                genrationOiseau(false);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            step++;

            for (Oiseau oi : listOiseau)
                oi.move();


            for (Oiseau oi : toRemove)
                listOiseau.remove(oi);

            listOiseau.addAll(toAdd);

            toRemove.clear();
            toAdd.clear();

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void genrationOiseau(boolean fromDestroy) {
        if (fromDestroy || listOiseau.size() < 6) {
            Oiseau oiseau = new Oiseau(context);

            if (fromDestroy)
                toAdd.add(oiseau);
            else
                listOiseau.add(oiseau);
            oiseau.setOnDestroy(() -> {
                toRemove.add(oiseau);
                genrationOiseau(true);

            });
        }
    }

    public void endGame() {
        context.onPause();
        endGame = true;
    }


    public boolean isEndGame() {
        return endGame;
    }


}
