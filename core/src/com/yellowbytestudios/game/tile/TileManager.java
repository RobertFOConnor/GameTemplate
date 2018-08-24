package com.yellowbytestudios.game.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.game.Player;

import java.util.ArrayList;


public class TileManager {

    private OrthographicCamera camera;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    private static float tileSize = 80;
    private float mapWidth;
    private float mapHeight;

    private ArrayList<Vector2[]> lines;
    private ShapeRenderer sr;
    private int[] renderLayers = new int[]{0};

    public TileManager(OrthographicCamera camera) {
        this.camera = camera;
        tiledMap = new TmxMapLoader().load("tilemap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        setupMapWidthHeight();
        sr = new ShapeRenderer();
        TileWallMaker tc = new TileWallMaker();
        lines = tc.createWalls(getLayer(0));
    }

    public void update(Player player) {
        TileCollision.checkCollision(player, lines);
    }

    public void render() {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render(renderLayers);

        sr.setProjectionMatrix(camera.combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.WHITE);
        for (Vector2[] v : lines) {
            //sr.rectLine(v[0], v[1], 3); //DEBUG
        }
        sr.end();
    }

    private void setupMapWidthHeight() {
        MapProperties mapProps = tiledMap.getProperties();
        if (mapProps.get("width", Integer.class) != null) {
            mapWidth = mapProps.get("width", Integer.class);
            mapHeight = mapProps.get("height", Integer.class);
            tileSize = mapProps.get("tilewidth", Integer.class);
        }
    }

    public float getMapWidth() {
        return mapWidth * tileSize;
    }

    public float getMapHeight() {
        return mapHeight * tileSize;
    }

    public static float getTileSize() {
        return tileSize;
    }

    public TiledMapTileLayer getLayer(int index) {
        return (TiledMapTileLayer) tiledMap.getLayers().get(index);
    }
}
