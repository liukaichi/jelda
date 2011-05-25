package org.jelda.quest.map;

import java.awt.Point;
import java.security.InvalidParameterException;
import java.util.HashMap;

public class Map {
	public static final int DEFAULT_TILESET_WIDTH = 16, DEFAULT_TILESET_HEIGHT = 14;
	public HashMap<Point, TileSet> tileSets;
	public Map() {
		tileSets = new HashMap<Point, TileSet>();
	}
	public Map(HashMap<Point, TileSet> tileSets) {
		this.tileSets = tileSets;
	}
	public void putTileSet(Point point, TileSet set) {
		if (tileSets.containsKey(point)) {
			throw new InvalidParameterException("Map already contains a tileset at " + point);
		}
		else {
			set.mapLocation = point;
			tileSets.put(point, set);
		}
	}
	public TileSet removeTileSet(Point point) {
		if (!tileSets.containsKey(point)) {
			throw new InvalidParameterException("Map does not contain a tileset at " + point);
		}
		TileSet ts = tileSets.remove(point);
		ts.mapLocation = null;
		return ts;
	}
	
	public TileSet getTileSet(Point point) {
		if (!tileSets.containsKey(point)) {
			throw new InvalidParameterException("Map does not contain a tileset at " + point);
		}
		return tileSets.get(point);
	}
	
	public boolean containsTileSet(TileSet tileSet) {
		return tileSets.containsValue(tileSet);
	}
	
	public boolean containsTileSetAt(Point point) {
		return tileSets.containsKey(point);
	}
}
