package org.jelda.quest.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import org.jelda.quest.actor.Actor;

public class TileSet {
	public HashMap<Point, Tile> tiles;
	public TileSet() {
		tiles = new HashMap<Point, Tile>();
	}
	public TileSet(HashMap<Point, Tile> tiles) {
		this.tiles = tiles;
	}
	public void addTileAt(Point point, Tile tile) {
		tiles.put(point, tile);
	}
	public Tile getTileAt(Point point) {
		return tiles.get(point);
	}
	public ArrayList<Actor> getActorsAt(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		}
		else {
			return getTileAt(coordinates.getTileCoordinates()).getActorsAt(coordinates.getPixelCoordinates());
		}
	}
}
