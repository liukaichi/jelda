package org.jelda.quest.sprite;

import java.awt.image.BufferedImage;

public class Sprite extends BufferedImage {

	public Sprite(BufferedImage image) {
		super(image.getColorModel(), image.copyData(null), image.isAlphaPremultiplied(), null);
	}

}
