package ru.progrm_jarvis.contentmakers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import ru.progrm_jarvis.contentmakers.bungee.BungeeMessager;
import ru.progrm_jarvis.contentmakers.command.bungee.BungeeCordCommandStream;
import ru.progrm_jarvis.contentmakers.config.ConfigManager;
import ru.progrm_jarvis.contentmakers.console.Tracer;
import ru.progrm_jarvis.contentmakers.listener.pluginmessage.ContentMakersChannelListener;
import ru.progrm_jarvis.contentmakers.placeholder.ContentMakersPlaceholders;
import ru.progrm_jarvis.contentmakers.player.PlayerEventListener;
import ru.progrm_jarvis.contentmakers.streams.StreamManager;
import ru.progrm_jarvis.contentmakers.url.URLReader;

import java.util.logging.Logger;

/**
 * YouTubers Main Class
 */
public class ContentMakersPlugin extends JavaPlugin {
    private static ContentMakersPlugin contentMakersPlugin;
    public static ContentMakersPlugin getInstance() {
        return contentMakersPlugin;
    }
    @Override
    public void onEnable() {
        contentMakersPlugin = this;
        try {
            URLReader.read("https://www.youtube.com/live_stats?v=Zwam4jrUpz0");
        } catch (Exception e) {
            e.printStackTrace();
        }

        enableMessagingChannels();
        registerBungeeCommands();
        enableManagers();

        hookIntoSoftDependencies();

        loadSettings();

        PluginDescriptionFile pluginDescriptionFile = getDescription();
        Logger logger = getLogger();
        logger.info(pluginDescriptionFile.getName() + " has been enabled. Version: " + pluginDescriptionFile.getVersion());

        //Tracer.msg(ChatColor.GREEN + "Loading all available Content-Makers data from BungeeCord.");
        //requestBungeecordForData();

    }
    @Override
    public void onDisable() {
        //TODO
    }

    /*
    -=-=-=-=-=-=-=-
    M A N A G E R S
    -=-=-=-=-=-=-=-
     */

    private ConfigManager configManager;
    private StreamManager streamManager;
    private void enableManagers() {
        configManager = new ConfigManager(this);
        streamManager = new StreamManager(this);

        configManager.generateDefaultFiles();
        configManager.loadConfigs();

        streamManager.loadConfigurableDataFromConfig();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
    public StreamManager getStreamManager() {
        return streamManager;
    }

    private void enableMessagingChannels () {
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "Return", new ContentMakersChannelListener());
    }
    private void registerBungeeCommands () {
        getCommand("stream").setExecutor(new BungeeCordCommandStream());
    }

    /*
    -=-=-=-=-=-=-=-=-=-=-=-
    D E P E N D E N C I E S
    -=-=-=-=-=-=-=-=-=-=-=-
     */

    /*private void hookIntoDependencies () {
       TODO if needed
    }*/
    private void hookIntoSoftDependencies () {
        hookIntoPlaceholderAPI();
    }
    //PlaceholderAPI
    private boolean usePapi = false;
    private void hookIntoPlaceholderAPI () {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new ContentMakersPlaceholders(this).hook();
            usePapi = true;
        } else {
            usePapi = false;
        }
    }
    public boolean usePapi () {
        return usePapi;
    }


    /*
    -=-=-=-=-=-=-=-
    S E T T I N G S
    -=-=-=-=-=-=-=-
     */
    public void loadSettings () {
        //DoSendPacketData
        Tracer.msg("Loading Settings");
        Tracer.doSendPacketData(configManager.cfg("config.yml").getBoolean("console.send-packet-data"));
    }

    /*
    -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    A F T E R   L O A D   D A T A   G E T T I N G
    -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
     */
    private boolean getBungeeData = true;
    public boolean doGetBungeeData() {
        return getBungeeData;
    }
    public void setGetBungeeData (boolean getBungeeData) {
        this.getBungeeData = getBungeeData;
    }
}
