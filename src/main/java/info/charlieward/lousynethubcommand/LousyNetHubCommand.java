package info.charlieward.lousynethubcommand;

import info.charlieward.lousynethubcommand.commands.hubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public final class LousyNetHubCommand extends JavaPlugin {

    private static LousyNetHubCommand plugin;
    public Jedis jedis = new Jedis();

    @Override
    public void onEnable() {

        plugin = this;
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        getLogger().info("LousyNet-HubCommand v." + this.getDescription().getVersion() + " has loaded.");

        getCommand("hub").setExecutor(new hubCommand(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("LousyNet-HubCommands v." + this.getDescription().getVersion() + " has been disabled.");
    }

    public static LousyNetHubCommand getPlugin() {
        return plugin;
    }

    public static void sendPlayerToServer(Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(LousyNetHubCommand.getPlugin(), "BungeeCord", b.toByteArray());
            b.close();
            out.close();
        }
        catch (Exception e) {
            player.sendMessage(ChatColor.RED+"Error when trying to connect to "+server);
            System.out.println(e);
        }
    }
}
