package org.jelda.quest.world;

import java.awt.Point;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.jelda.quest.actor.Actor;

public class TileSet {
	public final int width, height;
	public HashMap<Point, Tile> tiles;
	public TileSet() {
		this( World.DEFAULT_TILESET_WIDTH, World.DEFAULT_TILESET_HEGHT);
	}
	public TileSet(int width, int height) {
		this( new HashMap<Point, Tile>(), width, height);
	}
	public TileSet(HashMap<Point, Tile> tiles, int width, int height) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
	}
	public void checkBounds(Point point) {
		if (point.x >= width) {
			throw new InvalidParameterException("Trying to add outside map. point.x cannot be >= width");
		}
		else if (point.y >= height) {
			throw new InvalidParameterException("Tring to add outside map. point.y cannot be >= height");
		}
		else if (point.x < 0) {
			throw new InvalidParameterException("Tring to add outside map. point.x cannot be < 0");
		}
		else if (point.y < 0) {
			throw new InvalidParameterException("Tring to add outside map. point.y cannot be < 0");
		}
	}
	public void addTileAt(Point point, Tile tile) {
		checkBounds(point);
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
