package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Assets;

public class Player extends GameObject {

    private float speed = 1000f;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    public Player(String name) {
        super(Assets.manager.get("ship.png", Texture.class));
        this.setName(name);

        this.setPos(MainGame.WIDTH / 2, MainGame.HEIGHT / 9);
        this.setWidth(100);
        this.setHeight(100);
    }

    public void onTouch(Vector2 touch, float delta) {

        float currX = getX();
        float currY = getY();

        float newXPos = currX;
        float newYPos = currY;

        movingLeft = false;
        movingRight = false;
        movingUp = false;
        movingDown = false;

        if (touch.x < MainGame.WIDTH / 3) {
            newXPos = currX - speed * delta;
            movingLeft = true;
        } else if (touch.x > MainGame.WIDTH - MainGame.WIDTH / 3) {
            newXPos = currX + speed * delta;
            movingRight = true;
        }

        if (touch.y < MainGame.HEIGHT / 3) {
            newYPos = currY - speed * delta;
            movingDown = true;
        } else if (touch.y > MainGame.HEIGHT - MainGame.HEIGHT / 3) {
            newYPos = currY + speed * delta;
            movingUp = true;
        }

        setPos(newXPos, newYPos);
    }

    public float getBulletStartX() {
        return getX() + (getWidth() / 2 - 10);
    }

    public float getBulletStartY() {
        return getY();
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }
}
