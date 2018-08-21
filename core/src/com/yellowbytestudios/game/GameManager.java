package com.yellowbytestudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.camera.OrthoCamera;
import com.yellowbytestudios.media.Sounds;

import java.util.Timer;
import java.util.TimerTask;

public class GameManager {

    private OrthoCamera camera;
    private Player player;
    private GameObjectArray bullets;
    private GameObjectArray enemies;
    private boolean addBullet = false;
    private boolean paused = false;
    private int score = 0;

    public GameManager(OrthoCamera camera) {
        player = new Player("Phil");
        bullets = new GameObjectArray();
        enemies = new GameObjectArray();
        this.camera = camera;

        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                addBullet = true;
            }
        }, 0, 400);

        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                enemies.add(new Enemy());
            }
        }, 0, 1500);
    }

    public void update(float delta) {
        if (!paused) {
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
                        }
                        Sounds.play("sound/click.wav");
                    }
                }
            }

            if (addBullet) {
                bullets.add(new Bullet(player.getBulletStartX(), player.getBulletStartY()));
                addBullet = false;
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
    }

    public int getScore() {
        return score;
    }
}
