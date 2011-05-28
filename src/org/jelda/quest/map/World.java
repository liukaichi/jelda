package org.jelda.quest.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import org.jelda.quest.actor.Actor;

public class World {
	public HashMap<Point, Map> maps;
	public World() {
		maps = new HashMap<Point, Map>();
	}
	public World(HashMap<Point, Map> maps) {
		this.maps = maps;
	}
	public void addMapAt(Point point, Map map) {
		maps.put(point, map);
	}
	public Map getMapAt(Point point) {
		return maps.get(point);
	}
	public ArrayList<Actor> getActorsAt(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		}
		else {
			return getMapAt(coordinates.getMapCoordinates()).getTileSetAt(coordinates.getTileSetCoordinates()).getTileAt(coordinates.getTileCoordinates()).getActorsAt(coordinates.getPixelCoordinates());
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
}
