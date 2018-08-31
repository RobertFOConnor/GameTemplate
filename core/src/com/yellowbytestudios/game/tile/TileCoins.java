package com.yellowbytestudios.game.tile;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.yellowbytestudios.game.Coin;
import com.yellowbytestudios.game.GameObjectArray;

public class TileCoins {

    private static float tileSize = TileManager.getTileSize();
    private static int coinSize = Coin.SIZE;

    private static float getCoinPos(int orientation) {
        return orientation * tileSize + tileSize / 2 - coinSize / 2;
    }

    public static GameObjectArray createCoins(TiledMapTileLayer layer) {
        GameObjectArray coins = new GameObjectArray();

        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);
                if (cell != null) {
                    coins.add(new Coin(getCoinPos(col), getCoinPos(row)));
                }
            }
        }
        return coins;
    }
}
