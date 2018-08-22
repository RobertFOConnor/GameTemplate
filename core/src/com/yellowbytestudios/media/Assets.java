package com.yellowbytestudios.media;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Assets {

    public static AssetManager manager = new AssetManager();
    private static HashMap<String, Class> assetMap = new HashMap<String, Class>();

    // Loads Assets
    public static void load() {

        //Images
        assetMap.put("ship.png", Texture.class);
        assetMap.put("enemy.png", Texture.class);

        //Sound
        assetMap.put("sound/click.wav", Sound.class);
        assetMap.put("sound/laser.wav", Sound.class);
        assetMap.put("sound/explode.wav", Sound.class);

        //Music
        assetMap.put("music/title_music.mp3", Music.class);

        Iterator it = assetMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            manager.load((String) pair.getKey(), (Class) pair.getValue());
            it.remove();
        }
    }

    public static void dispose() {
        manager.dispose();
    }

    public static boolean update() {
        return manager.update();
    }
}
