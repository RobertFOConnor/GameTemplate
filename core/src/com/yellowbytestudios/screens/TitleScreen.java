package com.yellowbytestudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.media.Sounds;
import com.yellowbytestudios.ui.OnTouchListener;
import com.yellowbytestudios.ui.TextButton;

import static com.yellowbytestudios.ui.Metrics.getCenter;

public class TitleScreen extends Screen {

    @Override
    public void create() {
        super.create();

        createMenuButton("Start Game", getCenter().add(0, -200), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                ScreenManager.setScreen(new GameScreen());
            }
        });

        createMenuButton("Options", getCenter().add(0, -400), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                ScreenManager.setScreen(new SettingsScreen());
            }
        });

        createMenuButton("Exit", getCenter().add(0, -600), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                goBack();
            }
        });

        Sounds.setMusic("music/title_music.mp3");
    }

    private void createMenuButton(String name, Vector2 pos, OnTouchListener onTouchListener) {
        TextButton button = new TextButton(name, pos, onTouchListener);
        UIElements.add(button);
    }

    @Override
    public void update(float delta) {
       super.update(delta);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
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
