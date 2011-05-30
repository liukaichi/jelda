/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jelda.player;

import java.awt.Graphics;
import java.io.File;
import org.jelda.quest.Quest;

/**
 *
 * @author Blaise
 */
public class QuestRunner {
    private boolean isRunning = false;
    public Quest quest;
    public Graphics graphics;

    public QuestRunner(File file, Graphics graphics) {
	this(loadQuestFromFile(file), graphics);
    }
    public QuestRunner(Quest quest, Graphics graphics) {
	this.quest = quest;
	this.graphics = graphics;
	load();
    }

    public void start() {
	//TODO
	isRunning = true;
    }

    private void load() {
	//TODO
    }

    public void stop() {
	//TODO
	isRunning = false;
    }

    public boolean isRunning() {
	return isRunning;
    }

    public static Quest loadQuestFromFile(File file) {
	//TODO
	return null;
    }

}
