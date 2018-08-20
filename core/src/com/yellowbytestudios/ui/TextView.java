package com.yellowbytestudios.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Fonts;

public class TextView extends UIElement {

    private String name;
    private Vector2 pos;

    public TextView(String name, Vector2 pos) {
        setName(name);
        setPos(pos);
    }

    public void render(SpriteBatch sb) {
        Fonts.detailsFont.draw(sb, name, pos.x, pos.y, MainGame.WIDTH - 200, Align.center, true);
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

    private void setPos(Vector2 pos) {
        this.pos = pos;
    }

}
