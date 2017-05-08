package ru.progrm_jarvis.contentmakers.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import ru.progrm_jarvis.contentmakers.message.Messager;

/**
 * Created by PROgrm_JARvis on 19.04.2017.
 */
public class ContentMakersPlayerEventListener implements Listener {
    @EventHandler
    public void onPlayerChangeWorld (PlayerChangedWorldEvent event) {
        //TODO if (event.getPlayer().getWorld())
        Player player = event.getPlayer();
        if (player != null /*TODO || player is CMer*/) {
            for (Player player1 : event.getPlayer().getWorld().getPlayers()) {
                Messager.sendFromCfg(player1, "message.ContentMakerJoinedWorld");
            }
        }
    }
}
