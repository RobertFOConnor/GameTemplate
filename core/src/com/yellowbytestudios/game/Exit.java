package com.yellowbytestudios.game;

import com.yellowbytestudios.media.Assets;

public class Exit extends GameObject {

    public Exit(float x, float y) {
        super(Assets.getTexture("ship.png"));
        setPos(x, y);
        this.setWidth(80);
        this.setHeight(80);
    }
}
