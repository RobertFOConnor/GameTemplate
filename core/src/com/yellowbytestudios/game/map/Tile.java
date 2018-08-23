package com.yellowbytestudios.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yellowbytestudios.media.Assets;

public class Tile {

    private Texture texture;
    private float x, y;

    public static final float TILE_SIZE = 90;
    private float width = TILE_SIZE;
    private float height = TILE_SIZE;

    public Tile() {
        this.texture = Assets.manager.get("ship.png", Texture.class);
        this.x = 0;
        this.y = 0;
    }

    public Tile(Texture texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
    }

    public void render(SpriteBatch sb) {
        sb.draw(texture, x, y, width, height);
    }
}
