package com.bdn.jfxinvaders;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

public class PowerUpHandler extends CollisionHandler {

    public PowerUpHandler() {
        super(EntityType.PLAYER, EntityType.POWERUP);
    }

    @Override
    protected void onCollisionBegin(Entity player, Entity powerup) {
        ShipComponent shipComponent = player.getComponent(ShipComponent.class);
        if(powerup.getComponent(NameComponent.class).getName().equals("attackUp")){
            AttackPowerUp attackPowerUp = powerup.getComponent(AttackPowerUp.class);
            shipComponent.increaseAttackSpeed(attackPowerUp.getAttackSpeedBoost());
            shipComponent.increaseDamage(attackPowerUp.getAttackBoost());
            powerup.removeFromWorld();
        } else if(powerup.getComponent(NameComponent.class).getName().equals("extraShot")){
            shipComponent.increaseNumberOfShots();
            powerup.removeFromWorld();
        }

    }

}
