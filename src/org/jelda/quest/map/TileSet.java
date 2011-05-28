package org.jelda.quest.map;

import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.jelda.quest.actor.Actor;

public class TileSet {
	public final int width, height;
	public HashMap<Point, Tile> tiles;
	public TileSet(int width, int height) {
		this( new HashMap<Point, Tile>(), width, height);
	}
	public TileSet(HashMap<Point, Tile> tiles, int width, int height) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
	}
	public void addTileAt(Point point, Tile tile) {
		tiles.put(point, tile);
	}
	public Tile getTileAt(Point point) {
		return tiles.get(point);
	}
	public Tile getTileAt(Coordinate point) {
		return tiles.get(point.getTileCoordinates());
	}
	public PriorityQueue<Actor> getActorsAtAsPriorityQueueByHeight(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		}
		else {
			return getTileAt(coordinates.getTileCoordinates()).getActorsAtAsPriorityQueueByHeight(coordinates.getPixelCoordinates());
		}
	}
	
	public Collection<Actor> getActorsAt(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		}
		else {
			return getTileAt(coordinates.getTileCoordinates()).getActorsAt(coordinates.getPixelCoordinates());
		}
	}
}
