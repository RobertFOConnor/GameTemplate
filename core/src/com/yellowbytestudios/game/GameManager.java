package com.yellowbytestudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.game.tile.TileCoins;
import com.yellowbytestudios.game.tile.TileExit;
import com.yellowbytestudios.game.tile.TileManager;
import com.yellowbytestudios.media.Sounds;
import com.yellowbytestudios.screens.GameScreen;
import com.yellowbytestudios.screens.ScreenManager;

import java.util.ArrayList;

public class GameManager {

    private OrthographicCamera camera;
    private TileManager tileManager;
    private Player player;
    private GameObjectArray coins;
    private Exit exit;
    private boolean paused = false;
    private int score = 0;
    private boolean gameOver = false;

    public GameManager() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);

        tileManager = new TileManager(camera);
        player = new Player("Phil");

        coins = TileCoins.createCoins(tileManager.getLayer(1));
        exit = TileExit.createExit(tileManager.getLayer(2));

        updateCamera();
    }

    private void updateCamera() {
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();
    }

    public void update(float delta) {
        if (!paused && !gameOver) {
            player.update(delta);
            for (int i = 0; i < coins.size(); i++) {
                if (player.getBounds().overlaps(coins.get(i).getBounds())) {
                    score++;
                    coins.remove(i);
                    Gdx.input.vibrate(20);
                    Sounds.play("sound/laser.wav");
                }
            }
            tileManager.update(player);
            updateCamera();
            coins.doRemovals();
            if (player.getBounds().overlaps(exit.getBounds())) {
                gameOver = true;
                ScreenManager.setScreen(new GameScreen());
            }
        }
    }

    public void render(SpriteBatch sb) {
        tileManager.render();

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        player.render(sb);
        coins.render(sb);
        exit.render(sb);
        sb.end();
    }

    public void togglePause() {
        paused = !paused;
    }

    public int getScore() {
        return score;
    }

    public void dispose() {
        tileManager.dispose();
        player = null;
        gameOver = true;
    }
}
