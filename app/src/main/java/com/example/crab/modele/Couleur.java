 package com.example.crab.modele;

import android.util.Log;

import com.example.crab.R;

import java.util.ArrayList;
import java.util.List;

public enum Couleur {


    BLANC(true, R.drawable.crabe_white, "BLANC",R.drawable.ballon_white),
    NOIR(true,R.drawable.crabe_black, "NOIR",R.drawable.ballon_black),
    MARRON(true,R.drawable.crabe_brown, "MARRON",R.drawable.ballon_brown),
    ROUGE(false,R.drawable.crabe_red, "ROUGE",R.drawable.ballon_red),
    JAUNE(false,R.drawable.crabe_yellow, "JAUNE",R.drawable.ballon_yellow),
    VERT(false,R.drawable.crabe_green, "VERT",R.drawable.ballon_green),
    BLEU(false,R.drawable.crabe_blue, "BLEU",R.drawable.ballon_blue),
    ORANGE(false,R.drawable.crabe_orange, "ORANGE",R.drawable.ballon_orange),
    ROSE(false,R.drawable.crabe_pink, "ROSE",R.drawable.ballon_pink),
    VIOLET(false,R.drawable.crabe_purple, "VIOLET",R.drawable.ballon_purple),
    GRIS(true,R.drawable.crabe_grey, "GRIS",R.drawable.ballon_grey);

    private static List<Couleur> easyColor = new ArrayList<>();
    private static List<Couleur> hardColor = new ArrayList<>();

    static {
        for(com.example.crab.modele.Couleur color : com.example.crab.modele.Couleur.values()){
            if(color.couleurHard){
                hardColor.add(color);
            }
            else{
                hardColor.add(color);
                easyColor.add(color);
            }
            Log.d("addcouleur","facile " +easyColor.size() +" diff " + hardColor.size());
        }
    }

    private boolean couleurHard;
    private int ballonImage;
    private int crabeImage;
    private String name;

    Couleur(Boolean couleurHard, int crabeImage, String name, int ballonImage){
        this.couleurHard = couleurHard;
        this.crabeImage = crabeImage;
        this.name = name;
        this.ballonImage = ballonImage;
    }

    public int getBallonImage() {
        return ballonImage;
    }

    public int getCrabeImage() {
        return crabeImage;
    }

    public static com.example.crab.modele.Couleur getRandomEasyColor(){
        int random = (int) (Math.random()* easyColor.size());
        return easyColor.get(random);
    }
    public static com.example.crab.modele.Couleur getRandomHardColor(){
        int random = (int) (Math.random()* hardColor.size());
        return hardColor.get(random);
    }

    public String getName() {
        return name;
    }
}
