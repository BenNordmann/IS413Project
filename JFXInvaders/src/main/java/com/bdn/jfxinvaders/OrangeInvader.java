package com.bdn.jfxinvaders;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import javafx.util.Duration;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class OrangeInvader extends InvaderComponent{

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
}
