package org.jelda.quest.world;

import java.awt.geom.AffineTransform;

import org.jelda.quest.sprite.Sprite;
import org.jelda.quest.sprite.SpritePool;

public class Tile {
	private int x, y, z;
	private AffineTransform transform;
	private String actorID;
	Tile() {
		super();
		transform = new AffineTransform();
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public void setZ(int z) {
		this.z = z;
	}
	
	public int getZ() {
		return z;
	}
	
	public void translateX(int dx) {
		x += dx;
	}
	
	public void translateY(int dy) {
		y += dy;
	}
	
	public void rotateSprite(double theta) {
		transform.rotate(theta);
	}
	
	public void scaleSprite(double scalex, double scaley) {
		transform.scale(scalex, scaley);
	}
	
	public boolean transformIsIdentity() {
		return transform.isIdentity();
	}
	
	public void setActorID(String id) {
		actorID = id;
	}
	
	public String getActorID(String id) {
		return actorID;
	}
	
	public AffineTransform getTransform() {
		return transform;
	}

	@Override
	public String toString() {
		return "Tile [x=" + x + ", y=" + y + ", z=" + z + ", transform="
				+ transform + ", actorID=" + actorID
				+ "]";
	}
	

}
