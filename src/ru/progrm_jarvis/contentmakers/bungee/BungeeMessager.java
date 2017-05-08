package ru.progrm_jarvis.contentmakers.bungee;

import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Final Class used for sending data to Bungeecord-Proxy
 */
public final class BungeeMessager {
    private BungeeMessager() {}
    public static void sendToBungeecord (Player player, String subchannel, String type, String[] packet) {
        System.out.println("Sending");
        if (packet == null) {
            packet = new String[0];
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF(subchannel);
            out.writeUTF(type);
            out.writeUTF(Integer.toString(packet.length));
            for (String field : packet) {
                out.writeUTF(field);
                System.out.println(field);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (player != null) {
            System.out.println("Player's not null");
            player.sendPluginMessage(ContentMakersPlugin.getInstance(), "BungeeCord", stream.toByteArray());
        } else {
            System.out.println("Player's null");
            ContentMakersPlugin.getInstance().getServer().sendPluginMessage(ContentMakersPlugin.getInstance(), "BungeeCord", stream.toByteArray());
            /*ContentMakersPlugin.getInstance().getServer().sendPluginMessage(ContentMakersPlugin.getPlugin(ContentMakersPlugin.class),
                    "BungeeCord", stream.toByteArray());*/
        }
    }
    public static void sendToBungeecord (Player player, String type, String[] packet) {
        sendToBungeecord(player, "ru.progrm_jarvis.contentmakers", type, packet);
    }
}
