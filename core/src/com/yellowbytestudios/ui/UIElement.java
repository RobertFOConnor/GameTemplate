package com.yellowbytestudios.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class UIElement {
    public abstract void render(SpriteBatch sb);
    public abstract void checkTouch(Vector2 touch);
}
