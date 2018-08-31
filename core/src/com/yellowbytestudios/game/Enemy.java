package com.yellowbytestudios.game;

import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Assets;

public class Enemy extends GameObject {

    private int health = 3;
    private float speed = 500f;

    public Enemy() {
        super(Assets.getTexture("ship.png"));
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
