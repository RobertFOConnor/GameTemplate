package com.yellowbytestudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.camera.OrthoCamera;

import java.util.ArrayList;

public class GameManager {

    private OrthoCamera camera;
    private Player player;
    private ArrayList<Enemy> enemies;

    public GameManager(OrthoCamera camera) {
        player = new Player("Phil");
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy());
        this.camera = camera;
    }

    public void update(float delta) {
        for(Enemy e : enemies) {
            e.update(delta);
            if(e.isOffScreen()) {
                e.reset();
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
        for(Enemy e : enemies) {
            e.render(sb);
        }
        player.render(sb);
        sb.end();
    }
}
