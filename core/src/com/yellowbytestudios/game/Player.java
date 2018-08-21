package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Assets;


public class Player extends GameObject {

    private float speed = 700f;

    public Player(String name) {
        super(Assets.manager.get("ship.png", Texture.class));
        this.setName(name);

        this.setPos(MainGame.WIDTH / 2, MainGame.HEIGHT / 9);
        this.setWidth(100);
        this.setHeight(100);
    }

    public void onTouch(Vector2 touch, float delta) {

        float currX = getX();

        float newXPos;

        if (touch.x < MainGame.WIDTH / 2) {//left side of screen
            newXPos = currX - speed * delta;
        } else {
            newXPos = currX + speed * delta;
        }

        if (withinScreenBounds(newXPos)) {
            setPos(newXPos, getY());
        }
    }

    private boolean withinScreenBounds(float xPos) {
        return (xPos > 0 && xPos < MainGame.WIDTH - 100);
    }

    public float getBulletStartX() {
        return getX() + (getWidth()/2 - 10);
    }

    public float getBulletStartY() {
        return getY();
    }
}
