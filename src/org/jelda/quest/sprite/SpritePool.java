package org.jelda.quest.sprite;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.jelda.player.Write;
import org.jelda.quest.manifest.Manifest.ImageManifest;

import orj.jelda.abstracts.Pool;

public class SpritePool extends Pool<Sprite> {

	public SpritePool(File loadDir) {
		super(loadDir);
	}

	@Override
	protected void init() {
		manifest.load(new File(loadDir, "sprites.manifest"));
		idToFilenameMap = new HashMap<String, String>();
		final int spriteCount = Integer.parseInt(manifest.getImageProperty(ImageManifest.NUMIMAGES));
		final String[] names = manifest.getImageProperty(ImageManifest.FILENAMELIST).split(",");
		final String[] ids = manifest.getImageProperty(ImageManifest.IDLIST).split(",");
		if (names.length != ids.length) {
			Write.info("Problem in " + new File(loadDir, "sprites.manifest").getAbsolutePath() + ": differing number of names and ID's. Some may be ignored.");
		}
		for (int i = 0; i < spriteCount; i++) {
			idToFilenameMap.put(ids[i].trim(), names[i].trim());
		}
	}

	@Override
	public Sprite load(String id) {
		try {
			Sprite sprite = new Sprite(ImageIO.read(new File(loadDir, idToFilenameMap.get(id))));
			return sprite;
		} catch (IOException e) {
			Write.error("Failed to load sprite with ID " +id +" from " + idToFilenameMap.get(id));
			return null;
		}
	}

	public int getNumSprites() {
		return Integer.parseInt(manifest.getImageProperty(ImageManifest.NUMIMAGES));
	}

	public String getFilenameList() {
		return manifest.getImageProperty(ImageManifest.FILENAMELIST);
	}

}
