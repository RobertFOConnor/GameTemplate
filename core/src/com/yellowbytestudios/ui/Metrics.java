package com.yellowbytestudios.ui;

import com.yellowbytestudios.MainGame;

public class Metrics {
    public static final float CENTER_X = MainGame.WIDTH / 2;
    public static final float CENTER_Y = MainGame.HEIGHT / 2;

    public static float marginX(int size) {
        return (MainGame.WIDTH/40) * size;
    }

    public static float marginY(int size) {
        return (MainGame.HEIGHT/80) * size;
    }
}
