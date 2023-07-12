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
// The parent class of all invaders
// Contains all shared functionality
public abstract class InvaderComponent extends Component {
    // Health, speedx/y, and points are the variables that will be set when an InvaderComponent is constructed
    // handles the hit point value, horizontal and vertical movement as well as the number of points they are worth
    int health;
    double speedX;
    double speedY;
    int points;
    // framecounter is used to keep track of time in onUpdate, dictates the movement pattern for the invaders
    int frameCounter = 0;
    // handles the invader's horizontal movement, changes direction when approaching a wall
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
    // handles the invader's vertical movement, is supposed to delete the invader once they reach the bottom of the screen
    // but for whatever reason it never recognizes that its reached the bottom
    public void invaderMovementY(){
        if(getEntity().getY() + (2.5 *speedY) <= FXGL.getSettings().getActualHeight() ){
            getEntity().translateY(+speedY);
        } else {
            entity.removeFromWorld();
        }
    }

    // handles the actual movement patterns
    public void onUpdate(double tpf){
        frameCounter ++;
        // every 4 seconds the invader moves down and horizontally, every 2 seconds the invader moves only horizontally
        if(frameCounter % 120 == 0 && frameCounter % 240 == 0){
            invaderMovementY();
            invaderMovementX();
        } else if (frameCounter % 120 == 0){
            invaderMovementX();
        }
    }
    // handles on death functionality
    public void die(){
        // spawns an image of an explosion and plays an explosion sound effect on death
        play("alienexplosion.wav");
        Entity explosion = spawn("explosion", new SpawnData(getEntity().getX(), getEntity().getY()));
        // generates a random number, if within a certain threshold spawns one of two powerups
        Random random = new Random();
        int seed = random.nextInt(100);
        System.out.println(seed);
        if(seed > 90 ){
            spawn("extraShot", new SpawnData(getAppWidth()/2, getAppHeight() - 40));
        }else if(seed > 75){
            spawn("attackUp", new SpawnData(getAppWidth()/2, getAppHeight() - 40));
        }// deletes the invader, and after a second deletes the explosion
        getEntity().removeFromWorld();
        runOnce(() -> {
            explosion.removeFromWorld();
            return null;
        }, Duration.seconds(1));
    }
    // getters and setters

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
