package com.yellowbytestudios.screens.LevelSelectScreen.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.yellowbytestudios.MainGame;
import com.yellowbytestudios.ui.UIElement;

import java.util.ArrayList;

public class LevelScrollView extends UIElement {

    private ArrayList<LevelButton> levelButtons;
    private OrthographicCamera camera;
    private Vector2 startTouch;
    private boolean scrollStarted = false;
    private float contentWidth;
    private static final float horizontalPadding = 700;
    private static final float verticalPadding = 500;

    private int levelCount = 20;
    private int currLevel = 2;

    public LevelScrollView(float x, float y, float width, float height) {
        super(x, y);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
        levelButtons = new ArrayList<LevelButton>();
        this.width = width;
        this.height = height;
        float centerX = width / 2;

        for (int i = 0; i < levelCount; i++) {
            float xPos = centerX - LevelButton.WIDTH / 2 + (i / 2) * horizontalPadding;
            float yPos = 200 + getY() + verticalPadding * (i % 2);
            levelButtons.add(new LevelButton(xPos, yPos, String.valueOf(i + 1)));
        }
        contentWidth = centerX - LevelButton.WIDTH / 2 + horizontalPadding * (levelCount / 2) - getWidth() + LevelButton.WIDTH * 2;

        //Scroll to current level.
        camera.position.set(getX() + getWidth() / 2 + ((currLevel - 1) / 2) * horizontalPadding, getY() + getHeight() / 2, 0);
    }

    float scrollAmount;

    public void update() {
        if (Gdx.input.justTouched() && !scrollStarted) {
            Vector2 touch = getTouchPos();
            if (touch.y < getY() + getHeight() && touch.y > getY()) {
                startTouch = touch;
                scrollStarted = true;
            }
        }

        float camX = camera.position.x - getWidth() / 2;

        if (Gdx.input.isTouched() && scrollStarted) {
            Vector2 currTouch = getTouchPos();
            scrollAmount = startTouch.x - currTouch.x;
            float newXPos = camX + scrollAmount;
            if (newXPos >= 0 && newXPos <= contentWidth) {
                camera.position.add(scrollAmount, 0, 0);
            } else {
                if (0 - camX > camX - contentWidth) {
                    camera.position.set(getWidth() / 2, camera.position.y, camera.position.z);
                } else {
                    camera.position.set(contentWidth + getWidth() / 2, camera.position.y, camera.position.z);
                }
            }
        }

        if (!Gdx.input.isTouched() && scrollStarted) {
            startTouch = null;
            scrollStarted = false;
        }

        if (Gdx.input.justTouched()) {
            for (LevelButton levelButton : levelButtons) {
                if (levelButton.checkTouch(getTouchPos())) {
                    break;
                }
            }
        }

        camera.update();
    }

    private Vector2 getTouchPos() {
        Vector3 rawtouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(rawtouch);
        return new Vector2(rawtouch.x, rawtouch.y);
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        sr.setProjectionMatrix(camera.combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.WHITE);
        for (int i = 0; i < levelButtons.size(); i++) {
            if (i != 0) {
                LevelButton levelButton = levelButtons.get(i);
                LevelButton prevLevelButton = levelButtons.get(i - 1);
                sr.rectLine(
                        prevLevelButton.getCenterX(),
                        prevLevelButton.getCenterY(),
                        levelButton.getCenterX(),
                        levelButton.getCenterY(),
                        10
                );
            }
        }
        sr.end();

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        for (LevelButton levelButton : levelButtons) {
            levelButton.render(sb, sr);
        }
        sb.end();
    }

    @Override
    public boolean checkTouch(Vector2 touch) {
        return false;
    }
}
