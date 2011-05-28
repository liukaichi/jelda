package org.jelda.quest.actor;

import org.jelda.quest.actor.Actor;
import org.jelda.quest.map.Coordinate;
import org.jelda.quest.scriptable.Scriptable;

public abstract class ScriptableActor extends Actor implements Scriptable{

	public ScriptableActor(Sprite defaultSprite, Coordinate location,
			CollisionMap collisionMap, int height, boolean isVisible, boolean isPersistent) {
		super(defaultSprite, location, collisionMap, height, isVisible, isPersistent);
		// TODO Auto-generated constructor stub
	}
	
}
