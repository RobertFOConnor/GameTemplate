package com.yellowbytestudios.screens.LevelSelectScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.screens.LevelSelectScreen.components.LevelScrollView;
import com.yellowbytestudios.screens.Screen;
import com.yellowbytestudios.screens.ScreenManager;
import com.yellowbytestudios.screens.TitleScreen;
import com.yellowbytestudios.ui.Metrics;
import com.yellowbytestudios.ui.OnTouchListener;
import com.yellowbytestudios.ui.TextButton;

import sun.applet.Main;

public class LevelSelectScreen extends Screen {

    private TextButton backButton;
    private LevelScrollView levelScrollView;
    private float levelScrollViewHeight = 1000;

    @Override
    public void create() {
        super.create();
        backButton = new TextButton();
        backButton.setName("Go Back");
        backButton.setupBounds();
        backButton.setPos(Metrics.marginX(3), MainGame.HEIGHT - Metrics.marginY(3));
        backButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public void onTouch(Vector2 touch) {
                ScreenManager.setScreen(new TitleScreen());
            }
        });
        UIElements.add(backButton);
        levelScrollView = new LevelScrollView(0, MainGame.HEIGHT / 2 - levelScrollViewHeight / 2, MainGame.WIDTH, levelScrollViewHeight);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        levelScrollView.update();
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        super.render(sb, sr);
        levelScrollView.render(sb, sr);
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

    }
}
