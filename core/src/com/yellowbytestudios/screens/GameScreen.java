package com.yellowbytestudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.camera.OrthoCamera;
import com.yellowbytestudios.game.Player;

public class GameScreen implements Screen {

    private OrthoCamera camera;
    private Player player;

    @Override
    public void create() {
        camera = new OrthoCamera();
        camera.resize();
        player = new Player("Phil");
    }

    @Override
    public void update(float step) {
        if (Gdx.input.isTouched(0)) {
            Vector2 touch = camera.unprojectCoordinates(
                    Gdx.input.getX(0),
                    Gdx.input.getY(0)
            );
            player.onTouch(touch);
        }
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        player.update();
        player.render(sb);
        sb.end();
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
        Gdx.app.exit();
    }
}
