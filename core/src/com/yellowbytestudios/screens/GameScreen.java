package com.yellowbytestudios.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.game.GameManager;
import com.yellowbytestudios.media.Fonts;
import com.yellowbytestudios.ui.OnTouchListener;
import com.yellowbytestudios.ui.TextButton;
import com.yellowbytestudios.ui.TextView;

import static com.yellowbytestudios.MainGame.HEIGHT;
import static com.yellowbytestudios.MainGame.WIDTH;
import static com.yellowbytestudios.ui.Metrics.CENTER_X;
import static com.yellowbytestudios.ui.Metrics.CENTER_Y;
import static com.yellowbytestudios.ui.Metrics.marginX;
import static com.yellowbytestudios.ui.Metrics.marginY;

public class GameScreen extends Screen {

    private GameManager gameManager;
    private TextView scoreDisplay;

    @Override
    public void create() {
        super.create();
        gameManager = new GameManager(camera);
        setupGUI();
    }

    private void setupGUI() {
        TextButton pauseButton = new TextButton();
        pauseButton.setName("Pause");
        pauseButton.setPos(marginX(6), HEIGHT - marginY(2));
        pauseButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                gameManager.togglePause();
            }
        });

        TextButton closeButton = new TextButton();
        closeButton.setName("Close");
        closeButton.setPos(WIDTH - marginX(6), HEIGHT - marginY(2));
        closeButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                gameManager.togglePause();
            }
        });

        scoreDisplay = new TextView();
        scoreDisplay.setName(String.valueOf(gameManager.getScore()));
        scoreDisplay.setPos(CENTER_X, CENTER_Y + marginY(24));
        scoreDisplay.setFont(Fonts.getFont(Fonts.size.LARGE));

        UIElements.add(pauseButton);
        UIElements.add(closeButton);
        UIElements.add(scoreDisplay);
    }

    private void updateScore() {
        String currScore = String.valueOf(gameManager.getScore());
        if (!currScore.equals(scoreDisplay.getName())) {
            scoreDisplay.setName(currScore);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        gameManager.update(delta);
        updateScore();
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        gameManager.render(sb); // 1. render the game
        super.render(sb, sr); // 2. render the GUI
    }

    @Override
    public void dispose() {
        gameManager.dispose();
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
