package com.yellowbytestudios.ui;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.media.Fonts;
import com.yellowbytestudios.media.Sounds;

public class TextButton extends UIElement {

    OnTouchListener onTouchListener;
    private String name;
    private float width;
    private float height;

    public TextButton(String name, Vector2 pos, OnTouchListener onTouchListener) {
        super(pos.x, pos.y);
        setName(name);
        setupBounds();
        setOnTouchListener(onTouchListener);
        center();
    }

    private void setupBounds() {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(Fonts.GUIFont, name);
        width = layout.width;
        height = layout.height;
    }

    public void render(SpriteBatch sb) {
        if (isVisible()) {
            Fonts.GUIFont.draw(sb, getName(), getX(), getY());
        }
    }

    Rectangle getBounds() {
        return new Rectangle(getX(), getY() - height, width, height);
    }

    public void checkTouch(Vector2 touch) {
        if (getBounds().contains(touch) && isVisible()) {
            onTouchListener.onTouch(touch);
            Sounds.play("sound/click.wav");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    private void center() {
        setPos(getX() - width / 2, getY());
    }
}
