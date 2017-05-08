package ru.progrm_jarvis.contentmakers.streams;

import org.bukkit.entity.Player;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;
import ru.progrm_jarvis.contentmakers.console.Tracer;
import ru.progrm_jarvis.contentmakers.message.Messager;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Manager for active Streams
 */
public class StreamManager {

    private static StreamManager streamManager = new StreamManager();
    private static ContentMakersPlugin plugin;
    public StreamManager (ContentMakersPlugin plugin) {
        StreamManager.plugin = plugin;
    }
    private StreamManager () {}
    public static StreamManager get () {
        return streamManager;
    }
    private Map<String, Stream> streams = new HashMap<>();
    public Map<String, Stream> getStreams () {
        return streams;
    }
    public int getStreamsNum () {
        return streams.size();
    }

    //Configurable Data

    private long updatePacketTimeout;
    public void loadConfigurableDataFromConfig () {
        updatePacketTimeout = ContentMakersPlugin.getInstance().getConfigManager().cfg("config.yml").getLong("stream.update-packet-timeout");
    }
    public long getUpdatePacketTimeout() {
        return updatePacketTimeout;
    }


    public void addStream (Stream stream) {
        Tracer.msg(stream.getPlayer() + " Has a stream");
        if (!streams.containsValue(stream)) {
            Tracer.msg(stream.getPlayer() + " added a stream");
            streams.put(stream.getPlayer().toLowerCase(), stream);
            Tracer.msg(streams.size() + "");
        }
    }
    public boolean removeStream (Stream stream) {
        if (streams.containsValue(stream)) {
            streams.remove(stream);
            Tracer.msg("TIS " + stream.getName());
            return true;
        }
        return false;
    }
    public void stopStream (String playerName, boolean silent) {
        if (removeStream(getStreamByPlayer(playerName))) {
            if (!silent) {
                Messager.broadcastFromCfg("StreamStopped");
            }
        }
    }
    public void stopStream (Stream stream, boolean silent) {
        if (removeStream(stream)) {
            if (!silent) {
                Messager.broadcastFromCfg("StreamStopped");
            }
        }
    }

    //Добавление стрима из обратного запроса к Спиготу
    public void addStreamFromPacket (String[] packet) {
        Stream stream = new Stream(packet[0], packet[1], packet[2], packet[3]);
        addStream(stream);
        for (Player p : ContentMakersPlugin.getInstance().getServer().getOnlinePlayers()) {
            Messager.sendMessage(p, "Теперь стримов: %contentmakers_streams_num%");
        }
    }

    public void stopStreamBungee () {
        
    }

    @Nullable
    public Stream getStreamByPlayer (String player) {
        return streams.getOrDefault(player.toLowerCase(), null);
    }
    public void resetStreamTimeoutFromBungee (String[] packet) {
        Stream stream = getStreamByPlayer(packet[0]);
        if (stream != null) {
            stream.resetTimeoutUpdatePacketTask();
        }
    }
}
