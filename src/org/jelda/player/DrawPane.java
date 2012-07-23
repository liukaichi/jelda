package org.jelda.player;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import orj.jelda.abstracts.Drawable;

public class DrawPane extends JPanel implements Drawable {
	private static final long serialVersionUID = -1235641740620922025L;
	private volatile BufferedImage toDraw;
	private float scaleFactor = 1.0f;
	private int imageWidth = 640, imageHeight = 480;

	public DrawPane() {
		super();
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				float width = DrawPane.this.getWidth();
				float height = DrawPane.this.getHeight();
				float horizontalScaleFactor = width / imageWidth;
				float verticalScaleFactor = height / imageHeight;
				scaleFactor = horizontalScaleFactor < verticalScaleFactor ? horizontalScaleFactor
						: verticalScaleFactor;
			}

		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(toDraw, 0, 0, (int) (getWidth() * scaleFactor),
				(int) (getHeight() * scaleFactor), null);
	}

	@Override
	public void setNextFrame(BufferedImage image) {
		toDraw = image;
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		repaint();
	}

}
