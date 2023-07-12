package com.bdn.jfxinvaders;

import com.almasb.fxgl.entity.component.Component;
// The parent class for all projectiles
public class BulletComponent extends Component {
// Instantiates speed which can be assigned later
    private double speed;

    public BulletComponent(double speed) {
        this.speed = speed;
    }
// Describes the projectiles movement
// 60 times per second the projectile will travel the distance of speed
    @Override
    public void onUpdate(double tpf) {
        entity.translateY(-speed);
    }
}
