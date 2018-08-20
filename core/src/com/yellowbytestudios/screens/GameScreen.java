package com.yellowbytestudios.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.game.GameManager;
import com.yellowbytestudios.ui.OnTouchListener;
import com.yellowbytestudios.ui.TextButton;

public class GameScreen extends Screen {

    private GameManager gameManager;
    private boolean paused = false;

    @Override
    public void create() {
        super.create();
        gameManager = new GameManager(camera);
        setupGUI();
    }

    private void setupGUI () {
        UIElements.add(new TextButton("Pause", new Vector2(50, MainGame.HEIGHT-20), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                paused = !paused;
            }
        }));

        UIElements.add(new TextButton("Close", new Vector2(MainGame.WIDTH-250, MainGame.HEIGHT-20), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                goBack();
            }
        }));
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (!paused) {
            gameManager.update(delta);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        gameManager.render(sb); // 1. render the game
        super.render(sb); // 2. render the GUI
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
