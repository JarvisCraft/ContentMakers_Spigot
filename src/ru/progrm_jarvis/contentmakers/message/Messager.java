package ru.progrm_jarvis.contentmakers.message;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;
import ru.progrm_jarvis.contentmakers.console.Tracer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PROgrm_JARvis on 11.04.2017.
 */
public final class Messager {
    //Private full
    private static void sendMessageToPlayer (Player player, String message) {
        if (ContentMakersPlugin.getInstance().usePapi()) {
            message = PlaceholderAPI.setPlaceholders(player, message);
        }
        message = ChatColor.translateAlternateColorCodes('&', message);
        player.sendMessage(message);
    }
    private static void sendMessageToPlayer (Player player, List<String> messages) {
        for (String message : messages) {
            sendMessageToPlayer(player, message);
        }
    }
    private static void broadcastMessage (String message) {
        if (ContentMakersPlugin.getInstance().usePapi()) {
            message = PlaceholderAPI.setPlaceholders(null, message);
        }
        message = ChatColor.translateAlternateColorCodes('&', message);
        ContentMakersPlugin.getInstance().getServer().broadcastMessage(message);
    }
    private static void broadcastMessage (List<String> messages) {
        for (String message : messages) {
            broadcastMessage(message);
        }
    }

    //Public-shortened
    public static void sendMessage (Player player, String message) {
        sendMessageToPlayer(player, message);
    }
    public static void broadcast (String message) {
        broadcastMessage(message);
    }
    //from-Config
    public static void sendFromCfg (Player player, String path) {
        List<String>message = getMessageFromConfig(path);
        if (message != null) {
            sendMessageToPlayer(player, message);
        } else {
            Tracer.err("Unable to load message from languages.yml! Blank path: " + path + ".");
        }
    }
    public static void broadcastFromCfg (String path) {
        List<String>message = getMessageFromConfig(path);
        if (message != null) {
            broadcastMessage(message);
        } else {
            Tracer.err("Unable to load message from languages.yml! Blank path: " + path + ".");
        }
    }
    public static List<String> getMessageFromConfig (String path) {
        List<String> message = ContentMakersPlugin.getInstance().getConfigManager().cfg("languages.yml").getConfigurationSection("messages").getStringList(path);
        if (message != null) {
        } else {
            message = new ArrayList<>();
        }
        return message;
    }
}
