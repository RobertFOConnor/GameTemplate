package com.yellowbytestudios.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.media.Sounds;
import com.yellowbytestudios.ui.OnTouchListener;
import com.yellowbytestudios.ui.TextButton;
import com.yellowbytestudios.ui.TextView;
import com.yellowbytestudios.ui.ToggleButton;

import static com.yellowbytestudios.ui.Metrics.getBottomCenter;
import static com.yellowbytestudios.ui.Metrics.getCenter;
import static com.yellowbytestudios.ui.Metrics.getTopCenter;

public class SettingsScreen extends Screen {

    @Override
    public void create() {
        super.create();

        TextView tv = new TextView("Here you can alter the settings for the game.", getTopCenter().add(0, -100));
        tv.center();
        UIElements.add(tv);

        ToggleButton musicButton = new ToggleButton("Music: on", "Music: off", getCenter().add(0, 100), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                Sounds.toggleMusic();
            }
        }, Sounds.isMusicEnabled());

        ToggleButton soundButton = new ToggleButton("Sounds: on", "Sounds: off", getCenter().add(0, -100), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                Sounds.toggleSound();
            }
        }, Sounds.isSoundFXEnabled());

        TextButton backButton = new TextButton("Go Back", getBottomCenter().add(0, 200), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                goBack();
            }
        });

        UIElements.add(musicButton);
        UIElements.add(soundButton);
        UIElements.add(backButton);
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
        ScreenManager.setScreen(new TitleScreen());
    }
}
