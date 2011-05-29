package org.jelda.quest.world;

import java.awt.Point;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.jelda.quest.actor.Actor;

public class World {
	public static final int DEFAULT_WORLD_WIDTH = 1, DEFAULT_WORLD_HEGHT = 1;
	public static final int DEFAULT_MAP_WIDTH = 32, DEFAULT_MAP_HEGHT = 32;
	public static final int DEFAULT_TILESET_WIDTH = 16,
			DEFAULT_TILESET_HEGHT = 16;
	public static final int DEFAULT_TILE_WIDTH = 32, DEFAULT_TILE_HEGHT = 32;
	public final int width, height;
	public HashMap<Point, Map> maps;

	public World() {
		this(DEFAULT_WORLD_WIDTH, DEFAULT_WORLD_HEGHT);
	}

	public World(int width, int height) {
		this(new HashMap<Point, Map>(), width, height);
	}

	public World(HashMap<Point, Map> maps, int width, int height) {
		this.maps = maps;
		this.width = width;
		this.height = height;
	}

	public void checkBounds(Point point) {
		if (point.x >= width) {
			throw new InvalidParameterException(
					"Trying to add outside map. point.x cannot be >= width");
		} else if (point.y >= height) {
			throw new InvalidParameterException(
					"Tring to add outside map. point.y cannot be >= height");
		} else if (point.x < 0) {
			throw new InvalidParameterException(
					"Tring to add outside map. point.x cannot be < 0");
		} else if (point.y < 0) {
			throw new InvalidParameterException(
					"Tring to add outside map. point.y cannot be < 0");
		}
	}

	public void addMapAt(Point point, Map map) {
		checkBounds(point);
		maps.put(point, map);
	}

	public Map getMapAt(Point point) {
		return maps.get(point);
	}

	public Map getMapAt(Coordinate point) {
		return maps.get(point.getMapCoordinates());
	}

	public PriorityQueue<Actor> getActorsAtAsPriorityQueueByHeight(
			Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		} else {
			try {
				return getMapAt(coordinates.getMapCoordinates())
						.getTileSetAt(coordinates.getTileSetCoordinates())
						.getTileAt(coordinates.getTileCoordinates())
						.getActorsAtAsPriorityQueueByHeight(
								coordinates.getPixelCoordinates());
			} catch (Exception e) {
				return null;
			}
		}
	}

	public Tile getTileAt(Coordinate coordinates) {
		try {
			return getMapAt(coordinates.getMapCoordinates()).getTileSetAt(
					coordinates.getTileSetCoordinates()).getTileAt(
					coordinates.getTileCoordinates());
		} catch (Exception e) {
			return null;
		}
	}

	public TileSet getTileSetAt(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		} else {
			try {
				return getMapAt(coordinates.getMapCoordinates()).getTileSetAt(
						coordinates.getTileSetCoordinates());
			} catch (Exception e) {
				return null;
			}
		}
	}

	public Collection<Actor> getActorsAt(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		} else {
			try {
				return getMapAt(coordinates.getMapCoordinates())
						.getTileSetAt(coordinates.getTileSetCoordinates())
						.getTileAt(coordinates.getTileCoordinates())
						.getActorsAt(coordinates.getPixelCoordinates());
			} catch (Exception e) {
				return null;
			}
		}
	}
}
