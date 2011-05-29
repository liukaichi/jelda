package org.jelda.quest.actor;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jelda.quest.world.Coordinate;


public abstract class StaticActor extends Actor {
	public static final int DEFAULT_UPDATE_RATE = 50;
	public int updateRate;
	public StaticActor(Sprite defaultSprite, Coordinate location, int height) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, false), height, true, false);
	}
	public StaticActor(Sprite defaultSprite, Coordinate location, int height, boolean passable) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, !passable), height, true, false);
	}
	public StaticActor(Sprite defaultSprite, Coordinate location, int height, CollisionMap collisionMap) {
		this(defaultSprite, location, collisionMap, height, true, false);
	}
	public StaticActor(Sprite defaultSprite, Coordinate location, int height, CollisionMap collisionMap, boolean isVisible) {
		this(defaultSprite, location, collisionMap, height, isVisible, false);
	}
	public StaticActor(Sprite defaultSprite, Coordinate location) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, false), Actor.ACTOR_HEIGHT_DEFAULT, true, false);
	}
	public StaticActor(Sprite defaultSprite, Coordinate location, boolean passable) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, !passable), Actor.ACTOR_HEIGHT_DEFAULT, true, false);
	}
	public StaticActor(Sprite defaultSprite, Coordinate location, CollisionMap collisionMap) {
		this(defaultSprite, location, collisionMap, Actor.ACTOR_HEIGHT_DEFAULT, true, false);
	}
	public StaticActor(Sprite defaultSprite, Coordinate location, CollisionMap collisionMap, boolean isVisible) {
		this(defaultSprite, location, collisionMap, Actor.ACTOR_HEIGHT_DEFAULT, isVisible, false);
	}
	public StaticActor(Sprite defaultSprite, Coordinate location, CollisionMap collisionMap, int height, boolean isVisible, boolean isPersistent) {
		super(defaultSprite,location,collisionMap,height, isVisible, isPersistent);
		this.updateRate = DEFAULT_UPDATE_RATE;
	}
	@Override
	public void paint(Graphics2D canvas, Rectangle viewWindow) {
		//TODO 
	}
	
	public int start() {
		return updateRate;
	}
	public int update() {
		return updateRate;
	}
	public void stop() {
		return;
	}
}