package com.yellowbytestudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.media.Assets;
import com.yellowbytestudios.media.Sounds;
import com.yellowbytestudios.ui.OnTouchListener;
import com.yellowbytestudios.ui.TextButton;
import com.yellowbytestudios.ui.UIAnimator;

import static com.yellowbytestudios.ui.Metrics.getCenter;

public class TitleScreen extends Screen {

    Sprite s;

    @Override
    public void create() {
        super.create();

        s = new Sprite(Assets.manager.get("ship.png", Texture.class));
        s.setPosition(340, getCenter().y+100);
        UIAnimator.applyAnimation(s, s.getX(), -100, 50);


        TextButton startButton = new TextButton("Start Game", getCenter().add(0, -200), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                ScreenManager.setScreen(new GameScreen());
            }
        });
        UIAnimator.applyAnimation(startButton.getSprite(), -1000, startButton.getY(), 50);
        UIElements.add(startButton);

        TextButton optionsButton = new TextButton("Options", getCenter().add(0, -400), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                ScreenManager.setScreen(new SettingsScreen());
            }
        });
        UIAnimator.applyAnimation(optionsButton.getSprite(), 1000, optionsButton.getY(), 50);
        UIElements.add(optionsButton);

        TextButton exitButton = new TextButton("Exit", getCenter().add(0, -600), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                goBack();
            }
        });
        UIAnimator.applyAnimation(exitButton.getSprite(), -1000, exitButton.getY(), 50);
        UIElements.add(exitButton);

        UIAnimator.startAnimation();

        Sounds.setMusic("music/title_music.mp3");
    }

    @Override
    public void update(float delta) {
       super.update(delta);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        sb.begin();
        s.draw(sb);
        sb.end();
    }

    @Override
    public void resize(int w, int h) {
        camera.resize();
    }

    @Override
    public void dispose() {

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
