package me.urlordjames.gnuhc;

import org.bukkit.command.CommandSender;

public class Util {
    public static void notifysender(CommandSender sender, String message){
        System.out.println(message);
        sender.sendMessage(message);
        return;
    }
}
