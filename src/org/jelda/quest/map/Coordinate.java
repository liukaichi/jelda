package org.jelda.quest.map;

import java.awt.Point;

public class Coordinate {
	private Point mapCoordinates = null, tileSetCoordinates = null, tileCoordinates = null, pixelCoordinates = null;

	public Coordinate(Coordinate other) {
		this(other.getMapCoordinates(), other.getTileSetCoordinates(), other.getTileCoordinates(), other.getPixelCoordinates());
	}
	public Coordinate(Point mapCoordinates) {
		this(mapCoordinates, null, null, null);
	}
	public Coordinate(Point mapCoordinates, Point tileSetCoordinates) {
		this(mapCoordinates, tileSetCoordinates, null, null);
	}
	
	public Coordinate(Point mapCoordinates, Point tileSetCoordinates,
			Point tileCoordinates) {
		this(mapCoordinates,tileSetCoordinates,tileCoordinates, null);
	}
	public Coordinate(Point mapCoordinates, Point tileSetCoordinates,
			Point tileCoordinates, Point pixelCoordinates) {
		this.mapCoordinates = new Point(mapCoordinates);
		this.tileSetCoordinates = new Point(tileSetCoordinates);
		this.tileCoordinates = new Point(tileCoordinates);
		this.pixelCoordinates = new Point(pixelCoordinates);
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
		return hasMapCoordinates() && hasTileSetCoordinates() && hasTileCoordinates() && hasPixelCoordinates();
	}
	
}
