package org.jelda.quest.map;

import java.awt.Point;
import java.security.InvalidParameterException;

public class Coordinate {
	private Point mapCoordinates = null, tileSetCoordinates = null,
			tileCoordinates = null, pixelCoordinates = null;

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
		} else {
			throw new InvalidParameterException("Map Coordinates are required");
		}
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
		this.mapCoordinates = new Point(mapCoordinates);
	}

	public void setTileSetCoordinates(Point tileSetCoordinates) {
		this.tileSetCoordinates = new Point(tileSetCoordinates);
	}

	public void setTileCoordinates(Point tileCoordinates) {
		this.tileCoordinates = new Point(tileCoordinates);
	}

	public void setPixelCoordinates(Point pixelCoordinates) {
		this.pixelCoordinates = new Point(pixelCoordinates);
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
	public void translatePixels(int dx, int dy) {
		if (dx != 0) {
			if (dx < 0) {
				
			}
			else {
				
			}
		}
		if (dy != 0) {
			
		}
	}
	public static boolean isValidCoordinate(Coordinate point) {
		Point map = point.mapCoordinates, tileSet = point.tileSetCoordinates, tile = point.tileCoordinates, pixel = point.pixelCoordinates;
		if (map != null && map.x >= 0 && map.y >= 0) {
			if (tileSet != null) {
				if (tileSet.x >= 0 && tileSet.y >= 0) {
					if (tile != null) {
						if (tile.x >= 0 && tile.y >= 0) {
							if (pixel != null) {
								if (pixel.x >= 0 && pixel.y >= 0) {
									return true;
								}
								else {
									return false;
								}
							}
							else {
								return true;
							}
						}
						else {
							return false;
						}
					}
					else {
						return true;
					}
				}
				else {
					return false;
				}
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

}
