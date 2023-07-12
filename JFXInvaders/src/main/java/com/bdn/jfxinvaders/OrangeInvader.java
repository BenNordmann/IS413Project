package com.bdn.jfxinvaders;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import javafx.util.Duration;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
// implementation of the orange invader
public class OrangeInvader extends InvaderComponent{
    // has a higher chance to drop powerups on death
    @Override
    public void die() {
        Entity explosion = spawn("explosion", new SpawnData(getEntity().getX(), getEntity().getY()));
        Random random = new Random();
        int seed = random.nextInt(100);
        System.out.println(seed);
        if(seed > 75 ){
            spawn("extraShot", new SpawnData(getAppWidth()/2, getAppHeight() - 40));
        }else {
            spawn("attackUp", new SpawnData(getAppWidth()/2, getAppHeight() - 40));
        }
        getEntity().removeFromWorld();
        runOnce(() -> {
            explosion.removeFromWorld();
            return null;
        }, Duration.seconds(1));
    }

    public OrangeInvader(int health, double speedX, double speedY, int points) {
        super(health, speedX, speedY, points);
    }
}
