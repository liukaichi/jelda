package org.jelda.quest.manifest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.jelda.player.Write;

public class Manifest {
	Properties manifest;
	public final static String QUESTNAME = "quest.name";
	public final static String QUESTVERSION = "quest.version";
	public final static String QUESTAUTHOR = "quest.author";
	
	public Manifest(File f) {
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
	
	public String getQuestName() {
		return manifest.getProperty(QUESTNAME);
	}
	
	public String getQuestVersion() {
		return manifest.getProperty(QUESTVERSION);
	}
	
	public String getQuestAuthor() {
		return manifest.getProperty(QUESTAUTHOR);
	}
}
