package com.bdn.jfxinvaders;

public class AttackPowerUp extends PowerUpComponent{
    double attackBoost = .25;
    double attackSpeedBoost = .25;

    public double getAttackBoost() {
        return attackBoost;
    }

    public void setAttackBoost(double attackBoost) {
        this.attackBoost = attackBoost;
    }

    public double getAttackSpeedBoost() {
        return attackSpeedBoost;
    }

    public void setAttackSpeedBoost(double attackSpeedBoost) {
        this.attackSpeedBoost = attackSpeedBoost;
    }
}
