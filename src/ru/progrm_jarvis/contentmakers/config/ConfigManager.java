package ru.progrm_jarvis.contentmakers.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PROgrm_JARvis on 27.03.2017.
 */
public class ConfigManager {
    private static ConfigManager streamManager = new ConfigManager();
    private static ContentMakersPlugin plugin;
    public ConfigManager (ContentMakersPlugin plugin) {
        ConfigManager.plugin = plugin;
    }
    private ConfigManager () {}

    //Основная часть
    private String[] configNames = {"config.yml", "platforms.yml", "languages.yml"};
    private String[] fileNames = {};
    private Map<String, FileConfiguration> configs = new HashMap<>();
    private File generateDefaultFile(String name) {
        File file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            System.out.println(file.getParentFile().mkdirs() ? "Default file " + name + " directory generated." : "ERROR generating default file " + name + " directory (maybe it already exists(?).");
            plugin.saveResource(name, false);
        }
        return file;
    }
    public void generateDefaultFiles () {
        for (String fileName : fileNames) {
            generateDefaultFile(fileName);
        }
    }
    public void loadConfigs () {
        for (String configName  : configNames) {
            if (!configs.containsKey(configName)) {
                System.out.println(configName);
                File configFile = generateDefaultFile(configName);
                FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
                configs.put(configName, config);
                plugin.getLogger().info(config.getString("i.am"));
            }
        }
    }
    private FileConfiguration getConfigByName (String configName) {
        if (configs.keySet().contains(configName)) {
            return configs.get(configName);
        } else {
            return null;
        }
    }
    public FileConfiguration cfg (String configName) {
        return getConfigByName(configName);
    }
}
