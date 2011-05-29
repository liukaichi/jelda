package org.jelda.quest.actor;

import org.jelda.quest.actor.Actor;
import org.jelda.quest.scriptable.Scriptable;
import org.jelda.quest.world.Coordinate;

public abstract class ScriptableActor extends Actor implements Scriptable{

	public ScriptableActor(Sprite defaultSprite, Coordinate location, int height, boolean isPersistent) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, isPersistent), height, true, isPersistent);
	}
	public ScriptableActor(Sprite defaultSprite, Coordinate location, int height, boolean passable, boolean isPersistent) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, !passable), height, true, isPersistent);
	}
	public ScriptableActor(Sprite defaultSprite, Coordinate location, int height, CollisionMap collisionMap, boolean isPersistent) {
		this(defaultSprite, location, collisionMap, height, true, isPersistent);
	}
	public ScriptableActor(Sprite defaultSprite, Coordinate location, boolean isPersistent) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, isPersistent), Actor.ACTOR_HEIGHT_DEFAULT, true, isPersistent);
	}
	public ScriptableActor(Sprite defaultSprite, Coordinate location, boolean passable, boolean isPersistent) {
		this(defaultSprite, location, new CollisionMap(defaultSprite, !passable), Actor.ACTOR_HEIGHT_DEFAULT, true, isPersistent);
	}
	public ScriptableActor(Sprite defaultSprite, Coordinate location, CollisionMap collisionMap, boolean isPersistent) {
		this(defaultSprite, location, collisionMap, Actor.ACTOR_HEIGHT_DEFAULT, true, isPersistent);
	}
	public ScriptableActor(Sprite defaultSprite, Coordinate location, CollisionMap collisionMap, int height, boolean isVisible, boolean isPersistent) {
		super(defaultSprite,location,collisionMap,height, isVisible, isPersistent);
	}
	
}
