package com.yellowbytestudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.camera.OrthoCamera;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameManager {

    private OrthoCamera camera;
    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private boolean addBullet = false;

    public GameManager(OrthoCamera camera) {
        player = new Player("Phil");
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy());
        this.camera = camera;

        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                addBullet = true;
            }
        }, 0, 400);
    }

    public void update(float delta) {
        for (Enemy e : enemies) {
            e.update(delta);
            if (e.isOffScreen()) {
                e.reset();
            }
        }
        if (addBullet) {
            bullets.add(new Bullet(player.getBulletStartPos()));
            addBullet = false;
        }
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.update(delta);
            if (b.isOffScreen()) {
                bullets.remove(b);
            }
        }
        if (Gdx.input.isTouched(0)) {
            Vector2 touch = getTouchPos();
            player.onTouch(touch, delta);
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
        for (Enemy e : enemies) {
            e.render(sb);
        }
        for (Bullet b : bullets) {
            b.render(sb);
        }
        player.render(sb);
        sb.end();
    }
}
