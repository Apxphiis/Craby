package com.example.crab.modele;

import android.util.Log;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;


import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.crab.R;
import com.example.crab.controller.MainActivity;

public class Oiseau {
    public static final int LEFT = -1;
    private static final int SPEED = 5;
    private static final int SIZE = 40;

    private MainActivity context;
    private float dp;
    private ImageView birdView;
    private int direction;

    private boolean isDestroyed;
    private Runnable onDestroy;

    public Oiseau(MainActivity qcontext) {
        this.context = qcontext;
        direction = LEFT;

        dp = context.getResources().getDisplayMetrics().density;
        int width = (int) (SIZE * dp);
        int height = (int) (SIZE * dp);

        float x = context.screen.getX();
        if (direction == LEFT)
            x = context.screen.getWidth() - height;

        float y = (float) (Math.random() * (context.screen.getHeight() - height));

        birdView = new ImageView(context);
        birdView.setImageResource(R.drawable.bird);
        birdView.setLayoutParams(new ConstraintLayout.LayoutParams(width, height));
        birdView.setX(x);
        birdView.setY(y);

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.screen.addView(birdView);
            }
        });


        Log.d("bird", "bird bird sku");
    }

    public ImageView getBirdView() {
        return birdView;
    }

    public void move() {

        float x = birdView.getX();
        x += direction * SPEED * dp;
        move(x, birdView.getY());

        if (context.screen.getWidth() == 0)
            return;
        if (birdView.getX() + SIZE * dp < context.screen.getX() || birdView.getX() > context.screen.getX() + context.screen.getWidth())
            destroy();
    }

    private void move(float x, float y) {
        context.runOnUiThread(() -> {
            birdView.setX(x);
            birdView.setY(y);
            Log.d("birdlocation", "I'm a bird x = " + birdView.getX() + " y = " + birdView.getY());
        });
    }

    public void setOnDestroy(Runnable callback) {
        this.onDestroy = callback;
    }

    private void destroy() {
        isDestroyed = true;
        context.runOnUiThread(() -> context.screen.removeView(birdView));
        if (onDestroy != null)
            onDestroy.run();
    }
}
