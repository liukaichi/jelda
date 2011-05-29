package org.jelda.quest.world;

import java.awt.Point;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.jelda.quest.actor.Actor;

public class Map {
	public final int width, height;
	public HashMap<Point, TileSet> tileSets;

	public Map() {
		this(World.DEFAULT_MAP_WIDTH, World.DEFAULT_MAP_HEGHT);
	}

	public Map(int width, int height) {
		this(new HashMap<Point, TileSet>(), width, height);
	}

	public Map(HashMap<Point, TileSet> tileSets, int width, int height) {
		this.tileSets = tileSets;
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

	public void addTileSetAt(Point point, TileSet tileSet) {
		checkBounds(point);
		tileSets.put(point, tileSet);
	}

	public TileSet getTileSetAt(Point point) {
		return tileSets.get(point);
	}

	public TileSet getTileSetAt(Coordinate point) {
		return tileSets.get(point.getTileSetCoordinates());
	}

	public PriorityQueue<Actor> getActorsAtAsPriorityQueueByHeight(
			Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		} else {
			try {
				return getTileSetAt(coordinates.getTileSetCoordinates())
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
			return getTileSetAt(coordinates.getTileSetCoordinates()).getTileAt(
					coordinates.getTileCoordinates());
		} catch (Exception e) {
			return null;
		}
	}

	public Collection<Actor> getActorsAt(Coordinate coordinates) {
		if (!coordinates.hasAllCoordinates()) {
			return null;
		} else {
			try {
				return getTileSetAt(coordinates.getTileSetCoordinates())
						.getTileAt(coordinates.getTileCoordinates())
						.getActorsAt(coordinates.getPixelCoordinates());
			} catch (Exception e) {
				return null;
			}
		}
	}

}
