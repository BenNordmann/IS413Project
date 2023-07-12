package com.bdn.jfxinvaders;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.TimeComponent;
import com.almasb.fxgl.time.TimerAction;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.fire;

public abstract class InvaderComponent extends Component {
    int health;
    double speedX;
    double speedY;
    int points;
    int frameCounter = 0;
    public void invaderMovementX(){
        if(getEntity().getX() + (2.5 * speedX) > FXGL.getSettings().getActualWidth()){
            speedX *= -1;
            getEntity().translateX(speedX);
        } else if(getEntity().getX() + speedX <= 0){
            speedX *= -1;
            getEntity().translateX(speedX);
        }else{
            getEntity().translateX(speedX);
        }

    }

    public void invaderMovementY(){
        if(getEntity().getY() + (2.5 *speedY) <= FXGL.getSettings().getActualHeight() ){
            getEntity().translateY(+speedY);
        } else {
            entity.removeFromWorld();
        }
    }


    public void onUpdate(double tpf){
        frameCounter ++;
        if(frameCounter % 120 == 0 && frameCounter % 240 == 0){
            invaderMovementY();
            invaderMovementX();
        } else if (frameCounter % 120 == 0){
            invaderMovementX();
        }
    }

    public void die(){
        getEntity().removeFromWorld();
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public InvaderComponent() {
    }

    public InvaderComponent(int health, double speedX, double speedY, int points) {
        this.health = health;
        this.speedX = speedX;
        this.speedY = speedY;
        this.points = points;
    }
}
