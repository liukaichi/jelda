package org.jelda.quest.actor;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


public abstract class StaticActor extends Actor {
	public StaticActor(Sprite defaultSprite, Point location, int height) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, false), height, true, false);
	}
	public StaticActor(Sprite defaultSprite, Point location, int height, boolean passable) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, !passable), height, true, false);
	}
	public StaticActor(Sprite defaultSprite, Point location, int height, CollisionMap collisionMap) {
		this(defaultSprite, location, collisionMap, height, true, false);
	}
	public StaticActor(Sprite defaultSprite, Point location, int height, CollisionMap collisionMap, boolean isVisible) {
		this(defaultSprite, location, collisionMap, height, isVisible, false);
	}
	public StaticActor(Sprite defaultSprite, Point location) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, false), Actor.ACTOR_PRIORITY_DEFAULT, true, false);
	}
	public StaticActor(Sprite defaultSprite, Point location, boolean passable) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, !passable), Actor.ACTOR_PRIORITY_DEFAULT, true, false);
	}
	public StaticActor(Sprite defaultSprite, Point location, CollisionMap collisionMap) {
		this(defaultSprite, location, collisionMap, Actor.ACTOR_PRIORITY_DEFAULT, true, false);
	}
	public StaticActor(Sprite defaultSprite, Point location, CollisionMap collisionMap, boolean isVisible) {
		this(defaultSprite, location, collisionMap, Actor.ACTOR_PRIORITY_DEFAULT, isVisible, false);
	}
	public StaticActor(Sprite defaultSprite, Point location, CollisionMap collisionMap, int height, boolean isVisible, boolean isPersistent) {
		super(defaultSprite,location,collisionMap,height, isVisible, isPersistent);
	}
	@Override
	public void paint(Graphics2D canvas, Rectangle viewWindow) {
		//TODO 
	}

}