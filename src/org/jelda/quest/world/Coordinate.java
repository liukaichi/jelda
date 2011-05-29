package org.jelda.quest.world;

import java.awt.Point;
import java.security.InvalidParameterException;
import java.util.Collection;

import org.jelda.quest.Quest;
import org.jelda.quest.actor.Actor;

public class Coordinate {
	private Point mapCoordinates = null, tileSetCoordinates = null,
			tileCoordinates = null, pixelCoordinates = null;
	public Map containedMap = null;
	public TileSet containedTileSet = null;
	public Tile containedTile = null;
	public Collection<Actor> containedActors = null;

	public Coordinate(Coordinate other) {
		this(other.getMapCoordinates(), other.getTileSetCoordinates(), other
				.getTileCoordinates(), other.getPixelCoordinates());
	}

	public Coordinate(Point mapCoordinates) {
		this(mapCoordinates, null, null, null);
	}

	public Coordinate(Point mapCoordinates, Point tileSetCoordinates) {
		this(mapCoordinates, tileSetCoordinates, null, null);
	}

	public Coordinate(Point mapCoordinates, Point tileSetCoordinates,
			Point tileCoordinates) {
		this(mapCoordinates, tileSetCoordinates, tileCoordinates, null);
	}

	public Coordinate(Point mapCoordinates, Point tileSetCoordinates,
			Point tileCoordinates, Point pixelCoordinates) {
		if (mapCoordinates != null) {
			this.mapCoordinates = new Point(mapCoordinates);
			if (tileSetCoordinates != null) {
				this.tileSetCoordinates = new Point(tileSetCoordinates);
				if (tileCoordinates != null) {
					this.tileCoordinates = new Point(tileCoordinates);
					if (pixelCoordinates != null) {
						this.pixelCoordinates = new Point(pixelCoordinates);
					}
				}
			}
			getReferencedItems();
			
		} else {
			throw new InvalidParameterException("Map Coordinates are required");
		}
	}

	private void getReferencedItems() {
		containedMap = Quest.world.getMapAt(this);
		containedTileSet = Quest.world.getTileSetAt(this);
		containedTile = Quest.world.getTileAt(this);
		containedActors = Quest.world.getActorsAt(this);
	}
	public Point getMapCoordinates() {
		return new Point(mapCoordinates);
	}

	public Point getTileSetCoordinates() {
		return new Point(tileSetCoordinates);
	}

	public Point getTileCoordinates() {
		return new Point(tileCoordinates);
	}

	public Point getPixelCoordinates() {
		return new Point(pixelCoordinates);
	}

	public void setMapCoordinates(Point mapCoordinates) {
		if (mapCoordinates == null) {
			throw new InvalidParameterException("mapCoordinates cannot be null");
		}
		if (!isValid(mapCoordinates)) {
			throw new InvalidParameterException("mapCoordinates are not valid");
		}
		this.mapCoordinates = new Point(mapCoordinates);
		getReferencedItems();
	}

	public void setTileSetCoordinates(Point tileSetCoordinates) {
		if (!isValid(tileSetCoordinates)) {
			throw new InvalidParameterException("tileSetCoordinates are not valid");
		}
		this.tileSetCoordinates = new Point(tileSetCoordinates);
		getReferencedItems();
	}

	public void setTileCoordinates(Point tileCoordinates) {
		if (!isValid(tileCoordinates)) {
			throw new InvalidParameterException("tileCoordinates are not valid");
		}
		this.tileCoordinates = new Point(tileCoordinates);
		getReferencedItems();
	}

	public void setPixelCoordinates(Point pixelCoordinates) {
		if (!isValid(pixelCoordinates)) {
			throw new InvalidParameterException("pixelCoordinates are not valid");
		}
		this.pixelCoordinates = new Point(pixelCoordinates);
		getReferencedItems();
	}

	public boolean hasPixelCoordinates() {
		return pixelCoordinates != null;
	}

	public boolean hasTileCoordinates() {
		return tileCoordinates != null;
	}

	public boolean hasTileSetCoordinates() {
		return tileSetCoordinates != null;
	}

	public boolean hasMapCoordinates() {
		return mapCoordinates != null;
	}

	public boolean hasAllCoordinates() {
		return hasMapCoordinates() && hasTileSetCoordinates()
				&& hasTileCoordinates() && hasPixelCoordinates();
	}
	
	public void translatePixel(int dx, int dy) {
		Point coordinates = pixelCoordinates;
		if (dx != 0) {
			if (dx > 0) {
				coordinates.x += dx;
			}
			else {
				if (coordinates.x + dx < 0) {
					pixelCoordinates.y = 0;
				}
				else {
					coordinates.x = coordinates.x + dx;
				}
			}
			getReferencedItems();
		}
		if (dy != 0) {
			if (dy > 0) {
				coordinates.y += dy;
			}
			else {
				if (coordinates.y + dy < 0) {
					pixelCoordinates.y = 0;
				}
				else {
					coordinates.y = coordinates.y + dy;
				}
			}
			getReferencedItems();
		}
	}
	
