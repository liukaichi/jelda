package org.jelda.quest.actor;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jelda.quest.map.Coordinate;


public abstract class Actor {
	public Coordinate location;
	public boolean isVisible;
	public final boolean isPersistent;
	public Sprite currentSprite = null;
	public CollisionMap collisionMap = null;
	
	public abstract void paint(Graphics2D canvas, Rectangle viewWindow);
		
	public Actor(Sprite defaultSprite, Coordinate globalLocation, CollisionMap collisionMap, boolean isVisible, boolean isPersistent) {
		this.currentSprite = defaultSprite;
		this.location = globalLocation;
		this.collisionMap = collisionMap;
		this.isVisible = isVisible;
		this.isPersistent = isPersistent;
	}
	
	public Actor(Actor other) {
		this.location = other.location;
		this.isVisible = other.isVisible;
		this.isPersistent = other.isPersistent;
		this.currentSprite = other.currentSprite;
		this.collisionMap = other.collisionMap;
	}
	public int getWidth() {
		return currentSprite.getWidth();
	}
	public int getHeight() {
		return currentSprite.getHeight();
	}
}
