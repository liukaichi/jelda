package org.jelda.player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private final static String PRINTINFOMESSAGESKEY = "print.info.messages";
	private final static String PRINTERRORMESSAGESKEY = "print.error.messages";
	private static Config defaultConfig = null;
	private Properties props = null;
	
	private Config (File f) {
		defaultConfig = this;
		Properties defaults = new Properties();
		try {
			FileInputStream in = new FileInputStream(new File("." +File.separator + "default.config.properties"));
			defaults.load(in);
			in.close();
		}
		catch (IOException e) {}
		props = new Properties(defaults);
		try {
			FileInputStream in = new FileInputStream(f);
			props.load(in);
			in.close();
		}
		catch (IOException e) {}
	}
	
	public static Config getDefaultConfig() {
		if (defaultConfig == null) {
			new Config(new File("." +File.separator + "config.properties"));
		}
		return defaultConfig;
	}
	
	public boolean isPrintInfoMessages() {
		return getPropertyAsBoolean(PRINTINFOMESSAGESKEY);
	}
	
	public boolean isPrintErrorMessages() {
		return getPropertyAsBoolean(PRINTERRORMESSAGESKEY);
	}
	
	private boolean getPropertyAsBoolean(String key) {
		return props.getProperty(key).equalsIgnoreCase("true");
	}
}
