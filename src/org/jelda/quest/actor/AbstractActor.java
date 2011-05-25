package org.jelda.quest.actor;

import java.awt.Graphics;


public abstract class AbstractActor {
	public boolean isVisible;
	public boolean isPersistent;
	public Sprite defaultSprite = null;
	public CollisionMap collisionMap = null;
	public abstract void paint(Graphics canvas);
		
	public AbstractActor(boolean isVisible, boolean isPersistent, Sprite defaultSprite, CollisionMap collisionMap) {
		this.isVisible = isVisible;
		this.isPersistent = isPersistent;
		this.defaultSprite = defaultSprite;
		this.collisionMap = collisionMap;
	}
}
