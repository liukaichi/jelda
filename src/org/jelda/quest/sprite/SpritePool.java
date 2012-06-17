package org.jelda.quest.sprite;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.jelda.player.Write;
import org.jelda.quest.manifest.Manifest;
import org.jelda.quest.manifest.Manifest.ImageManifest;

public class SpritePool {
	private File loadDir;
	private Manifest imageManifest;
	private HashMap<String, String> idToFilenameMap;
	private HashMap<String, Sprite> idToImageMap;
	public SpritePool(File loadDir) {
		this.loadDir = loadDir;
		imageManifest = new Manifest(new File(loadDir, "sprites.manifest"));
		idToFilenameMap = new HashMap<String, String>();
		final int spriteCount = Integer.parseInt(imageManifest.getImageProperty(ImageManifest.NUMIMAGES));
		final String[] names = imageManifest.getImageProperty(ImageManifest.FILENAMELIST).split(",");
		final String[] ids = imageManifest.getImageProperty(ImageManifest.IDLIST).split(",");
		if (names.length != ids.length) {
			Write.info("Problem in " + new File(loadDir, "sprites.manifest").getAbsolutePath() + ": differing number of names and ID's. Some may be ignored.");
		}
		for (int i = 0; i < spriteCount; i++) {
			idToFilenameMap.put(ids[i].trim(), names[i].trim());
		}
		idToImageMap = new HashMap<String, Sprite>();
	}
	public Sprite getSprite(String id) {
		if (idToFilenameMap.containsKey(id)) {
			if (idToImageMap.containsKey(id)) {
				return idToImageMap.get(id);
			}
			else {
				try {
					Sprite sprite = new Sprite(ImageIO.read(new File(loadDir, idToFilenameMap.get(id))));
					idToImageMap.put(id, sprite);
					return sprite;
				} catch (IOException e) {
					Write.error("Failed to load sprite with ID " +id +" from " + idToFilenameMap.get(id));
					return null;
				}
				
			}
		}
		else {
			Write.error("No sprite exists with ID " +id);
			return null;
		}
	}
	public void unloadAllSprites() {
		for (Sprite image : idToImageMap.values()) {
			image.flush();
		}
		idToImageMap.clear();
	}
	
	public void unloadSprite(String id) {
		Sprite temp = idToImageMap.get(id);
		if (temp != null) {
			temp.flush();
			idToImageMap.remove(temp);
		}
	}
	
	public void loadAll(Collection<String> ids) {
		idToImageMap.keySet().retainAll(ids);
		for (String s: ids) {
			if (!idToImageMap.containsKey(s)) {
				getSprite(s);
			}
		}
	}
	
	public int getNumSprites() {
		return Integer.parseInt(imageManifest.getImageProperty(ImageManifest.NUMIMAGES));
	}
	
	public String getFilenameList() {
		return imageManifest.getImageProperty(ImageManifest.FILENAMELIST);
	}
	
	public String getIDList() {
		return imageManifest.getImageProperty(ImageManifest.IDLIST);
	}
}
