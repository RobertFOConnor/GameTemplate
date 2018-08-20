package com.yellowbytestudios.media;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Fonts {

    public static BitmapFont GUIFont;
    public static BitmapFont detailsFont;

    public static void load() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/reg_font.otf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 85;
        parameter.shadowColor = new Color(0, 0f, 0, 0.8f);
        parameter.color = Color.WHITE;
        GUIFont = generator.generateFont(parameter);
        parameter.color = Color.WHITE;
        parameter.size = 60;
        detailsFont = generator.generateFont(parameter);
        generator.dispose();
    }

    public static void dispose() {
        GUIFont.dispose();
    }
}
