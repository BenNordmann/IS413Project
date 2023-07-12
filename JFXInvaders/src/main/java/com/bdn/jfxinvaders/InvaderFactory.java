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

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.texture;

public class InvaderFactory implements EntityFactory {

    @Spawns("greenAlien")
    public Entity newEnemy(SpawnData data){
        Texture texture = texture("greenalien.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(40);
        return entityBuilder(data)
                .type(EntityType.ENEMY)
                .viewWithBBox("greenalien.png")
                .with(new GreenInvader(2, 40, 20, 10), new CollidableComponent(true), new HealthIntComponent(2), new NameComponent("greenAlien"))
                .build();
    }
    @Spawns("player")
    public Entity newPlayer(SpawnData data){
        Texture texture = texture("ship.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(40);
        return entityBuilder(data)
                .type(EntityType.PLAYER)
                .viewWithBBox("ship.png")
                .with(new ShipComponent())
                .build();
    }

    @Spawns("shipBullet")
    public Entity newShipBullet(SpawnData data){
        Texture texture = texture("shipbullet.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(40);
        return entityBuilder(data)
                .type(EntityType.PROJECTILE)
                .viewWithBBox("shipbullet.png")
                .with(new shipBullet(1.25), new CollidableComponent(true))
                .build();

    }
}
