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

import java.util.ArrayList;


public class TileManager {

    private OrthographicCamera camera;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    private float tileSize;
    private float mapWidth;
    private float mapHeight;

    ArrayList<Vector2[]> lines;
    private ShapeRenderer sr;

    public TileManager(OrthographicCamera camera) {
        this.camera = camera;
        tiledMap = new TmxMapLoader().load("tilemap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        setupMapWidthHeight();
        TileCollision tc = new TileCollision();

        sr = new ShapeRenderer();
        lines = tc.createWalls((TiledMapTileLayer) tiledMap.getLayers().get(0));
    }

    public void render() {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        sr.setProjectionMatrix(camera.combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.WHITE);
        for (Vector2[] v : lines) {
            sr.rectLine(v[0], v[1], 5);
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
}
