package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.yellowbytestudios.game.GameObject;
import com.yellowbytestudios.media.Assets;

public class Exit extends GameObject {

    public Exit(float x, float y) {
        super(Assets.manager.get("ship.png", Texture.class));
        setPos(x, y);
        this.setWidth(80);
        this.setHeight(80);
    }
}
