package com.yellowbytestudios.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.screens.Screen;
import com.yellowbytestudios.screens.ScreenManager;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import sun.applet.Main;

/**
 * Created by BobbyBoy on 17-Feb-16.
 */
public class UIAnimator {

    //Tween manager.
    public static boolean ANIMATIONS_ENABLED = true;
    public static TweenManager tweenManager;
    private static long startTime;

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

        float targetX = element.getX();
        float targetY = element.getY();

        float startX = 0;
        float startY = 0;

        if(direction == LEFT) {
            startX = -element.getWidth();
            startY = targetY;
        } else if(direction == RIGHT) {
            startX = MainGame.WIDTH + element.getWidth();
            startY = targetY;
        } else if(direction == TOP) {
            startX = targetX;
            startY = MainGame.HEIGHT;
        } else if(direction == BOTTOM) {
            startX = targetX;
            startY = 0;
        }


        element.setPos(startX, startY);
        Tween.to(element.getSprite(), SpriteAccessor.POS_XY, 0.7f)
                .target(targetX, targetY).ease(TweenEquations.easeOutBack)
                .start(tweenManager);
    }

    public static void applyExitAnimation(Sprite b, float x, float y, final Screen s) {
        TweenCallback myCallBack = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                ScreenManager.setScreen(s);
            }
        };

        Tween.to(b, SpriteAccessor.POS_XY, 17f)
                .target(x, y).ease(TweenEquations.easeOutBack).setCallback(myCallBack)
                .setCallbackTriggers(TweenCallback.END).start(tweenManager);
    }


    public static void applyFadeAnimation(Sprite b) {
        Tween.to(b, SpriteAccessor.OPACITY, 200f)
                .target(1f).ease(TweenEquations.easeOutBack)
                .start(tweenManager);
    }

    public static void applyLevelStartAnimation(Sprite b, float x, float y) {
        Tween.to(b, SpriteAccessor.POS_XY, 35f)
                .target(x, y).ease(TweenEquations.easeOutBounce)
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

    public static void startAnimation() {
        startTime = TimeUtils.millis();
    }
}
