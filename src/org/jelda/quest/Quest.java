package org.jelda.quest;

import org.jelda.quest.world.World;

public class Quest {
	public static World world;
	public Quest() {
		this ( new World() );
	}
	public Quest(World world) {
		Quest.world = world;
	}
}
