package com.yellowbytestudios.game.tile;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.yellowbytestudios.game.Coin;

import java.util.ArrayList;

public class TileCoins {

    private static float tileSize = TileManager.getTileSize();
    private static int coinSize = Coin.SIZE;

    private static float getCoinX(int col) {
        return col * tileSize + tileSize / 2 - coinSize / 2;
    }

    private static float getCoinY(int row) {
        return row * tileSize + tileSize / 2 - coinSize / 2;
    }

    public static ArrayList<Coin> createCoins(TiledMapTileLayer layer) {
        ArrayList<Coin> coins = new ArrayList<Coin>();


        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {

                //Get cell at (row, col) position.
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);


                if (cell != null) {
                    coins.add(new Coin(getCoinX(col), getCoinY(row)));
                }
            }
        }
        return coins;
    }
}
