package org.jelda.quest;

import org.jelda.quest.map.World;

public class Quest {
	World world;
	public Quest() {
		world = new World();
	}
	public Quest(World world) {
		this.world = world;
	}
}
