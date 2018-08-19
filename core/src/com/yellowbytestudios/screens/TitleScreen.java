package com.yellowbytestudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.camera.OrthoCamera;
import com.yellowbytestudios.ui.OnTouchListener;
import com.yellowbytestudios.ui.TextButton;
import com.yellowbytestudios.ui.UIElement;

import java.util.ArrayList;

public class TitleScreen implements Screen {

    private OrthoCamera camera;
    private ArrayList<UIElement> UIElements;

    @Override
    public void create() {
        camera = new OrthoCamera();
        camera.resize();

        UIElements = new ArrayList<UIElement>();
        UIElements.add(new TextButton("Start Game", new Vector2(MainGame.WIDTH/2, MainGame.HEIGHT/2), new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                ScreenManager.setScreen(new GameScreen());
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
        Gdx.app.exit();
    }

}
