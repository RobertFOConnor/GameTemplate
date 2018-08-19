package com.yellowbytestudios.media;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Fonts {

    public static BitmapFont GUIFont;

    public static void load() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/custom_font.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 80;
        GUIFont = generator.generateFont(parameter);
    }

    public static void dispose() {
        GUIFont.dispose();
    }

    public static float getWidth(BitmapFont f, String s) {
        final GlyphLayout layout = new GlyphLayout(f, s);
        return layout.width / 2;
    }
}
