package com.bdn.jfxinvaders;

import com.almasb.fxgl.entity.component.Component;

public class NameComponent extends Component {
    String name;

    public NameComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
