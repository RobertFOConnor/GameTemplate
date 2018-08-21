package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Assets;

public class Bullet extends GameObject {
    private float speed = 1000f;

    public Bullet(Vector2 pos) {
        this.setImage(Assets.manager.get("ship.png", Texture.class));
        setPos(pos);
        this.setWidth(20);
        this.setHeight(70);
    }

    public void update(float delta) {
        getPos().add(0, speed * delta);
    }

    public boolean isOffScreen() {
        return (getPos().y > MainGame.HEIGHT);
    }
}
