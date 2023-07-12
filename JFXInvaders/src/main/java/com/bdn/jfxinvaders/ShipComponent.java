package com.bdn.jfxinvaders;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.TimeComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

// Contains the functionality for the player's controllable ship
public class ShipComponent extends Component {

    private PowerUp shipPowerUp = new PowerUp();
    private double damage = 1.0;
    private double attackSpeed = 1.75;
    private double lastShot = 0;
    final int speed = 60;
    // checks if the player's movement would place them out of bounds. If it would, play a sound effect for bouncing into the wall.
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

    public void shoot(){
        if(getGameTimer().getNow() - lastShot > attackSpeed) {
            spawn("shipBullet", new SpawnData(getEntity().getX(), getEntity().getY()));
            lastShot = getGameTimer().getNow();
        }
    }

    public void increaseAttackSpeed(){
        if(attackSpeed - shipPowerUp.attackSpeedBoost >0){
            attackSpeed -= shipPowerUp.attackSpeedBoost;
        }
    }

    public void increaseDamage(){
        damage += shipPowerUp.damageBoost;
    }

    public void die(){
    }

    public PowerUp getShipPowerUp() {
        return shipPowerUp;
    }

    public void setShipPowerUp(PowerUp shipPowerUp) {
        this.shipPowerUp = shipPowerUp;
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
