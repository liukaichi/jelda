package org.jelda.quest.world;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import org.jelda.player.Write;
import org.jelda.quest.manifest.Manifest.WorldManifest;

import orj.jelda.abstracts.Pool;

public class WorldPool extends Pool<World> {

	public WorldPool(File loadDir) {
		super(loadDir);
	}

	@Override
	protected void init() {
		manifest.load(new File(loadDir, "worlds.manifest"));
		idToFilenameMap = new HashMap<String, String>();
		final int spriteCount = Integer.parseInt(manifest.getWorldProperty(WorldManifest.NUMWORLDS));
		final String[] names = manifest.getWorldProperty(WorldManifest.FOLDERLIST).split(",");
		final String[] ids = manifest.getWorldProperty(WorldManifest.IDLIST).split(",");
		if (names.length != ids.length) {
			Write.info("Problem in " + new File(loadDir, "sprites.manifest").getAbsolutePath() + ": differing number of names and ID's. Some may be ignored.");
		}
		for (int i = 0; i < spriteCount; i++) {
			idToFilenameMap.put(ids[i].trim(), names[i].trim());
		}
	}

	@Override
	public World load(String id) {
		World world = new World(new File(loadDir, idToFilenameMap.get(id)));
		return world;
	}
	
	@Override
	public void loadAll(Collection<String> ids, boolean pruneOld) {
		super.loadAll(ids, false);
	}
	
	public int getNumWorlds() {
		return Integer.parseInt(manifest.getWorldProperty(WorldManifest.NUMWORLDS));
	}
	
	public String getWorldFolder() {
		return manifest.getWorldProperty(WorldManifest.FOLDERLIST);
	}

}