	public void translateTile(int dx, int dy) {
		Point coordinates = tileCoordinates;
		if (dx != 0) {
			if (dx > 0) {
				coordinates.x += dx;
			}
			else {
				if (coordinates.x + dx < 0) {
					tileCoordinates.y = 0;
					pixelCoordinates.y = 0;
				}
				else {
					coordinates.x = coordinates.x + dx;
				}
			}
			getReferencedItems();
		}
		if (dy != 0) {
			if (dy > 0) {
				coordinates.y += dy;
			}
			else {
				if (coordinates.y + dy < 0) {
					tileCoordinates.y = 0;
					pixelCoordinates.y = 0;
				}
				else {
					coordinates.y = coordinates.y + dy;
				}
			}
			getReferencedItems();
		}
	}
	
	public void translateTileSet(int dx, int dy) {
		Point coordinates = tileSetCoordinates;
		if (dx != 0) {
			if (dx > 0) {
				coordinates.x += dx;
			}
			else {
				if (coordinates.x + dx < 0) {
					tileCoordinates.y = 0;
					tileSetCoordinates.y = 0;
					pixelCoordinates.y = 0;
				}
				else {
					coordinates.x = coordinates.x + dx;
				}
			}
			getReferencedItems();
		}
		if (dy != 0) {
			if (dy > 0) {
				coordinates.y += dy;
			}
			else {
				if (coordinates.y + dy < 0) {
					tileCoordinates.y = 0;
					tileSetCoordinates.y = 0;
					pixelCoordinates.y = 0;
				}
				else {
					coordinates.y = coordinates.y + dy;
				}
			}
			getReferencedItems();
		}
		
	}

	public void translateMap(int dx, int dy) {
		Point coordinates = mapCoordinates;
		if (dx != 0) {
			if (dx > 0) {
				coordinates.x += dx;
			}
			else {
				if (coordinates.x + dx < 0) {
					mapCoordinates.x = 0;
					tileCoordinates.x = 0;
					tileSetCoordinates.x = 0;
					pixelCoordinates.x = 0;
				}
				else {
					coordinates.x = coordinates.x + dx;
				}
			}
			getReferencedItems();
		}
		if (dy != 0) {
			if (dy > 0) {
				coordinates.y += dy;
			}
			else {
				if (coordinates.y + dy < 0) {
					mapCoordinates.y = 0;
					tileCoordinates.y = 0;
					tileSetCoordinates.y = 0;
					pixelCoordinates.y = 0;
				}
				else {
					coordinates.y = coordinates.y + dy;
				}
			}
			getReferencedItems();
		}
		
	}

	private static boolean isValid(Point p) {
		if (p != null) {
			return p.x >= 0 && p.y >= 0;
		} else {
			return true;
		}
	}

	public static boolean isValidCoordinate(Coordinate point) {
		Point map = point.mapCoordinates, tileSet = point.tileSetCoordinates, tile = point.tileCoordinates, pixel = point.pixelCoordinates;
		if (map != null && isValid(map)) {
			return isValid(tileSet) && isValid(tile) && isValid(pixel);
		} else {
			return false;
		}
	}
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(mapCoordinates.toString());
		b.append(tileSetCoordinates.toString());
		b.append(tileCoordinates.toString());
		b.append(pixelCoordinates.toString());
		return b.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mapCoordinates == null) ? 0 : mapCoordinates.hashCode());
		result = prime
				* result
				+ ((pixelCoordinates == null) ? 0 : pixelCoordinates.hashCode());
		result = prime * result
				+ ((tileCoordinates == null) ? 0 : tileCoordinates.hashCode());
		result = prime
				* result
				+ ((tileSetCoordinates == null) ? 0 : tileSetCoordinates
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (mapCoordinates == null) {
			if (other.mapCoordinates != null)
				return false;
		} else if (!mapCoordinates.equals(other.mapCoordinates))
			return false;
		if (pixelCoordinates == null) {
			if (other.pixelCoordinates != null)
				return false;
		} else if (!pixelCoordinates.equals(other.pixelCoordinates))
			return false;
		if (tileCoordinates == null) {
			if (other.tileCoordinates != null)
				return false;
		} else if (!tileCoordinates.equals(other.tileCoordinates))
			return false;
		if (tileSetCoordinates == null) {
			if (other.tileSetCoordinates != null)
				return false;
		} else if (!tileSetCoordinates.equals(other.tileSetCoordinates))
			return false;
		return true;
	}

	public static void main(String[] args) {
		new Quest();
		Point one = new Point(1,1);
		Coordinate test = new Coordinate(one, one, one, one);
		test.translateMap(-1, -1);
		System.out.println(test);
//		test.translateMap(-1, -1);
		System.out.println(test);
		test.translateTileSet(-1, -1);
		System.out.println(test);
	} 

}
