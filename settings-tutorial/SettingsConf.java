package dev.micah.skyclient.settings;

import java.io.File;

public enum SettingsConf {
	
	XYZ(true, "Coordinates"),
        FPS(true, "Frames Per Second");
	
	private boolean value;
	private String disString;
	
	SettingsConf(boolean value, String displayName) {
		this.value = value;
		this.disString = displayName;
	}
	
	public boolean getDefaultValue() {
		return value;
	}
	
	public String getDisplayString() {
		return disString;
	}
	
	private static File getConfigFolder(SettingsConf conf) {
		File folder = new File(Settings.getSettingsDir(), conf.getDisplayString());
		folder.mkdirs();
		return folder;
	}
	
	public static void saveConfSettingsValue(SettingsConf conf, boolean value) {
		Settings.writeJsonToFile(new File(getFolder(conf), conf.getDisplayString() + ".json"), value);
	}
	
	public static boolean getValueFromSavedConf(SettingsConf conf) {
		boolean loaded = Settings.readFromJson(new File(getFolder(conf), conf.getDisplayString() + ".json"), boolean.class);
		if (loaded == true) {
			return true;
		} else {
			return false;
		}
	}
	
}
