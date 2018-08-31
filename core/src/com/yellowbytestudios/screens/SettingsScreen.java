package com.yellowbytestudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Sounds;
import com.yellowbytestudios.ui.OnTouchListener;
import com.yellowbytestudios.ui.ScrollView;
import com.yellowbytestudios.ui.TextButton;
import com.yellowbytestudios.ui.TextView;
import com.yellowbytestudios.ui.ToggleButton;

import static com.yellowbytestudios.ui.Metrics.CENTER_X;
import static com.yellowbytestudios.ui.Metrics.CENTER_Y;

public class SettingsScreen extends Screen {

    ScrollView sv;
    String headerText = "Here you can alter the settings for the game. In fact, you can change the sound and music settings from right here. It's true! Just tap the text below to find out how very easy it truly is.";

    @Override
    public void create() {
        super.create();

        sv = new ScrollView(0, 300, MainGame.WIDTH, MainGame.HEIGHT/2, ScrollView.HORIZONTAL);

        TextView tv = new TextView(headerText, CENTER_X, MainGame.HEIGHT - 100, MainGame.WIDTH - 200);
        tv.center();
        UIElements.add(tv);

        ToggleButton musicButton = new ToggleButton("Music: on", "Music: off", CENTER_X, CENTER_Y + 100, new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                Sounds.toggleMusic();
            }
        }, Sounds.isMusicEnabled());

        ToggleButton soundButton = new ToggleButton("Sounds: on", "Sounds: off", CENTER_X, CENTER_Y - 100, new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                Sounds.toggleSound();
            }
        }, Sounds.isSoundFXEnabled());

        TextButton backButton = new TextButton("Go Back", CENTER_X, 200, new OnTouchListener() {
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
        sv.update();
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        super.render(sb, sr);
        sv.render(sb, sr);
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
        ScreenManager.setScreen(new TitleScreen());
    }
}
