package org.jelda.quest.map;

import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.jelda.quest.actor.Actor;

public class World {
	public final int width, height;
	public HashMap<Point, Map> maps;
	public World(int width, int height) {
		this( new HashMap<Point, Map>(), width, height);
	}
	public World(HashMap<Point, Map> maps, int width, int height) {
		this.maps = maps;
		this.width = width;
		this.height = height;
	}
	public void addMapAt(Point point, Map map) {
		maps.put(point, map);
	}
	public Map getMapAt(Point point) {
		return maps.get(point);
	}
	public Map getMapAt(Coordinate point) {
		return maps.get(point.getMapCoordinates());
	}
	public PriorityQueue<Actor> getActorsAtAsPriorityQueueByHeight(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		}
		else {
			return getMapAt(coordinates.getMapCoordinates()).getTileSetAt(coordinates.getTileSetCoordinates()).getTileAt(coordinates.getTileCoordinates()).getActorsAtAsPriorityQueueByHeight(coordinates.getPixelCoordinates());
		}
	}
	public Tile getTileAt(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		}
		else {
			return getMapAt(coordinates.getMapCoordinates()).getTileSetAt(coordinates.getTileSetCoordinates()).getTileAt(coordinates.getTileCoordinates());
		}
	}
	public TileSet getTileSetAt(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		}
		else {
			return getMapAt(coordinates.getMapCoordinates()).getTileSetAt(coordinates.getTileSetCoordinates());
		}
	}
	public Collection<Actor> getActorsAt(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		}
		else {
			return getMapAt(coordinates.getMapCoordinates()).getTileSetAt(coordinates.getTileSetCoordinates()).getTileAt(coordinates.getTileCoordinates()).getActorsAt(coordinates.getPixelCoordinates());
		}
	}
}
