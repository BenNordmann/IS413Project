package com.bdn.jfxinvaders;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import javafx.util.Duration;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
// implementation of the red invader
public class RedInvader extends InvaderComponent{
    //spawns green aliens every 12 seconds

    @Override
    public void onUpdate(double tpf) {
        frameCounter ++;
        if(frameCounter % 1200 == 0){
            spawn("greenAlien", getEntity().getX(), getEntity().getY());
        }
          else if(frameCounter % 120 == 0 && frameCounter % 240 == 0){
            invaderMovementY();
            invaderMovementX();
        } else if (frameCounter % 120 == 0){
            invaderMovementX();
        }
    }
    // has guaranteed powerup drops
    @Override
    public void die() {
        Entity explosion = spawn("explosion", new SpawnData(getEntity().getX(), getEntity().getY()));
        Random random = new Random();
        int seed = random.nextInt(100);
        System.out.println(seed);
            spawn("extraShot", new SpawnData(getAppWidth()/2, getAppHeight() - 40));
            spawn("attackUp", new SpawnData(getAppWidth()/2, getAppHeight() - 40));
        getEntity().removeFromWorld();
        runOnce(() -> {
            explosion.removeFromWorld();
            return null;
        }, Duration.seconds(1));
    }

    public RedInvader(int health, double speedX, double speedY, int points) {
        super(health, speedX, speedY, points);
    }
}
