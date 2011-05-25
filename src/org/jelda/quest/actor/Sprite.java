package org.jelda.quest.actor;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import javax.imageio.ImageIO;

public class Sprite {
	public BufferedImage sprite = null;
	public Sprite() {
	}
	public Sprite(Sprite other) {
		sprite = other.sprite;
	}
	public Sprite(String path) {
		loadSprite(path);
	}
	public Sprite(String path, Rectangle spriteLocation) {
		this(path);
		sprite = sprite.getSubimage(spriteLocation.x, spriteLocation.y, spriteLocation.width, spriteLocation.height);
	}
	public Sprite(String path, int x, int y, int width, int height) {
		this(path);
		sprite = sprite.getSubimage(x, y, width, height);
	}
	public Sprite(Sprite other, int x, int y, int width, int height) {
		this(other);
		sprite = sprite.getSubimage(x, y, width, height);
	}
	public void loadSprite(String path) {
		File filePath = new File(path);
		if (!filePath.exists() || filePath.isDirectory()) {
			throw new InvalidParameterException(path + " is not a valid image file.");
		}
		else {
			try {
				sprite = ImageIO.read(filePath);
			} catch (IOException e) {
				throw new RuntimeException("Unsupported image format for file " + path);
			}
		}
	}
	public void loadSprite(Sprite other) {
		sprite = other.sprite;
	}
	public BufferedImage getSubImage(int x, int y, int width, int height) {
		return sprite.getSubimage(x, y, width, height);
	}
}
