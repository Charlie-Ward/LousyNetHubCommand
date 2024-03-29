package info.charlieward.lousynethubcommand.commands;

import info.charlieward.lousynethubcommand.LousyNetHubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class hubCommand implements CommandExecutor {

    static LousyNetHubCommand plugin;

    public hubCommand(LousyNetHubCommand plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            String serverStatus = plugin.jedis.get("mainHub");
            if (serverStatus == null || serverStatus.equals("offline")) {
                player.sendMessage(ChatColor.BLUE + "[LousyNet] " + ChatColor.WHITE + "The hub server is not currently online");
            } else {
                player.sendMessage(ChatColor.BLUE + "[LousyNet] " + ChatColor.WHITE + "Sending you to hub");
                LousyNetHubCommand.sendPlayerToServer(player, "hub");
            }
        }
        return true;
    }
}
