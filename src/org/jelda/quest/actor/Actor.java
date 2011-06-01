package org.jelda.quest.actor;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import org.jelda.quest.world.World;

public abstract class Actor {
	public static final int ACTOR_PRIORITY_DEFAULT = 0, ACTOR_PRIORITY_HIGH = 100, ACTOR_PRIORITY_LOW = -100;
	private int priority;
	private Point location;
	private boolean isVisible;
	private final boolean isPersistent;
	private Sprite currentSprite = null;
	private CollisionMap collisionMap = null;
	
	public abstract void paint(Graphics2D canvas, Rectangle viewWindow);
		
	public Actor(Sprite defaultSprite, Point location, CollisionMap collisionMap, int priority, boolean isVisible, boolean isPersistent) {
		setCurrentSprite(defaultSprite);
		setLocation(location);
		setCollisionMap(collisionMap);
		setPriority(priority);
		setVisible(isVisible);
		this.isPersistent = isPersistent;
	}
	
	public Actor(Actor other) {
		this(other.getCurrentSprite(), other.getLocation(), other.getCollisionMap(), other.getPriority(), other.isVisible(), other.isPersistent());
	}
	public int getSpriteWidth() {
		return currentSprite.getWidth();
	}
	public int getSpriteHeight() {
		return currentSprite.getHeight();
	}
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		World.boundsAreValid(location);
		this.location = location;
	}
	public void translate(int dx, int dy) {
		if (location.x + dx < 0) {
			location.x += dx;
		}
		else {
			location.x = 0;
		}
		if (location.y + dy < 0) {
			location.y += dy;
		}
		else {
			location.y = 0;
		}
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Sprite getCurrentSprite() {
		return currentSprite;
	}

	public void setCurrentSprite(Sprite currentSprite) {
		this.currentSprite = currentSprite;
	}

	public CollisionMap getCollisionMap() {
		return collisionMap;
	}

	public void setCollisionMap(CollisionMap collisionMap) {
		this.collisionMap = collisionMap;
	}

	public boolean isPersistent() {
		return isPersistent;
	}
}
