package com.bdn.jfxinvaders;
// Contains the specific implementation of the attackUp powerup
public class AttackPowerUp extends PowerUpComponent{
    // sets the values the powerup increases your ship's statistics by.
    double attackBoost = .25;
    double attackSpeedBoost = .25;
// getters and setters
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
