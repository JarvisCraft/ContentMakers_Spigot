package ru.progrm_jarvis.contentmakers.streams;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;
import ru.progrm_jarvis.contentmakers.console.Tracer;

/**
 * Created by PROgrm_JARvis on 26.03.2017.
 */
public class Stream {
    private String player;
    private String platform;
    private String id;
    private String name;
    private BukkitTask timeoutUpdatePacketTask;
    public Stream (String player, String platform, String id, String name) {
        this.player = player;
        this.platform = platform;
        this.id = id;
        this.name = name;

        scheduleUpdatePacketTask();
    }

    //Packet-Tasking
    public BukkitTask getTimeoutUpdatePacketTask() {
        return timeoutUpdatePacketTask;
    }
    private void scheduleUpdatePacketTask () {
        long timeout = ContentMakersPlugin.getInstance().getStreamManager().getUpdatePacketTimeout();
        if (timeout > 0) {
            timeoutUpdatePacketTask = Bukkit.getScheduler().runTaskLater(ContentMakersPlugin.getInstance(), () -> {
                Tracer.msg("Stopping stream");
                //scheduleUpdatePacketTask();
                ContentMakersPlugin.getInstance().getStreamManager().stopStream(this, false);
            }, timeout * 20);
        }
    }
    public void cancelTimeoutUpdatePacketTask () {
        timeoutUpdatePacketTask.cancel();
    }
    public void resetTimeoutUpdatePacketTask () {
        timeoutUpdatePacketTask.cancel();
        scheduleUpdatePacketTask();
    }

    public String getPlayer() {
        return player;
    }
    public String getPlatform() {
        return platform;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
