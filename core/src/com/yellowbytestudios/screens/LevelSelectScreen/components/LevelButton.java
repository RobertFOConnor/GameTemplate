package com.yellowbytestudios.screens.LevelSelectScreen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.media.Assets;
import com.yellowbytestudios.media.Fonts;
import com.yellowbytestudios.media.Sounds;
import com.yellowbytestudios.screens.GameScreen;
import com.yellowbytestudios.screens.ScreenManager;
import com.yellowbytestudios.ui.UIElement;

public class LevelButton extends UIElement {

    public static final float WIDTH = 300;
    public static final float HEIGHT = 200;

    private Texture backgroundImage;
    private BitmapFont font = Fonts.getFont(Fonts.size.LARGE);
    private float textX, textY;
    private String levelNumber;

    public LevelButton(float x, float y, String levelNumber) {
        super(x, y);
        this.backgroundImage = Assets.getTexture("ship.png");
        this.width = WIDTH;
        this.height = HEIGHT;
        this.levelNumber = levelNumber;
        setupTextBounds(levelNumber);
    }

    private void setupTextBounds(String text) {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, text);
        textX = layout.width;
        textY = layout.height;
    }

    private Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public boolean checkTouch(Vector2 touch) {
        if (getBounds().contains(touch) && isVisible()) {
            ScreenManager.setScreen(new GameScreen());
            Sounds.play("sound/click.wav");
            return true;
        }
        return false;
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        sb.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.PURPLE);
        sr.rect(getX(), getY(), getWidth(), getHeight());
        sr.end();
        sb.begin();
        //sb.draw(backgroundImage, getX(), getY(), width, height);
        font.draw(sb, levelNumber, getX() + width / 2 - textX / 2, getY() + getHeight() / 2 + textY / 2);
    }
}
