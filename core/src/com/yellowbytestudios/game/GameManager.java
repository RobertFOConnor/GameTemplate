package com.yellowbytestudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.game.map.Tile;
import com.yellowbytestudios.media.Assets;
import com.yellowbytestudios.screens.ScreenManager;

import java.util.ArrayList;
import java.util.Timer;

import static com.yellowbytestudios.game.map.Tile.TILE_SIZE;

public class GameManager {

    private OrthographicCamera camera;
    private FitViewport viewport;

    private Player player;
    private boolean paused = false;
    private int score = 0;
    private ArrayList<Timer> timers;
    private boolean gameOver = false;

    private ArrayList<Tile> tileMap;

    public GameManager() {
        setupGameCamera();
        player = new Player("Phil");
        player.setPos(camera.position.x, camera.position.y);

        tileMap = new ArrayList<Tile>();

        for (int x = 0; x < MainGame.WIDTH / TILE_SIZE; x++) {
            for (int y = 0; y < MainGame.HEIGHT / TILE_SIZE; y++) {
                tileMap.add(new Tile(Assets.manager.get("ship.png", Texture.class), x * TILE_SIZE, y * TILE_SIZE));
            }
        }
        startTimers();
    }

    private void setupGameCamera() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(MainGame.WIDTH, MainGame.HEIGHT, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }

    private void startTimers() {
        timers = new ArrayList<Timer>();
        Timer bulletTimer = new Timer();
//        bulletTimer.scheduleAtFixedRate(new TimerTask() {
//
//            @Override
//            public void run() {
//
//            }
//        }, 0, 130);
//        timers.add(bulletTimer);
    }

    public void update(float delta) {
        if (!paused && !gameOver) {


            if (Gdx.input.isTouched(0)) {
                Vector2 touch = ScreenManager.getCurrentScreen().getTouchPos();
                player.onTouch(touch, delta);
            }
        }
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();
    }

    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        for (Tile t : tileMap) {
            t.render(sb);
        }

        player.render(sb);
        sb.end();
    }

    public void togglePause() {
        paused = !paused;

        if (paused) {
            stopTimers();
        } else {
            startTimers();
        }
    }

    private void stopTimers() {
        for (int i = 0; i < timers.size(); i++) {
            timers.get(i).cancel();
        }
    }

    public int getScore() {
        return score;
    }

    public void dispose() {
        stopTimers();
        player = null;
        gameOver = true;
    }
}
