package com.yellowbytestudios.game.tile;

import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.game.GameObject;
import com.yellowbytestudios.game.Player;

import java.util.ArrayList;


public class TileCollision {

    public static void checkCollision(GameObject gameObject, ArrayList<Vector2[]> lines) {
        Player player = (Player) gameObject;

        for (Vector2[] line : lines) {
            if (gameObject.getY() > line[1].y - gameObject.getHeight() && gameObject.getY() < line[0].y) {
                if (player.getOldX() < player.getX()) { //moving right
                    if (player.getOldX() + player.getWidth() <= line[0].x && player.getX2() >= line[0].x) {
                        player.setPos(line[0].x - player.getWidth(), player.getY());
                        player.onCollide();
                    }
                } else if (player.getOldX() > player.getX()) {
                    if (player.getOldX() >= line[0].x && player.getX() <= line[0].x) {
                        player.setPos(line[0].x, player.getY());
                        player.onCollide();
                    }
                }
            }

            if (gameObject.getX2() > line[0].x && gameObject.getX() < line[1].x) {
                if (player.getOldY() < player.getY()) { //moving up
                    if (player.getY2() >= line[0].y && player.getOldY() + player.getHeight() <= line[0].y) {
                        player.setPos(player.getX(), line[0].y - player.getHeight());
                        player.onCollide();
                    }
                } else if (player.getOldY() > player.getY()) {
                    if (player.getY() <= line[0].y && player.getOldY() >= line[0].y) {
                        player.setPos(player.getX(), line[0].y);
                        player.onCollide();
                    }
                }
            }
        }
    }
}


