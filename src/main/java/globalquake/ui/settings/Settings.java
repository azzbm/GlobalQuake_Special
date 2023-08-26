package globalquake.ui.settings;

import globalquake.main.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public final class Settings {

	private static final File optionsFile = new File(Main.MAIN_FOLDER, "globalQuake.properties");
	private static final Properties properties = new Properties();
	public static Boolean enableAlarmDialogs;
	
	public static Double homeLat;
	public static Double homeLon;

	public static final double pWaveInaccuracyThresholdDefault = 1000;
	public static Double pWaveInaccuracyThreshold;
	public static final double hypocenterCorrectThresholdDefault = 40;
	public static Double hypocenterCorrectThreshold;

	public static final double hypocenterDetectionResolutionDefault = 40;
	public static Double hypocenterDetectionResolution;

	public static Boolean displayArchivedQuakes;
	
	public static final boolean reportsEnabled = false; // not available ATM
	public static Boolean enableSound = true;

	static {
		load();
	}

	private static void load() {
		try {
			properties.load(new FileInputStream(optionsFile));
		} catch (IOException e) {
			System.out.println("Created GlobalQuake properties file at "+optionsFile.getAbsolutePath());
		}
		
		enableAlarmDialogs = Boolean.valueOf((String) properties.getOrDefault("enableAlarmDialogs", "false"));
		
		homeLat = Double.valueOf((String) properties.getOrDefault("homeLat", "0.0"));
		homeLon = Double.valueOf((String) properties.getOrDefault("homeLon", "0.0"));
		displayArchivedQuakes = Boolean.valueOf((String) properties.getOrDefault("displayArchivedQuakes", "true"));
		enableSound = Boolean.valueOf((String) properties.getOrDefault("enableSound", "true"));

		pWaveInaccuracyThreshold = Double.valueOf((String) properties.getOrDefault("pWaveInaccuracyThreshold", String.valueOf(pWaveInaccuracyThresholdDefault)));
		hypocenterCorrectThreshold = Double.valueOf((String) properties.getOrDefault("hypocenterCorrectThreshold", String.valueOf(hypocenterCorrectThresholdDefault)));
		hypocenterDetectionResolution = Double.valueOf((String) properties.getOrDefault("hypocenterDetectionResolution", String.valueOf(hypocenterDetectionResolutionDefault)));
		save();
	}
	
	
	public static void save() {
		properties.setProperty("enableAlarmDialogs", String.valueOf(enableAlarmDialogs));
		
		properties.setProperty("homeLat", String.valueOf(homeLat));
		properties.setProperty("homeLon", String.valueOf(homeLon));
		properties.setProperty("displayArchivedQuakes", String.valueOf(displayArchivedQuakes));
		properties.setProperty("enableSound", String.valueOf(enableSound));

		properties.setProperty("pWaveInaccuracyThreshold", String.valueOf(pWaveInaccuracyThreshold));
		properties.setProperty("hypocenterCorrectThreshold", String.valueOf(hypocenterCorrectThreshold));
		properties.setProperty("hypocenterDetectionResolution", String.valueOf(hypocenterDetectionResolution));
		try {
			properties.store(new FileOutputStream(optionsFile), "Fun fact: I've never felt an earthquake in my life");
		} catch (IOException e) {
			Main.getErrorHandler().handleException(e);
		}
	}
}
