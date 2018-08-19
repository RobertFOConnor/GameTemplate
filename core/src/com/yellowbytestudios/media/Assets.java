package com.yellowbytestudios.media;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static AssetManager manager = new AssetManager();
    public static final String SHIP = "ship.png";

    public static void load() {// Loads Assets

        manager = new AssetManager();

        manager.load(SHIP, Texture.class);
    }

    public static void dispose() {
        manager.dispose();
    }

    public static boolean update() {
        return manager.update();
    }
}
