package com.yellowbytestudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.camera.OrthoCamera;
import com.yellowbytestudios.media.Sounds;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameManager {

    private OrthoCamera camera;
    private Player player;
    private GameObjectArray bullets;
    private GameObjectArray enemies;
    private boolean addBullet = false;
    private boolean addEnemy = false;
    private boolean paused = false;
    private int score = 0;
    private ArrayList<Timer> timers;
    private boolean gameOver = false;

    public GameManager(OrthoCamera camera) {
        player = new Player("Phil");
        bullets = new GameObjectArray();
        enemies = new GameObjectArray();
        this.camera = camera;

        startTimers();
    }

    private void startTimers() {
        timers = new ArrayList<Timer>();
        Timer bulletTimer = new Timer();
        bulletTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                addBullet = true;
                System.out.println("add_bullet");
                Sounds.play("sound/laser.wav");
            }
        }, 0, 130);
        timers.add(bulletTimer);

        Timer enemiesTimer = new Timer();
        enemiesTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                addEnemy = true;
                System.out.println("add_enemy");
            }
        }, 0, 700);
        timers.add(enemiesTimer);
    }

    public void update(float delta) {
        if (!paused && !gameOver) {
            for (int j = 0; j < enemies.size(); j++) {
                Enemy e = (Enemy) enemies.get(j);
                e.update(delta);
                if (e.isOffScreen()) {
                    enemies.remove(j);
                }

                for (int i = 0; i < bullets.size(); i++) {
                    Bullet b = (Bullet) bullets.get(i);
                    if (b.getBounds().overlaps(e.getBounds())) {
                        bullets.remove(i);

                        e.setHealth(e.getHealth() - 1);
                        if (e.getHealth() <= 0) {
                            enemies.remove(j);
                            score++;
                            Sounds.play("sound/explode.wav");
                        }
                    }
                }
            }

            if (addBullet) {
                bullets.add(new Bullet(player.getBulletStartX(), player.getBulletStartY()));
                addBullet = false;
            }

            if (addEnemy) {
                enemies.add(new Enemy());
                addEnemy = false;
            }

            for (int i = 0; i < bullets.size(); i++) {
                Bullet b = (Bullet) bullets.get(i);
                b.update(delta);
                if (b.isOffScreen()) {
                    bullets.remove(i);
                }
            }

            enemies.doRemovals();
            bullets.doRemovals();

            if (Gdx.input.isTouched(0)) {
                Vector2 touch = getTouchPos();
                player.onTouch(touch, delta);
            }
        }
    }

    private Vector2 getTouchPos() {
        return camera.unprojectCoordinates(
                Gdx.input.getX(),
                Gdx.input.getY()
        );
    }

    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        enemies.render(sb);
        bullets.render(sb);
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

    public void stopTimers() {
        for (int i = 0; i < timers.size(); i++) {
            timers.get(i).cancel();
        }
    }

    public int getScore() {
        return score;
    }

    public void dispose() {
        stopTimers();
        enemies = null;
        bullets = null;
        player = null;
        gameOver = true;
    }
}
