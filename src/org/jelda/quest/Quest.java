package org.jelda.quest;

import org.jelda.quest.actor.ActorPool;
import org.jelda.quest.manifest.Manifest;
import org.jelda.quest.manifest.Manifest.QuestManifest;
import org.jelda.quest.sprite.SpritePool;
import org.jelda.quest.world.World;

public class Quest {
	private World world;
	private Manifest questManifest;
	private SpritePool spritePool;
	private ActorPool actorPool;
	public Quest() {
		super();
	}
	public void setQuestManifest(Manifest manifest) {
		this.questManifest = manifest;
	}
	public Manifest getQuestManifest() {
		return questManifest;
	}
	public void setSpritePool(SpritePool spritePool) {
		this.spritePool = spritePool;
	}
	public SpritePool getSpritePool() {
		return spritePool;
	}
	public String getQuestAuthor() {
		return questManifest.getQuestProperty(QuestManifest.QUESTAUTHOR);
	}
	public String getQuestVersion() {
		return questManifest.getQuestProperty(QuestManifest.QUESTVERSION);
	}
	public String getQuestName() {
		return questManifest.getQuestProperty(QuestManifest.QUESTNAME);
	}
	public int getNumSprites() {
		return spritePool.getNumSprites();
	}
	public String getSpriteFilenames() {
		return spritePool.getFilenameList();
	}
	public ActorPool getActorPool() {
		return actorPool;
	}
	public void setActorPool(ActorPool actorPool) {
		this.actorPool = actorPool;
	}
}
