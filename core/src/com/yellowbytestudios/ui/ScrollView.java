package com.yellowbytestudios.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.yellowbytestudios.MainGame;

import static com.yellowbytestudios.ui.Metrics.CENTER_X;

public class ScrollView extends UIElement {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private boolean isHorizontal = false;
    private OrthographicCamera camera;
    private Vector2 startTouch;
    private boolean scrollStarted = false;
    TextView tv;

    private float contentWidth;
    private float contentHeight;

    public ScrollView(float x, float y, float width, float height, int orientation) {
        super(x, y);
        if (orientation == HORIZONTAL) {
            isHorizontal = true;
        }
        this.width = width;
        this.height = height;
        contentWidth = width + 100;
        contentHeight = height;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);

        tv = new TextView("This is text within a scrollview.", CENTER_X, getY() + 200, 200);
        tv.center();
    }

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
            if (isHorizontal) {
                float scrollAmount = startTouch.x - currTouch.x;
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
            } else {
                float scrollAmount = startTouch.y - currTouch.y;
                if (camera.position.y - getHeight() / 2 > 0) {
                    camera.position.add(0, scrollAmount, 0);
                }
            }
            System.out.println("cam pos: " + camX);
        }

        if (!Gdx.input.isTouched() && scrollStarted) {
            startTouch = null;
            scrollStarted = false;
        }

        camera.update();
    }

    public Vector2 getTouchPos() {
        Vector3 rawtouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(rawtouch);
        return new Vector2(rawtouch.x, rawtouch.y);
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.WHITE);
        sr.rect(getX(), getY(), width, height);
        sr.setColor(Color.BLUE);
        sr.rect(getX() + 10, getY() + 10, width - 20, height - 20);
        sr.end();

        sr.setProjectionMatrix(camera.combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.rect(getX(), getY(), width, height);
        sr.setColor(Color.ORANGE);
        sr.rect(getX() + 10, getY() + 10, width - 20, height - 20);
        sr.end();

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        tv.render(sb);
        sb.end();
    }

    @Override
    public boolean checkTouch(Vector2 touch) {
        return false;
    }
}
