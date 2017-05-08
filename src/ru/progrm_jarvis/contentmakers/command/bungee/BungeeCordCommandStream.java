package ru.progrm_jarvis.contentmakers.command.bungee;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.progrm_jarvis.contentmakers.bungee.BungeeMessager;
import ru.progrm_jarvis.contentmakers.message.Messager;

/**
 * Command: stream <platform> <id> <stream name>
 */
public class BungeeCordCommandStream implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            switch (args.length) {
                case 0:
                    Messager.sendFromCfg(player, "command.stream.Description");
                    break;
                case 1:
                    if (args[0].equalsIgnoreCase("stop")) {
                        String[] params = new String[2];
                        params[0] = "player";
                        params[1] = player.getName();
                        BungeeMessager.sendToBungeecord(player, "stopStream", params);
                    } else {
                        Messager.sendFromCfg(player, "command.stream.NotEnoughArguments.stream");
                    }
                    break;
                default:
                    if (args[0].equalsIgnoreCase("stop")) {
                        if (args[1].equalsIgnoreCase("all")) {
                            String[] params = new String[1];
                            params[0] = "all";
                            BungeeMessager.sendToBungeecord(player, "stopStream", params);
                        }
                    } else {
                        String platformId;
                        String streamId;
                        StringBuilder streamName = new StringBuilder();
                        //Установка значений
                        platformId = args[0];
                        streamId = args[1];
                        String[] params;
                        if (args.length >= 3) {
                            for (int i = 0; i < args.length-2; i++) {
                                streamName.append(args[i + 2]).append(" ");
                            }
                            streamName = new StringBuilder(streamName.substring(0, (streamName.length()-1)));
                            params = new String[4];
                            params[0] = player.getName();
                            params[1] = platformId;
                            params[2] = streamId;
                            params[3] = streamName.toString();
                        } else {
                            params = new String[3];
                            params[0] = player.getName();
                            params[1] = platformId;
                            params[2] = streamId;
                        }
                        player.sendMessage(ChatColor.DARK_AQUA + "Пытаюсь создать стрим: " + platformId + streamId + streamName + ".");
                        BungeeMessager.sendToBungeecord(player, "addStream", params);
                    }
                    break;
            }
            return true;
        }
        return false;
    }
}
