package org.jelda.quest.world;

import java.io.File;
import java.util.HashMap;

import org.jelda.player.Write;
import org.jelda.quest.manifest.Manifest.RoomManifest;

import orj.jelda.abstracts.Pool;

public class RoomPool extends Pool<Room> {

	public RoomPool(File loadDir) {
		super(loadDir);
	}

	@Override
	protected void init() {
		manifest.load(new File(loadDir, "rooms.manifest"));
		idToFilenameMap = new HashMap<String, String>();
		final int roomCount = Integer.parseInt(manifest.getRoomProperty(RoomManifest.NUMROOMS));
		final String[] names = manifest.getRoomProperty(RoomManifest.FILENAMELIST).split(",");
		final String[] ids = manifest.getRoomProperty(RoomManifest.IDLIST).split(",");
		if (names.length != ids.length) {
			Write.info("Problem in " + new File(loadDir, "rooms.manifest").getAbsolutePath() + ": differing number of names and ID's. Some may be ignored.");
		}
		for (int i = 0; i < roomCount; i++) {
			idToFilenameMap.put(ids[i].trim(), names[i].trim());
		}
	}

	@Override
	public Room load(String id) {
		Write.info("loaddir:"  + loadDir + " name: " + idToFilenameMap.get(id));
		Room room = new Room(new File(loadDir, idToFilenameMap.get(id)));
		return room;
	}
	
	public int getNumRooms() {
		return Integer.parseInt(manifest.getRoomProperty(RoomManifest.NUMROOMS));
	}
	
	public String getFilenameList() {
		return manifest.getRoomProperty(RoomManifest.FILENAMELIST);
	}
	
	public String getIDList() {
		return manifest.getRoomProperty(RoomManifest.IDLIST);
	}

}
