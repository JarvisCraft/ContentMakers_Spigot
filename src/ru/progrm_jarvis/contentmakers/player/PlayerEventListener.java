package ru.progrm_jarvis.contentmakers.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;

/**
 * Created by PROgrm_JARvis on 28.04.2017.
 */
public class PlayerEventListener implements Listener {
    ContentMakersPlugin plugin;
    public PlayerEventListener (ContentMakersPlugin plugin) {
        this.plugin = plugin;
    }/*
    @EventHandler
    onPlayerJoinServer (ServerEvent event) {
        event.
    }
    */
    @EventHandler
    public void onTabComplete (TabCompleteEvent event) {
        System.out.println(event.getBuffer());
    }
}
