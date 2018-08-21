package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Assets;

public class Enemy extends GameObject {

    private float speed = 600f;

    public Enemy() {
        this.setImage(Assets.manager.get("enemy.png", Texture.class));
        this.setWidth(90);
        this.setHeight(90);
        reset();
    }

    public void update(float delta) {
        getPos().add(0, -speed * delta);
    }

    public boolean isOffScreen() {
        return (getPos().y < -100);
    }

    public void reset() {
        setPos(new Vector2((float) (Math.random() * MainGame.WIDTH - getWidth()), MainGame.HEIGHT + getHeight()));
    }
}
