package com.yellowbytestudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.media.Assets;
import com.yellowbytestudios.media.Sounds;
import com.yellowbytestudios.screens.LevelSelectScreen.LevelSelectScreen;
import com.yellowbytestudios.ui.LabelButton;
import com.yellowbytestudios.ui.OnTouchListener;
import com.yellowbytestudios.ui.TextButton;
import com.yellowbytestudios.ui.UIAnimator;
import com.yellowbytestudios.ui.UIImage;

import static com.yellowbytestudios.ui.Metrics.CENTER_X;
import static com.yellowbytestudios.ui.Metrics.CENTER_Y;

public class TitleScreen extends Screen {

    private LabelButton startButton;
    private TextButton optionsButton, exitButton;
    private UIImage logoImage;

    @Override
    public void create() {
        super.create();

        logoImage = new UIImage(Assets.manager.get("ship.png", Texture.class), 340, CENTER_Y + 100);

        startButton = new LabelButton("Play", CENTER_X, CENTER_Y - 100, 500, 150, 20, new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                ScreenManager.setScreen(new LevelSelectScreen());
            }
        });
        startButton.center();

        optionsButton = new TextButton("Options", CENTER_X, CENTER_Y - 400, new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                ScreenManager.setScreen(new SettingsScreen());
            }
        });
        optionsButton.center();

        exitButton = new TextButton("Exit", CENTER_X, CENTER_Y - 600, new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                goBack();
            }
        });
        exitButton.center();

        UIElements.add(logoImage);
        UIElements.add(startButton);
        UIElements.add(optionsButton);
        UIElements.add(exitButton);

        animateUI();
        Sounds.setMusic("music/title_music.mp3");
    }

    private void animateUI() {
        if (UIAnimator.ANIMATIONS_ENABLED) {
            UIAnimator.applyAnimation(logoImage, UIAnimator.BOTTOM);
            UIAnimator.applyAnimation(startButton, UIAnimator.RIGHT);
            UIAnimator.applyAnimation(optionsButton, UIAnimator.LEFT);
            UIAnimator.applyAnimation(exitButton, UIAnimator.RIGHT);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        super.render(sb, sr);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void goBack() {
        Gdx.app.exit();
    }

}
