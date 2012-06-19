package org.jelda.quest.world;

import java.io.File;

public class World {
	RoomPool roomPool;
	public World(File f) {
		roomPool = new RoomPool(f);
		
	}
	public RoomPool getRoomPool() {
		return roomPool;
	}
	public void setRoomPool(RoomPool roomPool) {
		this.roomPool = roomPool;
	}
}
