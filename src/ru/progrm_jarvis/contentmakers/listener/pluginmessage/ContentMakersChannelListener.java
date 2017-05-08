package ru.progrm_jarvis.contentmakers.listener.pluginmessage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;
import ru.progrm_jarvis.contentmakers.console.Tracer;
import ru.progrm_jarvis.contentmakers.message.Messager;
import ru.progrm_jarvis.contentmakers.streams.StreamManager;

import java.io.*;
import java.util.Arrays;

/**
 * The Class used for From-Bungee-Messaging
 */
public class ContentMakersChannelListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived (String channel, Player player, byte[] message) {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
        try {
            String subchannel = in.readUTF();
            if (subchannel.equals("ru.progrm_jarvis.contentmakers")) {
                String type = in.readUTF();
                Integer length = Integer.parseInt(in.readUTF());
                String[] packet = new String[length];
                for (int i = 0; i < length; i++) {
                    packet[i] = in.readUTF();
                }
                ContentMakersPacketHandler.handle(channel, subchannel, length, type, packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
