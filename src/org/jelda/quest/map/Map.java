package org.jelda.quest.map;

import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.jelda.quest.actor.Actor;

public class Map {
	public final int width, height;
	public HashMap<Point, TileSet> tileSets;
	public Map(int width, int height) {
		this( new HashMap<Point, TileSet>(), width, height);
	}
	public Map(HashMap<Point, TileSet> tileSets, int width, int height) {
		this.tileSets = tileSets;
		this.width = width;
		this.height = height;
	}
	public void addTileSetAt(Point point, TileSet tileSet) {
		tileSets.put(point, tileSet);
	}
	public TileSet getTileSetAt(Point point) {
		return tileSets.get(point);
	}
	public TileSet getTileSetAt(Coordinate point) {
		return tileSets.get(point.getTileSetCoordinates());
	}
	
	public PriorityQueue<Actor> getActorsAtAsPriorityQueueByHeight(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		}
		else {
			return getTileSetAt(coordinates.getTileSetCoordinates()).getTileAt(coordinates.getTileCoordinates()).getActorsAtAsPriorityQueueByHeight(coordinates.getPixelCoordinates());
		}
	}
	public Tile getTileAt(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		}
		else {
			return getTileSetAt(coordinates.getTileSetCoordinates()).getTileAt(coordinates.getTileCoordinates());
		}
	}
	public Collection<Actor> getActorsAt(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		}
		else {
			return getTileSetAt(coordinates.getTileSetCoordinates()).getTileAt(coordinates.getTileCoordinates()).getActorsAt(coordinates.getPixelCoordinates());
		}
	}
	
}
