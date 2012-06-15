package org.jelda.quest;

import org.jelda.world.World;

public class Quest {
	World world;
	Manifest manifest;
	public Quest() {
		world = new World();
	}
	public void setManifest(Manifest manifest) {
		this.manifest = manifest;
	}
	public Manifest getManifest() {
		return manifest;
	}
	public String getQuestAuthor() {
		return manifest.getQuestAuthor();
	}
	public String getQuestVersion() {
		return manifest.getQuestVersion();
	}
	public String getQuestName() {
		return manifest.getQuestName();
	}
}
