package com.yellowbytestudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            advanceScreen();

        } else if (Gdx.input.justTouched()) {
            advanceScreen();
        }
    }

    private void advanceScreen() {

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
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
