package org.jelda.quest.actor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.jelda.player.Write;
import org.jelda.quest.manifest.Manifest.ActorManifest;
import org.jelda.quest.sprite.Sprite;
import org.jelda.quest.sprite.SpritePool;

public class Actor {

	private String spriteID;
	
	private Sprite sprite;

	public Actor(File f) {
		Properties prop = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(f);
			prop.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			Write.error("Couldn't find actor manifest " + f.getAbsolutePath());
		} catch (IOException e) {
			Write.error("Couldn't load actor manifest " + f.getAbsolutePath());
		}
		spriteID = prop.getProperty(ActorManifest.ACTORSPRITE.key).trim();
	}

	public String getSpriteID() {
		return spriteID;
	}
	
	public void loadSprite(SpritePool spritePool) {
		sprite = spritePool.getInstance(spriteID);
	}
	
	public Sprite getSprite(SpritePool spritePool) {
		if (sprite == null) {
			loadSprite(spritePool);
		}
		return sprite;
	}
	
	public void unloadSprite(SpritePool spritePool) {
		sprite = null;
		spritePool.unload(spriteID);
	}

	public void setSpriteID(String spriteID) {
		this.spriteID = spriteID;
	}
}
