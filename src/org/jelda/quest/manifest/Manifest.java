package org.jelda.quest.manifest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.jelda.player.Write;

public class Manifest {
	Properties manifest;
	
	public enum QuestManifest {
	    QUESTVERSION("quest.version"),
	    QUESTAUTHOR("quest.author"),
	    QUESTNAME("quest.name"),
	    QUESTWIDTH("quest.width"),
	    QUESTHEIGHT("quest.height")
	    ;
	    private QuestManifest(final String text) {
	        this.key = text;
	    }

	    public final String key;

	}
	public enum ImageManifest {
		NUMIMAGES("number.images"),
		FILENAMELIST("filename.list"),
		IDLIST("id.list");
		
		private ImageManifest(final String text) {
	        this.key = text;
	    }

	    public final String key;
	}
	public enum ActorManifest {
		NUMIMAGES("number.actors"),
		FILENAMELIST("filename.list"),
		IDLIST("id.list"),
		ACTORSPRITE("actor.sprite");
		
		private ActorManifest(final String text) {
	        this.key = text;
	    }

	    public final String key;
	}
	
	public enum WorldManifest {
		NUMWORLDS("number.worlds"),
		FOLDERLIST("folder.list"),
		IDLIST("id.list"),
		STARTWORLD("start.world");
		
		private WorldManifest(final String text) {
	        this.key = text;
	    }

	    public final String key;
	}
	
	public enum RoomManifest {
		NUMROOMS("number.rooms"),
		FILENAMELIST("file.list"),
		IDLIST("id.list"),
		STARTROOM("start.room");
		
		private RoomManifest(final String text) {
	        this.key = text;
	    }

	    public final String key;
	}
	
	public Manifest() {
		
	}
	
	public Manifest(File f) {
		load(f);
	}
	
	public void load(File f) {
		manifest = new Properties();
		try {
			FileInputStream in = new FileInputStream(f);
			manifest.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			Write.error("Could not find manifest at " + f.getAbsolutePath());
		} catch (IOException e) {
			Write.error("Could not read manifest at " + f.getAbsolutePath());
		}
	}
	
	public String getQuestProperty(QuestManifest property) {
		return manifest.getProperty(property.key);
	}
	
	public String getImageProperty(ImageManifest property) {
		return manifest.getProperty(property.key);
	}
	
	public String getActorProperty(ActorManifest property) {
		return manifest.getProperty(property.key);
	}
	
	public String getWorldProperty(WorldManifest property) {
		return manifest.getProperty(property.key);
	}
	
	public String getRoomProperty(RoomManifest property) {
		return manifest.getProperty(property.key);
	}
}
