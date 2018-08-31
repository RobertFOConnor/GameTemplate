package com.yellowbytestudios.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.utils.DeviceTypes;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1080 / 3;
        config.height = 1920 / 3;
        new LwjglApplication(new MainGame(DeviceTypes.DESKTOP), config);
    }
}
