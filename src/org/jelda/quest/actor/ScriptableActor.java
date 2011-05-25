package org.jelda.quest.actor;

import org.jelda.quest.actor.AbstractActor;
import org.jelda.quest.scriptable.Scriptable;

public abstract class ScriptableActor extends AbstractActor implements Scriptable{

	public ScriptableActor(boolean isVisible, boolean isPersistent, int startX,
			int startY, Sprite defaultSprite, CollisionMap collisionMap) {
		super(isVisible, isPersistent, defaultSprite, collisionMap);
		// TODO Auto-generated constructor stub
	}
	
}
