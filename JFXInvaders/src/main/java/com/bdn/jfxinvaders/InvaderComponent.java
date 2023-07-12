package com.bdn.jfxinvaders;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.TimeComponent;
import com.almasb.fxgl.time.TimerAction;
import javafx.util.Duration;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public abstract class InvaderComponent extends Component {
    int health;
    double speedX;
    double speedY;
    int points;
    int frameCounter = 0;
    public void invaderMovementX(){
        if(getEntity().getX() + (2.5 * speedX) > FXGL.getSettings().getActualWidth()){
            speedX *= -1;
            getEntity().translateX(speedX);
        } else if(getEntity().getX() + speedX <= 0){
            speedX *= -1;
            getEntity().translateX(speedX);
        }else{
            getEntity().translateX(speedX);
        }

    }

    public void invaderMovementY(){
        if(getEntity().getY() + (2.5 *speedY) <= FXGL.getSettings().getActualHeight() ){
            getEntity().translateY(+speedY);
        } else {
            entity.removeFromWorld();
        }
    }


    public void onUpdate(double tpf){
        frameCounter ++;
        if(frameCounter % 120 == 0 && frameCounter % 240 == 0){
            invaderMovementY();
            invaderMovementX();
        } else if (frameCounter % 120 == 0){
            invaderMovementX();
        }
    }

    public void die(){
        play("alienexplosion.wav");
        Entity explosion = spawn("explosion", new SpawnData(getEntity().getX(), getEntity().getY()));
        Random random = new Random();
        int seed = random.nextInt(100);
        System.out.println(seed);
        if(seed > 90 ){
            spawn("extraShot", new SpawnData(getAppWidth()/2, getAppHeight() - 40));
        }else if(seed > 75){
            spawn("attackUp", new SpawnData(getAppWidth()/2, getAppHeight() - 40));
        }
        getEntity().removeFromWorld();
        runOnce(() -> {
            explosion.removeFromWorld();
            return null;
        }, Duration.seconds(1));
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public InvaderComponent() {
    }

    public InvaderComponent(int health, double speedX, double speedY, int points) {
        this.health = health;
        this.speedX = speedX;
        this.speedY = speedY;
        this.points = points;
    }
}
