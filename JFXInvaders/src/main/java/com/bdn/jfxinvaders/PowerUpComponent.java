package com.bdn.jfxinvaders;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
// parent class for all powerups
public class PowerUpComponent extends Component {
    double attackSpeedBoost;
    double damageBoost;
    int speed = 10;
    // once upon a time powerups had movement, maybe this could be overriden with something useful
    public void onUpdate(double tpf){

    }
}
