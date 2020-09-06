package com.github.sonic14.simpleping.plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

public class SimplePingPlugin extends JavaPlugin {

    @Override
    public void onEnable() { getLogger().info("SimplePing plugin loaded"); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(ChatColor.YELLOW + "Current Ping: " + getPing(p) + " ms");
            } else {
                p.sendMessage(ChatColor.RED + "Usage: /ping");
            }
        } else {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "This command is not available on the console!");
        }
        return false;
    }

    public static int getPing(Player p) {
        try {
            Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
            return (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
