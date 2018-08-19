package com.yellowbytestudios.ui;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.media.Fonts;

public class TextButton extends UIElement {

    private OnTouchListener onTouchListener;
    private String name;
    private Vector2 pos;
    private float width;
    private float height;

    public TextButton(String name, Vector2 pos, OnTouchListener onTouchListener) {
        setName(name);
        setPos(pos);
        setupBounds();
        setOnTouchListener(onTouchListener);
    }

    private void setupBounds() {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(Fonts.GUIFont, name);
        width = layout.width;
        height = layout.height;
    }

    public void render(SpriteBatch sb) {
        Fonts.GUIFont.draw(sb, name, pos.x, pos.y);
    }

    private Rectangle getBounds() {
        return new Rectangle(pos.x, pos.y - height, width, height);
    }

    public void checkTouch(Vector2 touch) {
        if (getBounds().contains(touch)) {
            onTouchListener.onTouch(touch);
        }
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

    private void setPos(Vector2 pos) {
        this.pos = pos;
    }

    private void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    public void center() {
        Vector2 currPos = getPos();
        currPos.set(currPos.x - width/2, currPos.y);
    }
}
