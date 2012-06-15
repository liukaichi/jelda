package org.jelda.player;

import java.awt.Graphics;
import java.io.File;

import org.jelda.quest.Quest;
import org.jelda.quest.QuestReader;


public class QuestRunner {
	private Quest quest;
	private Graphics graphics;
	public QuestRunner(File file, Graphics graphics) {
		quest = QuestReader.loadQuest(file);
		this.graphics = graphics;
	}
}
