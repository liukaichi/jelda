package org.jelda.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;

import org.jelda.quest.Quest;
import org.jelda.quest.QuestReader;

import orj.jelda.abstracts.Drawable;

public class QuestRunner {
	private Quest quest;

	private Drawable drawPane;

	private boolean isRunning;

	private boolean isPaused;

	private int fps = 60;

	private final int width, height;

	private BufferedImage buffer1, buffer2, currentBuffer;

	public QuestRunner(File file, Drawable drawPane) {
		quest = QuestReader.loadQuest(file);
		this.drawPane = drawPane;
		isRunning = false;
		isPaused = false;
		width = quest.getQuestWidth();
		height = quest.getQuestHeight();
		buffer1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		buffer2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		currentBuffer = buffer1;
		buffer1.getGraphics().drawOval(50, 50, 50, 50);
		buffer2.getGraphics().fillRect(100, 50, 50, 50);
	}

	public void start() {
		if (!isRunning) {
			isRunning = true;
			runGameLoop();
		}
	}

	public void pause() {
		if (isRunning && !isPaused) {
			isPaused = true;
		}
	}

	public void unpause() {
		if (isRunning && isPaused) {
			isPaused = false;
		}
	}

	public void stop() {
		if (isRunning) {
			isRunning = false;
		}
	}

	public boolean isPaused() {
		return isPaused;
	}

	public boolean isRunning() {
		return isRunning;
	}

	// Starts a new thread and runs the game loop in it.
	private void runGameLoop() {
		Thread loop = new Thread() {
			public void run() {
				gameLoop();
			}
		};
		loop.start();
	}

	public void gameLoop() {
		long lastLoopTime = System.nanoTime();
		final long OPTIMAL_TIME = 1000000000 / 60; // 60fps is optimal
		long lastFpsTime = 0;

		// keep looping round til the game ends
		while (isRunning) {
			if (!isPaused) {
				// work out how long its been since the last update, this
				// will be used to calculate how far the entities should
				// move this loop
				long now = System.nanoTime();
				long updateLength = now - lastLoopTime;
				lastLoopTime = now;
				double delta = updateLength / ((double) OPTIMAL_TIME);

				// update the frame counter
				lastFpsTime += updateLength;
				fps++;

				// update our FPS counter if a second has passed since
				// we last recorded
				if (lastFpsTime >= 1000000000) {
					Write.info("(FPS: " + fps + ")");
					lastFpsTime = 0;
					fps = 0;
				}

				// update the game logic
				doGameUpdates(delta);

				// draw everyting
				render();

				// we want each frame to take 10 milliseconds, to do this
				// we've recorded when we started the frame. We add 10
				// milliseconds
				// to this and then factor in the current time to give
				// us our final value to wait for
				// remember this is in ms, whereas our lastLoopTime etc. vars
				// are in
				// ns.
				try {
					Thread.sleep((lastLoopTime - System.nanoTime()) / 1000000 + 10);
				} catch (Exception e) {
				}
			} else {
				try {
					Thread.sleep(200);
				} catch (Exception e) {
				}
			}
		}
	}

	private void render() {
		// TODO Draw onto currentBuffer image, then
		drawPane.setNextFrame(currentBuffer);
		currentBuffer = currentBuffer == buffer1 ? buffer2 : buffer1;
	}

	private void doGameUpdates(double delta) {
		// TODO
		/*
		 * for (int i = 0; i < stuff.size(); i++) { // all time-related values
		 * must be multiplied by delta! Stuff s = stuff.get(i); s.velocity +=
		 * Gravity.VELOCITY * delta; s.position += s.velocity * delta;
		 * 
		 * // stuff that isn't time-related doesn't care about delta... if
		 * (s.velocity >= 1000) { s.color = Color.RED; } else { s.color =
		 * Color.BLUE; } }
		 */
	}

}
