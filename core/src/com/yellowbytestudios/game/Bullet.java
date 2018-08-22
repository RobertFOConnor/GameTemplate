package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Assets;

public class Bullet extends GameObject {
    private float speed = 1300f;

    public Bullet(float x, float y) {
        super(Assets.manager.get("ship.png", Texture.class));
        setPos(x, y);
        this.setWidth(20);
        this.setHeight(70);
    }

    public void update(float delta) {
        setPos(getX(), getY() + speed * delta);
    }

    public boolean isOffScreen() {
        return (getY() > MainGame.HEIGHT);
    }
}
