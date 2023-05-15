package com.nhoryzon.mc.wanderweft;

import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Configuration {

    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "wanderweft.json");

    /* Dummy Base Category */
    private boolean dummyBoolSetting = true;
	
	/* Dummy Parent Category */
	private int dummyIntegerSetting = 100;
    private List<String> dummyListSetting = Arrays.asList("default", "list", "values");
	private double dummyDoubleSetting = 0.1;

    public static Configuration load() {
        Configuration configuration = new Configuration();
        if (!CONFIG_FILE.exists()) {
            save(configuration);
        }

        Reader reader;
        try {
            reader = Files.newBufferedReader(CONFIG_FILE.toPath());
            configuration = (new GsonBuilder().setPrettyPrinting().create()).fromJson(reader, Configuration.class);
            reader.close();
        } catch (IOException e) {
            WanderweftMod.LOGGER.error("Error while trying to load configuration file. Default configuration used.", e);
        }

        return configuration;
    }

    public static void save(Configuration config) {
        try {
            Writer writer = Files.newBufferedWriter(CONFIG_FILE.toPath());
            (new GsonBuilder().setPrettyPrinting().create()).toJson(config, writer);

            writer.close();
        } catch (IOException e) {
            WanderweftMod.LOGGER.error("Error while trying to save configuration file.", e);
        }
    }

    public boolean isDummyBoolSetting() {
        return dummyBoolSetting;
    }

    public void setDummyBoolSetting(boolean pDummyBoolSetting) {
        dummyBoolSetting = pDummyBoolSetting;
    }

    public int getDummyIntegerSetting() {
        return dummyIntegerSetting;
    }

    public void setDummyIntegerSetting(int pDummyIntegerSetting) {
        dummyIntegerSetting = limit(0, Integer.MAX_VALUE, pDummyIntegerSetting);
    }

    public List<String> getDummyListSetting() {
        return dummyListSetting;
    }

    public void setDummyListSetting(List<String> pDummyListSetting) {
        dummyListSetting = pDummyListSetting == null ? new ArrayList<>() : pDummyListSetting;
    }

    public double getDummyDoubleSetting() {
        return dummyDoubleSetting;
    }

    public void setDummyDoubleSetting(double pDummyDoubleSetting) {
        dummyDoubleSetting = limit(0.0, 1.0, pDummyDoubleSetting);
    }

    private static double limit(double min, double max, double value) {
        if (value > max) {
            return max;
        }

        return Math.max(value, min);
    }

    private static int limit(int min, int max, int value) {
        if (value > max) {
            return max;
        }

        return Math.max(value, min);
    }

}
