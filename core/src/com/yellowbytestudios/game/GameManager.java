package com.yellowbytestudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.game.tile.TileManager;
import com.yellowbytestudios.screens.ScreenManager;

import java.util.ArrayList;
import java.util.Timer;

public class GameManager {

    private OrthographicCamera camera;
    private TileManager tileManager;
    private Player player;
    private boolean paused = false;
    private int score = 0;
    private boolean gameOver = false;

    public GameManager() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
        camera.update();

        tileManager = new TileManager(camera);
        player = new Player("Phil");
        player.setPos(tileManager.getMapWidth() / 2, tileManager.getMapHeight() / 2);
    }

    public void update(float delta) {
        if (!paused && !gameOver) {

            if (Gdx.input.isTouched(0)) {
                Vector2 touch = ScreenManager.getCurrentScreen().getTouchPos();
                player.onTouch(touch, delta);
            }
            camera.position.set(player.getX(), player.getY(), 0);
            camera.update();
        }
    }

    public void render(SpriteBatch sb) {
        tileManager.render();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        player.render(sb);
        sb.end();
    }

    public void togglePause() {
        paused = !paused;
    }

    public int getScore() {
        return score;
    }

    public void dispose() {
        player = null;
        gameOver = true;
    }
}
