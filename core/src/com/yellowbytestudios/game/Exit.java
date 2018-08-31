package com.yellowbytestudios.game;

import com.yellowbytestudios.game.tile.TileManager;
import com.yellowbytestudios.media.Assets;

public class Exit extends GameObject {

    public Exit(float x, float y) {
        super(Assets.getTexture("ship.png"));
        setPos(x, y);
        this.setWidth(TileManager.getTileSize());
        this.setHeight(TileManager.getTileSize());
    }
}
