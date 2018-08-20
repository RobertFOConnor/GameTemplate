package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Assets;


public class Player extends GameObject {

    private float speed = 700f;

    public Player(String name) {
        this.setName(name);
        this.setImage(Assets.manager.get("ship.png", Texture.class));

        this.setPos(new Vector2(MainGame.WIDTH / 2, MainGame.HEIGHT / 9));
        this.setWidth(100);
        this.setHeight(100);
    }

    public void onTouch(Vector2 touch, float delta) {

        Vector2 currPos = this.getPos();
        float newXPos;

        if (touch.x < MainGame.WIDTH / 2) {//left side of screen
            newXPos = currPos.x - speed * delta;
        } else {
            newXPos = currPos.x + speed * delta;
        }

        if (withinScreenBounds(newXPos)) {
            currPos.set(newXPos, currPos.y);
        }
    }

    private boolean withinScreenBounds(float xPos) {
        return (xPos > 0 && xPos < MainGame.WIDTH - 100);
    }
}
