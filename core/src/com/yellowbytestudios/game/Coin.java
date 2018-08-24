package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.yellowbytestudios.media.Assets;

public class Coin extends GameObject {

    public static int SIZE = 20;

    public Coin(float x, float y) {
        super(Assets.manager.get("ship.png", Texture.class));
        setPos(x, y);
        this.setWidth(SIZE);
        this.setHeight(SIZE);
    }
}