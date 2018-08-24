package com.yellowbytestudios.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.game.tile.TileManager;

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
        player.setPos(960, 80);
    }

    public void update(float delta) {
        if (!paused && !gameOver) {
            player.update(delta);
            tileManager.update(player);
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
