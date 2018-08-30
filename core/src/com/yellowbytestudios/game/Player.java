package com.yellowbytestudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.game.input.SwipeGesture;
import com.yellowbytestudios.game.tile.TileCollision;
import com.yellowbytestudios.game.tile.TileManager;
import com.yellowbytestudios.media.Assets;

public class Player extends GameObject {

    private float speed = 3000f;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    private float oldX = 0;
    private float oldY = 0;

    public Player(String name) {
        super(Assets.manager.get("ship.png", Texture.class));
        this.setName(name);

        this.setPos(960, 160);
        oldX = 0;
        oldY = 0;
        this.setWidth(80);
        this.setHeight(80);

        Gdx.input.setInputProcessor(new SwipeGesture(new SwipeGesture.DirectionListener() {

            @Override
            public void onUp() {
                if (!isMoving()) {
                    movingUp = true;
                    getSprite().setRotation(0);
                }
            }

            @Override
            public void onRight() {
                if (!isMoving()) {
                    movingRight = true;
                    getSprite().setRotation(-90);
                }
            }

            @Override
            public void onLeft() {
                if (!isMoving()) {
                    movingLeft = true;
                    getSprite().setRotation(90);
                }
            }

            @Override
            public void onDown() {
                if (!isMoving()) {
                    movingDown = true;
                    getSprite().setRotation(180);
                }
            }
        }));
    }

    @Override
    public void onCollide() {
        resetMoving();
    }

    public void resetMoving() {
        movingLeft = false;
        movingRight = false;
        movingUp = false;
        movingDown = false;
        oldX = getX();
        oldY = getY();
        float tileSize = TileManager.getTileSize();
        setPos((Math.round(getX() / tileSize) * tileSize), (Math.round(getY() / tileSize)) * tileSize);
    }

    private boolean isMoving() {
        return movingDown || movingUp || movingRight || movingLeft;
    }

    public void update(float delta) {

        oldX = getX();
        oldY = getY();

        float newXPos = getX();
        float newYPos = getY();
        float velocity = speed * delta;

        if (movingLeft) {
            newXPos = getX() - velocity;
        } else if (movingRight) {
            newXPos = getX() + velocity;
        }

        if (movingDown) {
            newYPos = getY() - velocity;
        } else if (movingUp) {
            newYPos = getY() + velocity;
        }
        setPos(newXPos, newYPos);
    }

    public float getBulletStartX() {
        return getX() + (getWidth() / 2 - 10);
    }

    public float getBulletStartY() {
        return getY();
    }

    public float getOldX() {
        return oldX;
    }

    public float getOldY() {
        return oldY;
    }

    public float[] getOldCenter() {
        return new float[]{oldX, oldY};
    }

    public float[] getCenter() {
        return new float[]{getX(), getY()};
    }
}
