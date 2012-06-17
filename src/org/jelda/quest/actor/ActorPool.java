package org.jelda.quest.actor;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import org.jelda.player.Write;
import org.jelda.quest.manifest.Manifest;
import org.jelda.quest.manifest.Manifest.ActorManifest;

public class ActorPool {
	private File loadDir;
	private Manifest actorManifest;
	private HashMap<String, String> idToFilenameMap;
	private HashMap<String, Actor> idToActorMap;
	public ActorPool(File loadDir) {
		this.loadDir = loadDir;
		actorManifest = new Manifest(new File(loadDir, "actors.manifest"));
		idToFilenameMap = new HashMap<String, String>();
		final int actorCount = Integer.parseInt(actorManifest.getActorProperty(ActorManifest.NUMIMAGES));
		final String[] names = actorManifest.getActorProperty(ActorManifest.FILENAMELIST).split(",");
		final String[] ids = actorManifest.getActorProperty(ActorManifest.IDLIST).split(",");
		if (names.length != ids.length) {
			Write.info("Problem in " + new File(loadDir, "actors.manifest").getAbsolutePath() + ": differing number of names and ID's. Some may be ignored.");
		}
		for (int i = 0; i < actorCount; i++) {
			idToFilenameMap.put(ids[i].trim(), names[i].trim());
		}
		idToActorMap = new HashMap<String, Actor>();
	}
	public Actor getActor(String id) {
		if (idToFilenameMap.containsKey(id)) {
			if (idToActorMap.containsKey(id)) {
				return idToActorMap.get(id);
			}
			else {
				Actor actor = new Actor(new File(loadDir, idToFilenameMap.get(id)));
				idToActorMap.put(id, actor);
				return actor;
			}
		}
		else {
			Write.error("No actors exists with ID " +id);
			return null;
		}
	}
	public void unloadAllActors() {
		idToActorMap.clear();
	}
	
	public void unloadActor(String id) {
		Actor temp = idToActorMap.get(id);
		if (temp != null) {
			idToActorMap.remove(temp);
		}
	}
	
	public void loadAll(Collection<String> ids) {
		idToActorMap.keySet().retainAll(ids);
		for (String s : ids)
			if (!idToActorMap.containsKey(s)) {
				getActor(s);
			}
	}
	
	
	public int getNumActor() {
		return Integer.parseInt(actorManifest.getActorProperty(ActorManifest.NUMIMAGES));
	}
	
	public String getFilenameList() {
		return actorManifest.getActorProperty(ActorManifest.FILENAMELIST);
	}
	
	public String getIDList() {
		return actorManifest.getActorProperty(ActorManifest.IDLIST);
	}
}
