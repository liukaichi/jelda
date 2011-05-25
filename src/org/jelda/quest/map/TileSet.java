package org.jelda.quest.map;

import java.awt.Point;
import java.security.InvalidParameterException;
import java.util.HashMap;

public class TileSet {
	public int width, height;
	public Point mapLocation = null;
	public HashMap<Point, Tile> tiles;
	public TileSet() {
		this(new HashMap<Point, Tile>(), Map.DEFAULT_TILESET_WIDTH, Map.DEFAULT_TILESET_HEIGHT);
	}
	public TileSet(HashMap<Point, Tile> tiles) {
		this(tiles, Map.DEFAULT_TILESET_WIDTH, Map.DEFAULT_TILESET_HEIGHT);
	}
	public TileSet(int width, int height) {
		this(new HashMap<Point, Tile>(), width, height);
	}
	public TileSet(HashMap<Point, Tile> tiles, int width, int height) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
	}
	public void putTile(Point point, Tile tile) {
		if (tiles.containsKey(point)) {
			throw new InvalidParameterException("TileSet already contains a tile at " + point);
		}
		else {
			tile.tileSetLocation = point;
			tile.mapLocation = mapLocation;
			tiles.put(point, tile);
		}
	}
	public Tile removeTile(Point point) {
		if (!tiles.containsKey(point)) {
			throw new InvalidParameterException("TileSet does not contain a tile at " + point);
		}
		Tile t = tiles.remove(point);
		t.mapLocation = null;
		t.tileSetLocation = null;
		return t;
	}
	
	public Tile getTile(Point point) {
		if (!tiles.containsKey(point)) {
			throw new InvalidParameterException("TileSet does not contain a tile at " + point);
		}
		return tiles.get(point);
	}
	
	public boolean containsTile(Tile tile) {
		return tiles.containsValue(tile);
	}
	
	public boolean containsTileAt(Point point) {
		return tiles.containsKey(point);
	}
}
