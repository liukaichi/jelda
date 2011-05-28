package org.jelda.quest.actor;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jelda.quest.map.Coordinate;


public abstract class Actor {
	public static final int ACTOR_HEIGHT_DEFAULT = 0, ACTOR_HEIGHT_HIGH = 100, ACTOR_HEIGHT_LOW = -100;
	public int height;
	public Coordinate location;
	public boolean isVisible;
	public final boolean isPersistent;
	public Sprite currentSprite = null;
	public CollisionMap collisionMap = null;
	
	public abstract void paint(Graphics2D canvas, Rectangle viewWindow);
		
	public Actor(Sprite defaultSprite, Coordinate location, CollisionMap collisionMap, int height, boolean isVisible, boolean isPersistent) {
		this.currentSprite = defaultSprite;
		this.location = location;
		this.collisionMap = collisionMap;
		this.height = height;
		this.isVisible = isVisible;
		this.isPersistent = isPersistent;
	}
	
	public Actor(Actor other) {
		this.location = other.location;
		this.isVisible = other.isVisible;
		this.isPersistent = other.isPersistent;
		this.currentSprite = other.currentSprite;
		this.collisionMap = other.collisionMap;
		this.height = other.height;
	}
	public int getSpriteWidth() {
		return currentSprite.getWidth();
	}
	public int getSpriteHeight() {
		return currentSprite.getHeight();
	}
}
