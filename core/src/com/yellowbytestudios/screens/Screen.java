package com.yellowbytestudios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.camera.OrthoCamera;
import com.yellowbytestudios.ui.UIElement;

import java.util.ArrayList;

public abstract class Screen {

    OrthoCamera camera;
    ArrayList<UIElement> UIElements;

    public void create() {
        camera = new OrthoCamera();
        camera.resize();
        UIElements = new ArrayList<UIElement>();
    }

    public void update(float delta) {
        if (Gdx.input.justTouched()) {
            Vector2 touch = getTouchPos();
            for (UIElement uiElement : UIElements) {
                uiElement.checkTouch(touch);
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

        //render UI elements
        for (UIElement uiElement : UIElements) {
            uiElement.render(sb);
        }
        sb.end();
    }

    public abstract void resize(int w, int h);

    public abstract void dispose();

    public abstract void show();

    public abstract void hide();

    public abstract void pause();

    public abstract void resume();

    public abstract void goBack();
}
