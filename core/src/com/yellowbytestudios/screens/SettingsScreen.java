package com.yellowbytestudios.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.media.Sounds;
import com.yellowbytestudios.ui.OnTouchListener;
import com.yellowbytestudios.ui.TextButton;
import com.yellowbytestudios.ui.TextView;
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

        createMenuButton("Music: OFF", getCenter().add(0, 100), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                if (Sounds.toggleMusic()) {
                    updateButtonText(1, "Music: ON");
                } else {
                    updateButtonText(1, "Music: OFF");
                }
            }
        });

        createMenuButton("Sound: ON", getCenter().add(0, -100), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                if (Sounds.toggleSound()) {
                    updateButtonText(2, "Sound: ON");
                } else {
                    updateButtonText(2, "Sound: OFF");
                }
            }
        });

        createMenuButton("Go Back", getBottomCenter().add(0, 200), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                goBack();
            }
        });
    }

    private void updateButtonText(int index, String text) {
        ((TextButton) UIElements.get(index)).setName(text);
    }

    private void createMenuButton(String name, Vector2 pos, OnTouchListener onTouchListener) {
        TextButton button = new TextButton(name, pos, onTouchListener);
        button.center();
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
        ScreenManager.setScreen(new TitleScreen());
    }
}
