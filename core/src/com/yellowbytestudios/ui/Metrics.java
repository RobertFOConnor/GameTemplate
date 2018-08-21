package com.yellowbytestudios.ui;

import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;

public class Metrics {
    public static final float CENTER_X = MainGame.WIDTH / 2;
    public static final float CENTER_Y = MainGame.HEIGHT / 2;

    public static float H_MARGIN = 100;
    public static float V_MARGIN = 100;

    public static Vector2 getCenter() {
        return new Vector2(CENTER_X, CENTER_Y);
    }

    public static Vector2 getBottomCenter() {
        return new Vector2(CENTER_X, 0);
    }

    public static Vector2 getTopCenter() {
        return new Vector2(CENTER_X, MainGame.HEIGHT);
    }

    public static Vector2 getTopLeft() {
        return new Vector2(0, MainGame.HEIGHT);
    }

    public static Vector2 getTopRight() {
        return new Vector2(MainGame.WIDTH, MainGame.HEIGHT);
    }
}
