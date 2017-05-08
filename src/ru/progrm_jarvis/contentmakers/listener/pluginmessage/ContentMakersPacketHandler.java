package ru.progrm_jarvis.contentmakers.listener.pluginmessage;

import org.bukkit.Bukkit;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;
import ru.progrm_jarvis.contentmakers.bungee.BungeeMessager;
import ru.progrm_jarvis.contentmakers.console.Tracer;
import ru.progrm_jarvis.contentmakers.message.Messager;

import java.util.Arrays;

/**
 * Created by PROgrm_JARvis on 15.04.2017.
 */
final class ContentMakersPacketHandler {
    static void handle (String channel, String subchannel, int length, String type, String[] packet) {
        Tracer.packet(channel + ":" + subchannel + ":" + length + ":" + type + ":" + Arrays.toString(packet));
        switch (type) {
            case ("doUpdateData"):
                if (ContentMakersPlugin.getInstance().doGetBungeeData()) {
                    BungeeMessager.sendToBungeecord(null, "loadBukkit", null);
                }
                break;
            case ("dataLoaded"):
                if (ContentMakersPlugin.getInstance().doGetBungeeData()) {
                    ContentMakersPlugin.getInstance().setGetBungeeData(false);
                }
                break;
            case ("addStream"):
                ContentMakersPlugin.getInstance().getStreamManager().addStreamFromPacket(packet);
                break;
            case ("message"):
                Messager.sendFromCfg(Bukkit.getPlayer(packet[0]), "message." + packet[1]);
                break;
            case ("broadcast"):
                Messager.broadcastFromCfg("broadcast." + packet[1]);
                break;
            case ("streamUpdate"):
                ContentMakersPlugin.getInstance().getStreamManager().resetStreamTimeoutFromBungee(packet);
                break;
            default:
                Tracer.err("Received wrong packet! Probably you're using different versions of ContentMakers for BungeeCord and Spigot.\n"+
                        "Packet info:\n" + channel + ":" + subchannel + ":" + type + ":" + length + ":" + Arrays.toString(packet));
                break;
        }
    }
}
