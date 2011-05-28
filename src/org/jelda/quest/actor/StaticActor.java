package org.jelda.quest.actor;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jelda.quest.map.Coordinate;


public abstract class StaticActor extends Actor {
	public StaticActor(Sprite defaultSprite, Coordinate globalCoordinates) {
		this(defaultSprite, globalCoordinates, new CollisionMap(defaultSprite, false), true, false);
	}
	public StaticActor(Sprite defaultSprite, Coordinate globalCoordinates, boolean passable) {
		this(defaultSprite, globalCoordinates, new CollisionMap(defaultSprite, !passable), true, false);
	}
	public StaticActor(Sprite defaultSprite, Coordinate globalCoordinates, CollisionMap collisionMap) {
		this(defaultSprite, globalCoordinates, collisionMap, true, false);
	}
	public StaticActor(Sprite defaultSprite, Coordinate globalCoordinates, CollisionMap collisionMap, boolean isVisible) {
		this(defaultSprite, globalCoordinates, collisionMap, isVisible, false);
	}
	public StaticActor(Sprite defaultSprite, Coordinate globalCoordinates, CollisionMap collisionMap, boolean isVisible, boolean isPersistent) {
		super(defaultSprite,globalCoordinates,collisionMap,isVisible, isPersistent);
	}
	@Override
	public void paint(Graphics2D canvas, Rectangle viewWindow) {
		//TODO 
	}
}