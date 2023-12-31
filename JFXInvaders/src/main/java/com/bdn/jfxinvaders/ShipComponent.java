package com.bdn.jfxinvaders;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

// Contains the functionality for the player's controllable ship
public class ShipComponent extends Component {
    // ships attributes
    private double damage = 1.0;
    private int numberOfShots= 1;
    private double attackSpeed = 1;
    private double lastShot = 0;
    final int speed = 30;
    // checks if the player's movement would place them out of bounds, if there is room, moves the player
    public void moveLeft(){
        if (getEntity().getX() - speed >= 0){
            getEntity().translateX(-speed);
        }
    }

    public void moveRight(){
        if (getEntity().getX() + speed <= FXGL.getSettings().getWidth()){
            getEntity().translateX(+speed);
        }
    }
    // fires a projectile, if enough time has not passed between the last shot and now, does not fire a shot
    public void shoot(){
        if(getGameTimer().getNow() - lastShot > attackSpeed) {
            for(int i = 1; i <= numberOfShots; i++) {
                // plays a sound effect and spawns a bullet entity
                play("shot.wav");
                spawn("shipBullet", new SpawnData(getEntity().getX(), getEntity().getY()));
            }
            lastShot = getGameTimer().getNow();
            System.out.println(numberOfShots+ "" + damage);
        }
    }
    //methods for handling powerups
    public void increaseAttackSpeed(double boost){
        if(attackSpeed - boost > 0){
            attackSpeed -= boost;
        }
    }

    public void increaseDamage(double boost){
        damage += boost;
    }

    public void increaseNumberOfShots(){
        numberOfShots += 1;
    }

    public void die(){
    }


    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getLastShot() {
        return lastShot;
    }

    public void setLastShot(double lastShot) {
        this.lastShot = lastShot;
    }

    public int getSpeed() {
        return speed;
    }

    public ShipComponent() {
    }
}
