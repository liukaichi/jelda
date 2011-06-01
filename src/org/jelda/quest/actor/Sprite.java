package org.jelda.quest.actor;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {
	protected BufferedImage sprite = null;

	public Sprite(Sprite other) {
		this(other, false);
	}
	
	public Sprite(Sprite other, boolean deepCopy) {
		if (!deepCopy) {
			sprite = other.sprite;
		}
		else {
			copySpriteFrom(other);
		}
	}

	public Sprite(String path) {
		loadSprite(path);
	}

	public Sprite(String path, Rectangle spriteLocation) {
		this(path);
		sprite = sprite.getSubimage(spriteLocation.x, spriteLocation.y,
				spriteLocation.width, spriteLocation.height);
	}

	public Sprite(String path, int x, int y, int width, int height) {
		this(path);
		sprite = sprite.getSubimage(x, y, width, height);
	}

	public Sprite(Sprite other, int x, int y, int width, int height) {
		this(other, false);
		sprite = sprite.getSubimage(x, y, width, height);
	}

	public Sprite(Sprite other, int x, int y, int width, int height, boolean deepCopy) {
		this(other, deepCopy);
		sprite = sprite.getSubimage(x, y, width, height);
	}
	public void loadSprite(String path) {
		File filePath = new File(path);
		if (!filePath.exists() || filePath.isDirectory()) {
			throw new IllegalArgumentException(path
					+ " is not a valid image file.");
		} else {
			try {
				sprite = ImageIO.read(filePath);
			} catch (IOException e) {
				throw new RuntimeException("Unsupported image format for file "
						+ path);
			}
		}
	}

	public void useSpriteFrom(Sprite other) {
		sprite = other.sprite;
	}

	public void copySpriteFrom(Sprite other) {
		ColorModel cm = other.sprite.getColorModel();
		sprite = new BufferedImage(cm, other.sprite.copyData(null), cm.isAlphaPremultiplied(), null);
	}

	public BufferedImage getNewSubImage(int x, int y, int width, int height) {
		ColorModel cm = sprite.getColorModel();
		return new BufferedImage(cm, sprite.copyData(null), cm.isAlphaPremultiplied(), null).getSubimage(x, y, width, height);
	}
	
	public BufferedImage getSubImage(int x, int y, int width, int height) {
		return sprite.getSubimage(x, y, width, height);
	}

	public int getWidth() {
		return sprite.getWidth();
	}

	public int getHeight() {
		return sprite.getHeight();
	}
}
