package org.jelda.player;


public class Write {
	private static boolean showInfo = false;
	private static boolean showError = true;
	static {
		Config config = Config.getDefaultConfig();
		showInfo = config.isPrintInfoMessages();
		showError = config.isPrintErrorMessages();
	}
	public static void info(String message) {
		if (showInfo) {
			System.out.println(message);
		}
	}
	public static void error(String message) {
		if (showError) {
			System.err.println(message);
		}
	}
}
