package dev.micah.skyclient.settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class Settings {
	
	private static Gson gson = new Gson();
	
	private static File root = new File("skyclient");
	private static File settings = new File(root, "settings");
	
	private static File getFolder(SettingsConf conf) {
		File folder = new File(Settings.getSettingsDir(), conf.getDisplayString());
		folder.mkdirs();
		return folder;
	}

	public static void init() {
		if (!root.exists()) {
			root.mkdirs();
		}
		if (settings.exists()) {
			settings.mkdirs();
		}
	}

	public static Gson getGson() {
		return gson;
	}
	
	public static File getSettingsDir() {
		return settings;
	}
	
	public static boolean writeJsonToFile(File file, Object obj) {
		try {
		if (!file.exists()) {
			file.createNewFile();
		}
			FileOutputStream out = new FileOutputStream(file);
			out.write(gson.toJson(obj).getBytes());
			out.flush();
			out.close();		
			return true;
		} catch (IOException x) {
			x.printStackTrace();
		}
		return false;
	}
	
	public static <T extends Object> T readFromJson(File file, Class<T> c) {
		try {
			FileInputStream in = new FileInputStream(file);
			InputStreamReader inRead = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(inRead);
			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			reader.close();
			inRead.close();
			in.close();
			
			return gson.fromJson(builder.toString(), c);
		} catch (IOException x) {
			x.printStackTrace();
			return null;
		}
	}
	
}
