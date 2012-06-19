package org.jelda.quest.actor;

import java.io.File;

import org.jelda.player.Write;
import org.jelda.quest.manifest.Manifest.ActorManifest;

import orj.jelda.abstracts.Pool;

public class ActorPool extends Pool<Actor>{
	public ActorPool(File loadDir) {
		super(loadDir);
	}
	
	@Override
	protected void init() {
		manifest.load(new File(loadDir, "actors.manifest"));
		final int actorCount = Integer.parseInt(manifest.getActorProperty(ActorManifest.NUMIMAGES));
		final String[] names = manifest.getActorProperty(ActorManifest.FILENAMELIST).split(",");
		final String[] ids = manifest.getActorProperty(ActorManifest.IDLIST).split(",");
		if (names.length != ids.length) {
			Write.info("Problem in " + new File(loadDir, "actors.manifest").getAbsolutePath() + ": differing number of names and ID's. Some may be ignored.");
		}
		for (int i = 0; i < actorCount; i++) {
			idToFilenameMap.put(ids[i].trim(), names[i].trim());
		}
	}
	
	@Override
	public Actor load(String id) {
		Actor actor = new Actor(new File(loadDir, idToFilenameMap.get(id)));
		return actor;
	}

}
