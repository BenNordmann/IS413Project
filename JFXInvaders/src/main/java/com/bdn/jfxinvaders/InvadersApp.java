package com.bdn.jfxinvaders;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
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
        Music backgroundMusic = FXGL.getAssetLoader().loadMusic("backgroundmusic.mp3");
        FXGL.getAudioPlayer().loopMusic(backgroundMusic);
        spawnBackground();

        int waveSize = 5;
        int waveTimer = 15;
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
                    } else if(enemy.getComponent(NameComponent.class).getName().equals("orangeAlien")){
                        enemy.getComponent(OrangeInvader.class).die();
                        score.addScore(enemy.getComponent(OrangeInvader.class).getPoints());
                        System.out.println(score.getScore());
                    }

                }
                healthIntComponent.setValue(healthIntComponent.getValue() - 1);
            }

        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.POWERUP) {
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

        });

    }
    private void spawnBackground(){
        spawn("background", 0, 0);


    }

    private void spawnPlayer(){
        player = spawn("player", getAppWidth()/2, getAppHeight() - 40);
        shipComponent = player.getComponent(ShipComponent.class);

    }

    private void spawnGreenAlien(int wSize){
        double xSpawn;
        for (double i = 1; i <= wSize; i++) {
            xSpawn = getAppWidth() * (i/5);
            spawn("greenAlien", xSpawn - 100, getAppHeight()/10);
        }
    }

    private void spawnGreenAlienContinuous(int wSize, int wTimer){

        run(() -> {
            double xSpawn;
            for (double i = 1; i <= wSize; i++) {
                xSpawn = getAppWidth() * (i/5);
                spawn("greenAlien", xSpawn - 100, getAppHeight()/10);
            }
            return null;
        }, Duration.seconds(wTimer));
    }
    private void spawnOrangeAlien(int wSize){
        double xSpawn;
        for (double i = 1; i <= wSize; i++) {
            xSpawn = getAppWidth() * (i/5);
            spawn("orangeAlien", xSpawn - 100, getAppHeight()/10);
        }
    }

    private void spawnOrangeAlienContinuous(int wSize, int wTimer){

        run(() -> {
            double xSpawn;
            for (double i = 1; i <= wSize; i++) {
                xSpawn = getAppWidth() * (i/5);
                spawn("orangeAlien", xSpawn - 100, getAppHeight()/10);
            }
            return null;
        }, Duration.seconds(wTimer));
    }

    private void spawnRedAlien(){
            spawn("redAlien", getAppWidth()/2, getAppHeight()/10);
        }


    private void spawnRedAlienContinuous(){

        run(() -> {
            spawn("redAlien", getAppWidth()/2, getAppHeight()/10);
            return null;
        }, Duration.seconds(30));
    }

    private void spawnAttackUp(){
        spawn("attackUp", getAppWidth() - 40, getAppHeight() - 40);
    }
    private void spawnExtraShot(){
        spawn("extraShot", getAppWidth() - 40, getAppHeight() - 40);
    }

    public static void main (String[]args){
        launch(args);
    }
}
