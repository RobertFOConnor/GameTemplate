package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Assets;

public class Enemy extends GameObject {

    private int health = 3;
    private float speed = 500f;

    public Enemy() {
        super(Assets.manager.get("enemy.png", Texture.class));
        this.setWidth(90);
        this.setHeight(90);
        reset();
    }

    public void update(float delta) {
        setPos(getX(), getY() + (-speed * delta));
    }

    public boolean isOffScreen() {
        return (getY() < -100);
    }

    public void reset() {
        setPos((float) (Math.random() * MainGame.WIDTH - getWidth() * 2) + getWidth(), MainGame.HEIGHT + getHeight());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
