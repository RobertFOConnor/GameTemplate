package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject {

    public Player(String name) {
        this.setName(name);
        this.setImage(new Texture("ship.png"));
        this.setPosition(new Vector2(0, 0));
    }
}
