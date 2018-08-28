package com.yellowbytestudios.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.media.Fonts;
import com.yellowbytestudios.media.Sounds;

public class LabelButton extends UIElement {

    OnTouchListener onTouchListener;
    private String name;
    private BitmapFont font = Fonts.getFont(Fonts.size.MEDIUM);
    private float textX, textY;
    private float radius;

    public LabelButton(String name, float x, float y, float width, float height, float radius, OnTouchListener onTouchListener) {
        super(x, y);
        setName(name);
        setWidth(width);
        setHeight(height);
        this.radius = radius;
        setOnTouchListener(onTouchListener);
        setupTextBounds();
        getSprite().setColor(Color.BLACK);
    }

    public void render(SpriteBatch sb, ShapeRenderer sr) {
        if (isVisible()) {
            sb.end();
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            drawBackground(sr);
            Gdx.gl.glDisable(GL20.GL_BLEND);
            sb.begin();
            font.setColor(getSprite().getColor());
            font.draw(sb, getName(), getX() + width / 2 - textX / 2, getY() - height / 2 + textY/2);
        }
    }

    private void setupTextBounds() {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, name);
        textX = layout.width;
        textY = layout.height;
    }

    Rectangle getBounds() {
        return new Rectangle(getX(), getY() - height, width, height);
    }

    public boolean checkTouch(Vector2 touch) {
        if (getBounds().contains(touch) && isVisible()) {
            onTouchListener.onTouch(touch);
            Sounds.play("sound/click.wav");
            return true;
        }
        return false;
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

    public void center() {
        setPos(getX() - width / 2, getY());
    }


    public float getWidth() {
        return width;
    }


    public void drawBackground(ShapeRenderer sr) {
        float shadowHeight = 15;
        float shadowOpacity = 0.2f;
        Color buttonColor = Color.WHITE;
        float alpha = getSprite().getColor().a;
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0, 0, 0, shadowOpacity*alpha);
        roundedRect(sr, getX(), getY() - height - shadowHeight, width, height, radius);
        sr.setColor(buttonColor.r, buttonColor.g, buttonColor.b, alpha);
        roundedRect(sr, getX(), getY() - height, width, height, radius);
        sr.end();
    }

    public void roundedRect(ShapeRenderer sr, float x, float y, float width, float height, float radius) {

        // TODO Add check for radius of half or more height / width (1 rect, 2 circles)
        if(radius <= 0) {
            sr.rect(x, y, width, height);
        } else {
            // Central rectangle
            sr.rect(x + radius, y + radius, width - 2 * radius, height - 2 * radius);

            // Four side rectangles, in clockwise order
            sr.rect(x + radius, y, width - 2 * radius, radius);
            sr.rect(x + width - radius, y + radius, radius, height - 2 * radius);
            sr.rect(x + radius, y + height - radius, width - 2 * radius, radius);
            sr.rect(x, y + radius, radius, height - 2 * radius);

            // Four arches, clockwise too
            sr.arc(x + radius, y + radius, radius, 180f, 90f);
            sr.arc(x + width - radius, y + radius, radius, 270f, 90f);
            sr.arc(x + width - radius, y + height - radius, radius, 0f, 90f);
            sr.arc(x + radius, y + height - radius, radius, 90f, 90f);
        }
    }
}