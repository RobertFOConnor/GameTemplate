package com.yellowbytestudios.game.tile;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class TileCollision {

    private ArrayList<Vector2[]> lines;
    private boolean shouldDrawVector = false;
    private boolean lineStarted = false;
    private int lineCountX = 0, lineCountY = 0;
    private int lineCount = 0;
    private float tileSize = 90;


    private Vector2 getTop_R(int row, int col) {
        return new Vector2(col * tileSize + tileSize, row * tileSize + tileSize);
    }

    private Vector2 getTop_L(int row, int col) {
        return new Vector2(col * tileSize, row * tileSize + tileSize);
    }

    private Vector2 getBot_L(int row, int col) {
        return new Vector2(col * tileSize, row * tileSize);
    }

    public ArrayList<Vector2[]> createWalls(TiledMapTileLayer layer) {
        lines = new ArrayList<Vector2[]>();
        tileSize = (int) layer.getTileWidth();

        Vector2 start = new Vector2();
        Vector2 finish = new Vector2();


        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {

                //Get cell at (row, col) position.
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);
                //Get cell above current cell.
                TiledMapTileLayer.Cell above_cell = layer.getCell(col, row + 1);
                //Set the main cell to our current cell.
                TiledMapTileLayer.Cell main_cell = cell;

                shouldDrawVector = false;

                //Check if cell is a tile.
                if (cell != null) {
                    //If cell is a tile and there is nothing above it, we should draw.

                    if (above_cell == null) {
                        if (!lineStarted) {
                            start = getTop_L(row, col);
                            finish = getTop_R(row, col);
                        } else {
                            finish = getTop_R(row, col).cpy().add(lineCountX, 0);
                        }
                        lineStarted = true;
                        lineCountX++;

                    } else {
                        checkDrawVector();
                    }

                } else {

                    //If cell is empty but there is a tile above it, we should draw.
                    if (above_cell != null) {
                        if (!lineStarted) {
                            start = getTop_L(row, col);
                            finish = getTop_R(row, col);
                        } else {
                            finish = getTop_R(row, col).cpy().add(lineCountX, 0);
                        }
                        lineStarted = true;
                        lineCountX++;
                        main_cell = above_cell;

                    } else {
                        checkDrawVector();
                    }
                }

                if (shouldDrawVector) {
                    drawVector(start, finish);
                    lineCountX = 0;
                }
            }
        }
        lineStarted = false;
        lineCountX = 0;


        for (int col = 0; col < layer.getWidth(); col++) {
            for (int row = 0; row < layer.getHeight(); row++) {

                TiledMapTileLayer.Cell cell = layer.getCell(col, row);
                TiledMapTileLayer.Cell left_cell = layer.getCell(col - 1, row);
                TiledMapTileLayer.Cell main_cell = cell;
                shouldDrawVector = false;


                if (cell != null) {
                    if (left_cell == null && col != 0) {
                        if (!lineStarted) {
                            start = getTop_L(row, col);
                            finish = getBot_L(row, col);
                        } else {
                            start = getTop_L(row, col).cpy().add(0, lineCountY);
                        }
                        lineStarted = true;
                        lineCountY++;


                    } else {
                        checkDrawVector();
                    }
                } else {
                    //If cell is empty but there is a tile above it, we should draw.
                    if (left_cell != null) {
                        if (!lineStarted) {
                            start = getTop_L(row, col);
                            finish = getBot_L(row, col);
                        } else {
                            start = getTop_L(row, col).cpy().add(0, lineCountY);
                        }
                        lineStarted = true;
                        lineCountY++;
                        main_cell = left_cell;


                    } else {
                        checkDrawVector();
                    }
                }

                if (shouldDrawVector) {
                    drawVector(start, finish);
                    lineCountY = 0;
                }
            }
        }
        return lines;
    }

    private void checkDrawVector() {
        if (lineStarted) {
            shouldDrawVector = true;
            lineStarted = false;
        }
    }

    private void drawVector(Vector2 start, Vector2 finish) {
        Vector2[] v;
        v = new Vector2[2];
        v[0] = start;
        v[1] = finish;
        lines.add(v);
        lineCount++;
    }
}
