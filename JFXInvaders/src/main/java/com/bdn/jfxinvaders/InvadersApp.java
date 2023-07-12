package com.bdn.jfxinvaders;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import com.bdn.jfxinvaders.ScoreHandler;


import java.util.concurrent.atomic.AtomicInteger;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;


public class InvadersApp extends GameApplication{
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("JFX Invaders");
        gameSettings.setWidth(640);
        gameSettings.setHeight(480);
        gameSettings.setMainMenuEnabled(true);
    }
    private Entity player;
    private ShipComponent shipComponent;

    @Override
    protected void initInput() {
        Input input = getInput();
         shipComponent = new ShipComponent();
        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onActionBegin() {
                shipComponent.moveLeft();
            }
        }, KeyCode.LEFT);
        input.addAction(new UserAction("Move Left Alternate") {
            @Override
            protected void onActionBegin() {
                shipComponent.moveLeft();
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onActionBegin() {
                shipComponent.moveRight();
            }
        }, KeyCode.RIGHT);

        input.addAction(new UserAction("Move Right Alternate") {
            @Override
            protected void onActionBegin() {
                shipComponent.moveRight();
            }
        }, KeyCode.D);

        input.addAction(new UserAction("Shoot") {
            @Override
            protected void onActionBegin() {
                shipComponent.shoot();
            }
        }, KeyCode.SPACE);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new InvaderFactory());
        int waveSize = 5;
        int waveTimer = 10;
        ScoreHandler score = new ScoreHandler();


        spawnPlayer();
        spawnGreenAlien(waveSize);
        spawnGreenAlienContinuous(waveSize, waveTimer);

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.ENEMY) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy) {

            }

        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PROJECTILE, EntityType.ENEMY) {
            @Override
            protected void onCollisionBegin(Entity projectile, Entity enemy) {
                HealthIntComponent healthIntComponent = enemy.getComponent(HealthIntComponent.class);
                projectile.removeFromWorld();
                if(healthIntComponent.getValue() <= 0){
                    if(enemy.getComponent(NameComponent.class).getName().equals("greenAlien")){
                        enemy.getComponent(GreenInvader.class).die();
                        score.addScore(enemy.getComponent(GreenInvader.class).getPoints());
                        System.out.println(score.getScore());
                        enemy.removeFromWorld();
                    }

                }
                healthIntComponent.setValue(healthIntComponent.getValue() - 1);
            }

        });

    }

    private void spawnPlayer(){
        player = spawn("player", getAppWidth()/2, getAppHeight() - 110);
        shipComponent = player.getComponent(ShipComponent.class);

    }

    private void spawnGreenAlien(int wSize){
        for (int i = 1; i <= wSize; i++) {
            spawn("greenAlien", getAppWidth() * (i/5), getAppHeight()/5);
        }
    }

    private void spawnGreenAlienContinuous(int wSize, int wTimer){
        run(() -> {
            for (int i = 1; i <= wSize; i++) {
                spawn("greenAlien", getAppWidth() * (i/5), getAppHeight()/5);
            }
            return null;
        }, Duration.seconds(wTimer));
    }

    public static void main (String[]args){
        launch(args);
    }
}
