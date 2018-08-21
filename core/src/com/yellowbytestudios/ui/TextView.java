package com.yellowbytestudios.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Fonts;

public class TextView extends UIElement {

    private String name;
    private Vector2 pos;
    private float width = MainGame.WIDTH - 200;

    public TextView(String name, Vector2 pos) {
        super(pos.x, pos.y);
        setName(name);
        setPos(pos);
    }

    public void render(SpriteBatch sb) {
        if (isVisible()) {
            Fonts.detailsFont.draw(sb, name, pos.x, pos.y, width, Align.center, true);
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
    }

    public Vector2 getPos() {
        return pos;
    }

    private void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public void center() {
        Vector2 currPos = getPos();
        currPos.set(currPos.x - width / 2, currPos.y);
    }
}
