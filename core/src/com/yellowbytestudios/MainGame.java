package com.yellowbytestudios;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.yellowbytestudios.media.Assets;
import com.yellowbytestudios.media.Fonts;
import com.yellowbytestudios.screens.ScreenManager;
import com.yellowbytestudios.screens.TitleScreen;
import com.yellowbytestudios.ui.UIAnimator;

public class MainGame extends ApplicationAdapter {

    public static int WIDTH = 1080;
    public static int HEIGHT = 1920;
    private SpriteBatch sb;
    private ShapeRenderer sr;
    private static UIAnimator uiAnimator;
    private boolean loaded = false;

    @Override
    public void create() {
        Fonts.load();
        Assets.load();
        sr = new ShapeRenderer();
        sb = new SpriteBatch();
        uiAnimator = new UIAnimator();
    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(0, 0, 0, 0);

        if (Assets.update() && !loaded) {
            loaded = true;
            ScreenManager.setScreen(new TitleScreen());
        }

        if (loaded) {

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            sr.setProjectionMatrix(ScreenManager.getCurrentScreen().getCamera().combined);
            sr.begin(ShapeRenderer.ShapeType.Filled);
            //sr.rect(0, 0, WIDTH, HEIGHT, Color.CORAL, Color.CORAL, Color.FIREBRICK, Color.FIREBRICK);
            sr.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

            uiAnimator.update(Gdx.graphics.getDeltaTime());
            ScreenManager.getCurrentScreen().update(Gdx.graphics.getDeltaTime());
            ScreenManager.getCurrentScreen().render(sb, sr);
        }
    }

    @Override
    public void resize(int w, int h) {

        if (ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().resize(w, h);
    }

    @Override
    public void dispose() {
        sb.dispose();
        Assets.dispose();
        Fonts.dispose();
    }
}
