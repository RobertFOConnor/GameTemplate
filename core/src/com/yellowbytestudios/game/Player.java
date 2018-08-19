package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;


public class Player extends GameObject {

    public Player(String name) {
        this.setName(name);
        this.setImage(new Texture("ship.png"));

        this.setPos(new Vector2(MainGame.WIDTH / 2, 0));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(this.getImage(), this.getPos().x, this.getPos().y, 100, 100);
    }

    public void update() {
        Vector2 currPos = this.getPos();
        currPos.set(currPos.x, currPos.y + 1f);
    }

    public void onTouch(Vector2 touch) {
        if (checkTouch(touch)) {
        }
    }
}
