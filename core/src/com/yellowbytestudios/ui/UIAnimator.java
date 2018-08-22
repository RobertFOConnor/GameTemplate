package com.yellowbytestudios.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.screens.Screen;
import com.yellowbytestudios.screens.ScreenManager;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by BobbyBoy on 17-Feb-16.
 */
public class UIAnimator {

    //Tween manager.
    public static boolean ANIMATIONS_ENABLED = true;
    public static TweenManager tweenManager;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    public static final int BOTTOM = 3;


    public UIAnimator() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        tweenManager = new TweenManager();
    }

    public void update(float delta) {
        tweenManager.update(delta);
    }

    public static void applyAnimation(UIElement element, int direction) {

        float duration = 0.9f;

        float targetX = element.getX();
        float targetY = element.getY();

        float startX = 0;
        float startY = 0;

        if (direction == LEFT) {
            startX = -element.getWidth();
            startY = targetY;
        } else if (direction == RIGHT) {
            startX = MainGame.WIDTH + element.getWidth();
            startY = targetY;
        } else if (direction == TOP) {
            startX = targetX;
            startY = MainGame.HEIGHT;
        } else if (direction == BOTTOM) {
            startX = targetX;
            startY = 0;
        }
        Sprite sprite = element.getSprite();


        element.setPos(startX, startY);
        Tween.to(sprite, SpriteAccessor.POS_XY, duration)
                .target(targetX, targetY).ease(TweenEquations.easeOutBack)
                .start(tweenManager);

        Color c = sprite.getColor();
        sprite.setColor(c.r, c.g, c.b, 0);
        Tween.to(sprite, SpriteAccessor.OPACITY, duration)
                .target(1f).ease(TweenEquations.easeNone)
                .start(tweenManager);
    }

    public static void applyLevelEndAnimation(Sprite b, float x, float y, final Screen s, final World world) {
        TweenCallback myCallBack = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                world.dispose();
                ScreenManager.setScreen(s);

            }
        };

        Tween.to(b, SpriteAccessor.POS_XY, 35f)
                .target(x, y).ease(TweenEquations.easeOutBounce).setCallback(myCallBack)
                .setCallbackTriggers(TweenCallback.END).start(tweenManager);
    }

}
