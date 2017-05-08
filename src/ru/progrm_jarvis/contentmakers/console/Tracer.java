package ru.progrm_jarvis.contentmakers.console;

import org.bukkit.ChatColor;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;

/**
 * Created by PROgrm_JARvis on 14.04.2017.
 */
public final class Tracer {
    private final static String prefix = "[ContentMakers] ";
    private static boolean doSendPacketData = false;
    public static boolean doSendPacketData () {
        return doSendPacketData;
    }
    public static void doSendPacketData (boolean doSend) {
        doSendPacketData = doSend;
        msg("Set value for doSendPacketData to: " + doSend);
    }
    //Tracing
    public static void msg (String message) {
        ContentMakersPlugin.getInstance().getLogger().info(prefix + message);
    }
    public static void warn (String message) {
        ContentMakersPlugin.getInstance().getLogger().warning(prefix + message);
    }
    public static void err (String message) {
        ContentMakersPlugin.getInstance().getLogger().warning(prefix + ChatColor.DARK_RED + message);
    }
    public static void packet (String message) {
        if (doSendPacketData) {
            ContentMakersPlugin.getInstance().getLogger().warning(prefix + "Received Packet: " + ChatColor.YELLOW + message);
        }
    }

}
