package com.yellowbytestudios.game.tile;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.yellowbytestudios.game.Exit;

public class TileExit {

    public static Exit createExit(TiledMapTileLayer layer) {
        Exit exit;
        float tileSize = TileManager.getTileSize();

        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {

                //Get cell at (row, col) position.
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);

                if (cell != null) {
                    exit = new Exit(col * tileSize, row * tileSize);
                    return exit;
                }
            }
        }
        return null;
    }
}
