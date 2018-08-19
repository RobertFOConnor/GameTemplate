package com.yellowbytestudios.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Button {

    private String name;
    private Vector2 pos;
    private Texture image;

    public void render(SpriteBatch sb) {
        sb.draw(image, pos.x, pos.y);
    }

    public Rectangle getBounds() {
        return new Rectangle(pos.x, pos.y, image.getWidth(), image.getHeight());
    }

    public boolean checkTouch(Vector2 touch) {
        return getBounds().contains(touch);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }
}
