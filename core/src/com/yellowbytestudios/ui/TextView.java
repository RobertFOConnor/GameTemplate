package com.yellowbytestudios.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Fonts;

public class TextView extends UIElement {

    private BitmapFont font = Fonts.getFont(Fonts.size.SMALL);
    private String name = "";

    public TextView(String name, float x, float y, int width) {
        super(x, y);
        setName(name);
        setWidth(width);
    }

    public TextView(String name, float x, float y) {
        super(x, y);
        setName(name);
        setupBounds();
    }

    private void setupBounds() {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, name);
        setWidth(layout.width);
        setHeight(layout.height);
    }

    public void render(SpriteBatch sb) {
        if (isVisible()) {
            font.draw(sb, name, getX(), getY(), width, Align.center, true);
        }
    }

    @Override
    public void checkTouch(Vector2 touch) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setupBounds();
        center();
    }

    public void center() {
        setPos(MainGame.WIDTH / 2 - width / 2, getY());
    }

    public void setFont(BitmapFont font) {
        this.font = font;
        setupBounds();
        center();
    }
}
