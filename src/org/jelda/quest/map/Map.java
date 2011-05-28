package org.jelda.quest.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import org.jelda.quest.actor.Actor;

public class Map {
	public HashMap<Point, TileSet> tileSets;
	public Map() {
		tileSets = new HashMap<Point, TileSet>();
	}
	public Map(HashMap<Point, TileSet> tileSets) {
		this.tileSets = tileSets;
	}
	public void addTileSetAt(Point point, TileSet tileSet) {
		tileSets.put(point, tileSet);
	}
	public TileSet getTileSetAt(Point point) {
		return tileSets.get(point);
	}
	public ArrayList<Actor> getActorsAt(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		}
		else {
			return getTileSetAt(coordinates.getTileSetCoordinates()).getTileAt(coordinates.getTileCoordinates()).getActorsAt(coordinates.getPixelCoordinates());
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
	
}
