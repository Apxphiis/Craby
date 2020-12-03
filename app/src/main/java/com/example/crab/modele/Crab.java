package com.example.crab.modele;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.crab.controller.MainActivity;


public class Crab implements SensorEventListener {

    public MainActivity context;
    public Couleur crabColor;
    public ImageView imageCrabView;
    public SensorManager sensorManager;


    public Sensor accelSens;

    public Crab(MainActivity pContext) {
        context = pContext;

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelSens = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelSens, SensorManager.SENSOR_DELAY_GAME);

        setCouleur();
    }

    public void setCouleur() {

        imageCrabView = new ImageView(context);
        crabColor = context.gameCrab.listBallon.get(0).ballonCouleur;
        imageCrabView.setBackgroundResource(crabColor.getCrabeImage());

        float dp = context.getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int) (55 * dp), (int) (55 * dp));
        imageCrabView.setLayoutParams(params);


        context.screen.addView(imageCrabView);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float newX = context.gameCrab.crabe.imageCrabView.getX() + event.values[1];
            float newY = context.gameCrab.crabe.imageCrabView.getY() + event.values[0];

            context.gameCrab.crabe.imageCrabView.setX(newX);
            context.gameCrab.crabe.imageCrabView.setY(newY);
            screenColision();
            ballonColision();
            oiseauColision();

            Log.e("position", "posX=" + context.gameCrab.crabe.imageCrabView.getX() + "postY=" + context.gameCrab.crabe.imageCrabView.getY());

        }
    }

    private void screenColision() {
        if (context.gameCrab.crabe.imageCrabView.getX() > (context.screen.getWidth() - context.gameCrab.crabe.imageCrabView.getWidth())) {
            context.gameCrab.crabe.imageCrabView.setX(context.screen.getWidth() - context.gameCrab.crabe.imageCrabView.getWidth());
        }
        if (context.gameCrab.crabe.imageCrabView.getX() < 0) {
            context.gameCrab.crabe.imageCrabView.setX(0);
        }
        if (context.gameCrab.crabe.imageCrabView.getY() > (context.screen.getHeight() - context.gameCrab.crabe.imageCrabView.getHeight())) {
            context.gameCrab.crabe.imageCrabView.setY(context.screen.getHeight() - context.gameCrab.crabe.imageCrabView.getHeight());

        } else if (context.gameCrab.crabe.imageCrabView.getY() < 0) {
            context.gameCrab.crabe.imageCrabView.setY(0);

        }
    }

    private void ballonColision() {
        for (int i = 0; i < context.gameCrab.listBallon.size(); i++) {
            if (context.gameCrab.listBallon.get(i).getBallon().getY() >= context.gameCrab.crabe.imageCrabView.getY() && context.gameCrab.listBallon.get(i).getBallon().getY() <= (context.gameCrab.crabe.imageCrabView.getY() + context.gameCrab.crabe.imageCrabView.getHeight() / 2) && context.gameCrab.listBallon.get(i).getBallon().getX() > context.gameCrab.crabe.imageCrabView.getX() && context.gameCrab.listBallon.get(i).getBallon().getX() < (context.gameCrab.crabe.imageCrabView.getX() + context.gameCrab.crabe.imageCrabView.getWidth() / 2)) {
                context.gameCrab.RemoveBallon(context.gameCrab.listBallon.get(i));

                Log.e("catch", "catch Yeah");

            }


        }

    }
    public void  oiseauColision(){
        for (int i = 0; i < context.gameCrab.listOiseau.size(); i++) {
            if (context.gameCrab.listOiseau.get(i).getBirdView().getY() >= context.gameCrab.crabe.imageCrabView.getY() && context.gameCrab.listOiseau.get(i).getBirdView().getY() <= (context.gameCrab.crabe.imageCrabView.getY() + context.gameCrab.crabe.imageCrabView.getHeight() / 2) && context.gameCrab.listOiseau.get(i).getBirdView().getX() > context.gameCrab.crabe.imageCrabView.getX() && context.gameCrab.listOiseau.get(i).getBirdView().getX() < (context.gameCrab.crabe.imageCrabView.getX() + context.gameCrab.crabe.imageCrabView.getWidth() / 2)) {
                context.gameCrab.listOiseau.get(i).getBirdView().setX(context.gameCrab.crabe.imageCrabView.getY()-1);
                context.gameCrab.level.removeLife();

                Log.e("lose", "fc bird");

            }


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    public void setNewCouleur() {
        if(context.gameCrab.listBallon.isEmpty()) return;

        crabColor = context.gameCrab.listBallon.get(0).ballonCouleur;
        imageCrabView.setBackgroundResource(crabColor.getCrabeImage());
        float dp = context.getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int) (55 * dp), (int) (55 * dp));
        imageCrabView.setLayoutParams(params);

       context.screen.addView(imageCrabView);
    }
}
