package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Assets;


public class Player extends GameObject {

    private float speed = 5f;

    public Player(String name) {
        this.setName(name);
        this.setImage(Assets.manager.get("ship.png", Texture.class));

        this.setPos(new Vector2(MainGame.WIDTH / 2, MainGame.HEIGHT / 9));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(this.getImage(), this.getPos().x, this.getPos().y, 100, 100);
    }

    public void update() {

    }

    public void onTouch(Vector2 touch) {

        Vector2 currPos = this.getPos();

        if (touch.x < MainGame.WIDTH / 2) {//left side of screen
            currPos.set(currPos.x - speed, currPos.y);
        } else {
            currPos.set(currPos.x + speed, currPos.y);
        }
    }
}
