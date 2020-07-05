package me.urlordjames.gnuhc;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public final class Gnuhc extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("it do be enabled tho");
        Bukkit.getServer().getPluginManager().registerEvents(new UhcListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            Util.notifysender(sender, "sender is not op");
            return false;
        }
        if (command.getName().equals("uhcstart")) {

            boolean status = UhcWorld.newworld();
            if (status) {
                Util.notifysender(sender, "success");
                World world = Bukkit.getWorld("world");
                World uhcworld = Bukkit.getWorld("uhc");
                for (Player player : world.getPlayers()) {
                    Random rand = new Random();
                    int x = rand.nextInt(1000) - 500;
                    int z = rand.nextInt(1000) - 500;
                    player.teleport(new Location(uhcworld, x, uhcworld.getHighestBlockYAt(x, z), z));
                    player.getInventory().clear();
                    player.setHealth(20);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100, 100));
                }
                WorldBorder worldBorder = uhcworld.getWorldBorder();
                worldBorder.setCenter(0, 0);
                worldBorder.setSize(2000);
                worldBorder.setSize(10, 60 * 10);
            } else {
                Util.notifysender(sender, "failed");
            }
            getServer().dispatchCommand(getServer().getConsoleSender(), "save-all");
            return status;
        }
        return false;
    }

    @Override
    public void onDisable() {
        System.out.println("I do be gone tho");
    }
}
