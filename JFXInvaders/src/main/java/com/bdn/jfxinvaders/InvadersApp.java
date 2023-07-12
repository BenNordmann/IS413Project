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


import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

// handles all of the game's core functionalities
// loads all of the games assets
// preps the games window and ui
public class InvadersApp extends GameApplication{
    @Override
    protected void initSettings(GameSettings gameSettings) {
        // Sets the game window size, title, and a main menu for the game
        gameSettings.setTitle("JFX Invaders");
        gameSettings.setWidth(640);
        gameSettings.setHeight(480);
        gameSettings.setMainMenuEnabled(true);
    }
    // Prepared the player entity and shipComponent for use in handlers that are no longer here
    private Entity player;
    private ShipComponent shipComponent;

    @Override
    protected void initInput() {
        // assigns player input to the arrow keys and "ad", space to shoot
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
    // loads game assets
    @Override
    protected void initGame() {
        // creates an instance of InvaderFactory entityfactory and attaches it to the gameworld
        getGameWorld().addEntityFactory(new InvaderFactory());
        // sets the background music and loops it
        Music backgroundMusic = FXGL.getAssetLoader().loadMusic("backgroundmusic.mp3");
        FXGL.getAudioPlayer().loopMusic(backgroundMusic);
        // spawns the background
        spawnBackground();
        // sets the size of each alien wave and the time between waves
        int waveSize = 5;
        int waveTimer = 15;
        // instantiates the score handler
        ScoreHandler score = new ScoreHandler();
        // spawns the player and the subsequent waves
        spawnPlayer();
        spawnGreenAlien(waveSize);
        gameOver(score.getScore());
        spawnGreenAlienContinuous(waveSize, waveTimer);
        // spawns orange aliens after 30 seconds
        runOnce(() -> {
            spawnOrangeAlienContinuous(waveSize, waveTimer);
            return null;
        }, Duration.seconds(30));
        // spawns a red alien after 30 seconds
        runOnce(() -> {
            spawnRedAlienContinuous();
            return null;
        }, Duration.seconds(60));
        // collisionhandler for projectiles and aliens
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PROJECTILE, EntityType.ENEMY) {
            @Override
            protected void onCollisionBegin(Entity projectile, Entity enemy) {
                // allows us to access the health component
                HealthIntComponent healthIntComponent = enemy.getComponent(HealthIntComponent.class);
                // on hit deletes the projectile
                projectile.removeFromWorld();
                // when an enemy is at 0 hp, checks what type of enemy it is and then kills the enemy
                if(healthIntComponent.getValue() <= 0){
                    if(enemy.getComponent(NameComponent.class).getName().equals("greenAlien")){
                        enemy.getComponent(GreenInvader.class).die();
                        score.addScore(enemy.getComponent(GreenInvader.class).getPoints());
                        System.out.println(score.getScore());
                    } else if(enemy.getComponent(NameComponent.class).getName().equals("orangeAlien")){
                        enemy.getComponent(OrangeInvader.class).die();
                        score.addScore(enemy.getComponent(OrangeInvader.class).getPoints());
                        System.out.println(score.getScore());
                    }  else if(enemy.getComponent(NameComponent.class).getName().equals("redAlien")){
                        enemy.getComponent(RedInvader.class).die();
                        score.addScore(enemy.getComponent(RedInvader.class).getPoints());
                        System.out.println(score.getScore());
                    }

                }
                // prevented loops that was occurring before
                healthIntComponent.setValue(healthIntComponent.getValue() - 1);
            }

        });
        // collision handler for powerups
        getPhysicsWorld().addCollisionHandler(new PowerUpHandler() {


        });
    // methods for spawning entities
    }
    private void spawnBackground(){
        spawn("background", 0, 0);


    }

    private void spawnPlayer(){
        player = spawn("player", getAppWidth()/2, getAppHeight() - 40);
        shipComponent = player.getComponent(ShipComponent.class);

    }
    // the (i/5) value staggered the aliens in a way similar to traditional space invaders
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
            spawn("orangeAlien", xSpawn - 180, getAppHeight()/10);
        }
    }

    private void spawnOrangeAlienContinuous(int wSize, int wTimer){

        run(() -> {
            double xSpawn;
            for (double i = 1; i <= wSize; i++) {
                xSpawn = getAppWidth() * (i/5);
                spawn("orangeAlien", xSpawn - 180, getAppHeight()/10);
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
    // checks if aliens have reached the bottom of the screen, if they have displays your score and a game over message
    // if it worked
    private void gameOver(int score) {
        run(() -> {
            List<Entity> enemies = getGameWorld().getEntitiesByType(EntityType.ENEMY);

            for (Entity enemy : enemies) {
                double enemyY = enemy.getY();
                if (enemyY >= getAppHeight()) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("Game Over!");
                    builder.append("Final score: ")
                            .append(score);
                    FXGL.getDialogService().showMessageBox(builder.toString(), () -> FXGL.getGameController().gotoMainMenu());
                }
            } return null;
        }, Duration.seconds(1));

    }

    public static void main (String[]args){
        launch(args);
    }
}
