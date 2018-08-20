package com.yellowbytestudios.ui;

import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;

public class Metrics {
    public static float H_CENTER = MainGame.WIDTH / 2;
    public static float V_CENTER = MainGame.HEIGHT / 2;

    public static float H_MARGIN = 100;
    public static float V_MARGIN = 100;

    public static Vector2 getCenter() {
        return new Vector2(H_CENTER, V_CENTER);
    }

    public static Vector2 getBottomCenter() {
        return new Vector2(H_CENTER, 0);
    }

    public static Vector2 getTopCenter() {
        return new Vector2(H_CENTER, MainGame.HEIGHT);
    }

    public static Vector2 getTopLeft() {
        return new Vector2(0, MainGame.HEIGHT);
    }

    public static Vector2 getTopRight() {
        return new Vector2(MainGame.WIDTH, MainGame.HEIGHT);
    }
}
