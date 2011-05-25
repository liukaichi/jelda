package org.jelda.quest.actor;

import java.awt.Graphics;


public abstract class StaticActor extends AbstractActor {
	public StaticActor(Sprite defaultSprite) {
		super(true,false,defaultSprite,new CollisionMap(defaultSprite, false));
	}
	public StaticActor(Sprite defaultSprite, CollisionMap collisionMap) {
		super(true,false,defaultSprite,collisionMap);
	}
	public StaticActor(Sprite defaultSprite, CollisionMap collisionMap, boolean isVisible) {
		super(isVisible,false,defaultSprite,collisionMap);
	}
	public StaticActor(Sprite defaultSprite, CollisionMap collisionMap, boolean isVisible, boolean isPersistent) {
		super(isVisible,isPersistent,defaultSprite,collisionMap);
	}
	@Override
	public void paint(Graphics canvas) {
		if (isVisible) {
			//TODO When coordinates are implemented, fix this call
			//canvas.drawImage(getDefaultSprite().sprite, getX(), getY(), null);
		}
	}
}