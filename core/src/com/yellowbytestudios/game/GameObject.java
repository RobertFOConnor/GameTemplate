package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

    private int id;
    private String name;
    private Sprite sprite;
    private float width;
    private float height;
    private Rectangle bounds;

    public GameObject(Texture image) {
        sprite = new Sprite(image);
        bounds = new Rectangle();
    }

    public void render(SpriteBatch sb) {
        sb.draw(sprite, sprite.getX(), sprite.getY(), width, height);

    }

    public Rectangle getBounds() {
        bounds.set(sprite.getX(), sprite.getY(), width, height);
        return bounds;
    }

    public boolean checkTouch(Vector2 touch) {
        return getBounds().contains(touch);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getX2() {
        return sprite.getX()+width;
    }

    public float getY() {
        return sprite.getY();
    }

    public float getY2() {
        return sprite.getY() + height;
    }

    public void setPos(float x, float y) {
        sprite.setPosition(x, y);
    }

    public Texture getImage() {
        return sprite.getTexture();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
