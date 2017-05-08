package ru.progrm_jarvis.contentmakers.placeholder;

import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;
import ru.progrm_jarvis.contentmakers.streams.StreamManager;

/**
 * Created by PROgrm_JARvis on 05.04.2017.
 */
public class ContentMakersPlaceholders extends EZPlaceholderHook {
    private ContentMakersPlugin plugin;
    public ContentMakersPlaceholders (ContentMakersPlugin plugin) {
        super(plugin, "contentmakers");
        this.plugin = plugin;
    }
    @Override
    public String onPlaceholderRequest(Player player, String s) {
        if (player != null) {
            //Player's placeholders
            switch (s) {
                case ("streams_num"):
                    return String.valueOf(ContentMakersPlugin.getInstance().getStreamManager().getStreamsNum());
            }
        } else {
            //Global placeholders
            switch (s) {
                case ("streams_num"):
                    return String.valueOf(ContentMakersPlugin.getInstance().getStreamManager().getStreamsNum());
            }
        }
        return null;
    }
}
