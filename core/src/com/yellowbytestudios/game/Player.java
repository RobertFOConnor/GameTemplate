package com.yellowbytestudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.game.input.SwipeGesture;
import com.yellowbytestudios.game.tile.TileManager;
import com.yellowbytestudios.media.Assets;

public class Player extends GameObject {

    private float speed = 2000f;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    public Player(String name) {
        super(Assets.manager.get("ship.png", Texture.class));
        this.setName(name);

        this.setPos(960, 80);
        this.setWidth(80);
        this.setHeight(80);

        Gdx.input.setInputProcessor(new SwipeGesture(new SwipeGesture.DirectionListener() {

            @Override
            public void onUp() {
                if (!isMoving()) {
                    movingUp = true;
                }
            }

            @Override
            public void onRight() {
                if (!isMoving()) {
                    movingRight = true;
                }
            }

            @Override
            public void onLeft() {
                if (!isMoving()) {
                    movingLeft = true;
                }
            }

            @Override
            public void onDown() {
                if (!isMoving()) {
                    movingDown = true;
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
        float tileSize = TileManager.getTileSize();
        setPos((Math.round(getX() / tileSize) * tileSize), (Math.round(getY() / tileSize)) * tileSize);
    }

    private boolean isMoving() {
        return movingDown || movingUp || movingRight || movingLeft;
    }

    public void update(float delta) {

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
        System.out.println("POS: " + newXPos + ", " + newYPos);

    }

    public float getBulletStartX() {
        return getX() + (getWidth() / 2 - 10);
    }

    public float getBulletStartY() {
        return getY();
    }

}
