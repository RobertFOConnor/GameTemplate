package com.yellowbytestudios;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yellowbytestudios.media.Assets;
import com.yellowbytestudios.media.Fonts;
import com.yellowbytestudios.screens.ScreenManager;
import com.yellowbytestudios.screens.TitleScreen;
import com.yellowbytestudios.ui.UIAnimator;

public class MainGame extends ApplicationAdapter {

    public static UIAnimator uiAnimator;
    public static int WIDTH = 1080;
    public static int HEIGHT = 1920;
    private SpriteBatch sb;
    private boolean loaded = false;
    private float[] bg = {(float) (Math.random() * 1f), (float) (Math.random() * 1f), (float) (Math.random() * 1f)};

    @Override
    public void create() {
        Fonts.load();
        Assets.load();
        sb = new SpriteBatch();
        uiAnimator = new UIAnimator();
    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(bg[0] - 0.5f, bg[1], bg[2] - 0.1f, 0);

        if (Assets.update() && !loaded) {
            loaded = true;
            ScreenManager.setScreen(new TitleScreen());
        }

        if (loaded) {
            uiAnimator.update(Gdx.graphics.getDeltaTime());
            ScreenManager.getCurrentScreen().update(Gdx.graphics.getDeltaTime());
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
