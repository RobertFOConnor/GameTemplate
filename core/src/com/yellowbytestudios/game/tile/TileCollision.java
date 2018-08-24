package com.yellowbytestudios.game.tile;

import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.game.GameObject;

import java.util.ArrayList;


public class TileCollision {

    public static void checkCollision(GameObject gameObject, ArrayList<Vector2[]> lines) {
        for (Vector2[] line : lines) {
            if (line[0].x > gameObject.getX() &&
                    line[0].x < gameObject.getX2() &&
                    line[0].y > gameObject.getY() &&
                    line[1].y < gameObject.getY2()) {
                if (gameObject.getX2() - gameObject.getWidth() / 2 > line[0].x) {
                    gameObject.setPos(line[0].x, gameObject.getY());
                    gameObject.onCollide();
                } else {
                    gameObject.setPos(line[0].x - gameObject.getWidth(), gameObject.getY());
                    gameObject.onCollide();
                }
            }

            if (line[0].y > gameObject.getY() &&
                    line[0].y < gameObject.getY2() &&
                    line[1].x > gameObject.getX() &&
                    line[0].x < gameObject.getX2()) {
                if (gameObject.getY2() - gameObject.getHeight() / 2 > line[0].y) {
                    gameObject.setPos(gameObject.getX(), line[0].y);
                    gameObject.onCollide();
                } else {
                    gameObject.setPos(gameObject.getX(), line[0].y - gameObject.getHeight());
                    gameObject.onCollide();
                }
            }
        }
    }
}


