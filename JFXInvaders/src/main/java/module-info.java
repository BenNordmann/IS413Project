module com.bdn.jfxinvaders {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.almasb.fxgl.all;

    opens assets.textures;
    opens com.bdn.jfxinvaders to javafx.fxml;
    exports com.bdn.jfxinvaders;
}