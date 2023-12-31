package com.bdn.jfxinvaders;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import com.bdn.jfxinvaders.EntityType;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.texture;
// contains the templates for spawning entities, all players and enemies are shrunk to 40pxs
// also contains the components for each entity
public class InvaderFactory implements EntityFactory {
    // green invader/green alien template
    @Spawns("greenAlien")
    public Entity newGreenAlien(SpawnData data){
        Texture texture = texture("greenalien.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(40);
        return entityBuilder(data)
                .type(EntityType.ENEMY)
                .viewWithBBox(texture)
                .with(new GreenInvader(1, 40, 20, 10), new CollidableComponent(true), new HealthIntComponent(2), new NameComponent("greenAlien"))
                .build();
    }
    // template for orange alien/orange invader
    @Spawns("orangeAlien")
    public Entity newOrangeAlien(SpawnData data){
        Texture texture = texture("orangealien.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(40);
        return entityBuilder(data)
                .type(EntityType.ENEMY)
                .viewWithBBox(texture)
                .with(new OrangeInvader(6, 60, 30, 40), new CollidableComponent(true), new HealthIntComponent(2), new NameComponent("orangeAlien"))
                .build();
    }
    // template for red alien/red invader
    @Spawns("redAlien")
    public Entity newRedAlien(SpawnData data){
        Texture texture = texture("redalien.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(40);
        return entityBuilder(data)
                .type(EntityType.ENEMY)
                .viewWithBBox(texture)
                .with(new RedInvader(10, 80, 40, 100), new CollidableComponent(true), new HealthIntComponent(2), new NameComponent("orangeAlien"))
                .build();
    }
    // template for the player ship
    @Spawns("player")
    public Entity newPlayer(SpawnData data){
        Texture texture = texture("ship.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(40);
        return entityBuilder(data)
                .type(EntityType.PLAYER)
                .viewWithBBox(texture)
                .with(new ShipComponent())
                .build();
    }
    // template for the ship projectile
    @Spawns("shipBullet")
    public Entity newShipBullet(SpawnData data){
        Texture texture = texture("shipbullet.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(40);
        return entityBuilder(data)
                .type(EntityType.PROJECTILE)
                .viewWithBBox(texture)
                .with(new shipBullet(1.25), new CollidableComponent(true))
                .build();

    }
    // template for the attack up powerup
    @Spawns("attackUp")
    public Entity newAttackUp(SpawnData data){
        Texture texture = texture("attackUp.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(40);
        return entityBuilder(data)
                .type(EntityType.POWERUP)
                .viewWithBBox(texture)
                .with(new AttackPowerUp(), new CollidableComponent(true), new NameComponent("attackUp"))
                .build();

    }
    // template for the extra shot powerup
    @Spawns("extraShot")
    public Entity newExtraShot(SpawnData data){
        Texture texture = texture("extrashot.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(40);
        return entityBuilder(data)
                .type(EntityType.POWERUP)
                .viewWithBBox(texture)
                .with(new PowerUpComponent(), new CollidableComponent(true), new NameComponent("extraShot"))
                .build();

    }
    // the explosion that is spawned on death for aliens
    @Spawns("explosion")
    public Entity newExplosion(SpawnData data){
        Texture texture = texture("explosion.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(40);
        return entityBuilder(data)
                .type(EntityType.PROJECTILE)
                .viewWithBBox(texture)
                .build();

    }
    // the background for the game
    @Spawns("background")
    public Entity newBackground(SpawnData data){
        Texture texture = texture("space.png");
        return entityBuilder(data)
                .view(texture)
                .build();
    }


}
