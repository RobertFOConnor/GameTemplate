package com.yellowbytestudios.media;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Fonts {

    public enum size {LARGE, MEDIUM, SMALL}

    private static BitmapFont small, medium, large;

    public static void load() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/reg_font.otf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        //parameter.shadowColor = new Color(0, 0f, 0, 0.8f);

        parameter.color = Color.WHITE;
        parameter.size = 60;
        small = createFont(generator, parameter, 30);

        parameter.size = 85;
        medium = createFont(generator, parameter, 85);

        parameter.size = 100;
        large = createFont(generator, parameter, 100);

        generator.dispose();
    }

    private static BitmapFont createFont(FreeTypeFontGenerator gen, FreeTypeFontParameter par, int size) {
        par.size = size;
        BitmapFont font = gen.generateFont(par);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return font;
    }

    public static BitmapFont getFont(size s) {
        switch (s) {
            case SMALL:
                return small;
            case MEDIUM:
                return medium;
            case LARGE:
                return large;
            default:
                return medium;
        }
    }

    public static void dispose() {
        small.dispose();
        medium.dispose();
        large.dispose();
    }
}
