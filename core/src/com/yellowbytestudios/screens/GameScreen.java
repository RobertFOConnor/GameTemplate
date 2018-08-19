package com.yellowbytestudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.camera.OrthoCamera;
import com.yellowbytestudios.game.Player;
import com.yellowbytestudios.ui.OnTouchListener;
import com.yellowbytestudios.ui.TextButton;
import com.yellowbytestudios.ui.UIElement;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private OrthoCamera camera;
    private Player player;
    private ArrayList<UIElement> UIElements;
    private boolean paused = false;

    @Override
    public void create() {
        camera = new OrthoCamera();
        camera.resize();
        player = new Player("Phil");

        UIElements = new ArrayList<UIElement>();
        UIElements.add(new TextButton("Pause", new Vector2(50, MainGame.HEIGHT-20), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                paused = !paused;
            }
        }));

        UIElements.add(new TextButton("Close", new Vector2(MainGame.WIDTH-250, MainGame.HEIGHT-20), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                goBack();
            }
        }));
    }

    @Override
    public void update(float step) {
        if (Gdx.input.justTouched()) {
            Vector2 touch = getTouchPos();
            for (UIElement uiElement : UIElements) {
                uiElement.checkTouch(touch);
            }
        }

        if (!paused) {
            if (Gdx.input.isTouched(0)) {
                Vector2 touch = getTouchPos();
                player.onTouch(touch);
            }
            player.update();
        }
    }

    private Vector2 getTouchPos() {
        return camera.unprojectCoordinates(
                Gdx.input.getX(0),
                Gdx.input.getY(0)
        );
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        player.render(sb);

        //render UI elements
        for (UIElement uiElement : UIElements) {
            uiElement.render(sb);
        }
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
        ScreenManager.setScreen(new TitleScreen());
    }
}
