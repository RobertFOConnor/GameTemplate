package com.yellowbytestudios.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.media.Fonts;
import com.yellowbytestudios.media.Sounds;

public class TextButton extends UIElement {

    OnTouchListener onTouchListener;
    private String name;
    private BitmapFont font = Fonts.getFont(Fonts.size.MEDIUM);

    public TextButton() {
        super(0, 0);
        setName("Button");
        setupBounds();
        setOnTouchListener(new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {

            }
        });
        center();
    }

    public TextButton(String name, float x, float y, OnTouchListener onTouchListener) {
        super(x, y);
        setName(name);
        setupBounds();
        setOnTouchListener(onTouchListener);
        center();
    }

    private void setupBounds() {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, name);
        setWidth(layout.width);
        setHeight(layout.height);
    }

    public void render(SpriteBatch sb, ShapeRenderer sr) {
        if (isVisible()) {
            font.setColor(getSprite().getColor());
            font.draw(sb, getName(), getX(), getY());
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

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    private void center() {
        setPos(getX() - width / 2, getY());
    }

    public float getWidth() {
        return width;
    }
}
