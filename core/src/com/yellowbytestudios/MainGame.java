package com.yellowbytestudios;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yellowbytestudios.media.Assets;
import com.yellowbytestudios.media.Fonts;
import com.yellowbytestudios.screens.GameScreen;
import com.yellowbytestudios.screens.ScreenManager;
import com.yellowbytestudios.screens.TitleScreen;

public class MainGame extends ApplicationAdapter {

    public static int WIDTH = 1080;
    public static int HEIGHT = 1920;
    private static final float STEP = 1 / 60f;
    private SpriteBatch sb;
    private boolean loaded = false;

    @Override
    public void create() {
        Fonts.load();
        Assets.load();
        sb = new SpriteBatch();
    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(0.15f, 0.1f, 0.5f, 0);

        if (Assets.update() && !loaded) {
            loaded = true;
            ScreenManager.setScreen(new TitleScreen());
        }

        if(loaded) {
            ScreenManager.getCurrentScreen().update(STEP);
            ScreenManager.getCurrentScreen().render(sb);
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
