package org.jelda.quest.actor;

import java.awt.Point;

import org.jelda.quest.actor.Actor;
import org.jelda.quest.scriptable.Scriptable;

public abstract class ScriptableActor extends Actor implements Scriptable{
	public static final int DEFAULT_UPDATE_RATE = 50;
	public int updateRate;
	public ScriptableActor(Sprite defaultSprite, Point location, int height, boolean isPersistent) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, isPersistent), height, true, isPersistent);
	}
	public ScriptableActor(Sprite defaultSprite, Point location, int height, boolean passable, boolean isPersistent) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, !passable), height, true, isPersistent);
	}
	public ScriptableActor(Sprite defaultSprite, Point location, int height, CollisionMap collisionMap, boolean isPersistent) {
		this(defaultSprite, location, collisionMap, height, true, isPersistent);
	}
	public ScriptableActor(Sprite defaultSprite, Point location, boolean isPersistent) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, isPersistent), Actor.ACTOR_PRIORITY_DEFAULT, true, isPersistent);
	}
	public ScriptableActor(Sprite defaultSprite, Point location, boolean passable, boolean isPersistent) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, !passable), Actor.ACTOR_PRIORITY_DEFAULT, true, isPersistent);
	}
	public ScriptableActor(Sprite defaultSprite, Point location, CollisionMap collisionMap, boolean isPersistent) {
		this(defaultSprite, location, collisionMap, Actor.ACTOR_PRIORITY_DEFAULT, true, isPersistent);
	}
	public ScriptableActor(Sprite defaultSprite, Point location, CollisionMap collisionMap, int height, boolean isVisible, boolean isPersistent) {
		super(defaultSprite,location,collisionMap,height, isVisible, isPersistent);
		this.updateRate = DEFAULT_UPDATE_RATE;
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
