package me.JollyPerson.BanManager.commands;

import me.JollyPerson.BanManager.BanManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class kickCommand implements CommandExecutor {

    public BanManager main;
    public String prefix;
    public String kickMessage;
    public String kickAnnouncement;
    public String sendKickAnnouncement;
    public String sendKickMessage;

    public kickCommand(BanManager main) {
        this.main = main;
        this.prefix = main.getConfig().getString("pluginPrefix");
        this.kickAnnouncement = main.getConfig().getString("punishments.kick.kickAnnouncement");
        this.kickMessage = main.getConfig().getString("punishments.kick.kickMessage");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        ChatColor.translateAlternateColorCodes('&', prefix);
        if (label.equalsIgnoreCase("kick")) {
            System.out.println("Found command");
            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target.isOnline()) {
                    String reason = "";

                    for (int i = 1; i < args.length; i++) { //loop threw all the arguments
                        String arg = args[i] + " "; //get the argument, and add a space so that the words get spaced out
                        reason = reason + arg; //add the argument to myString
                    }
                    this.sendKickAnnouncement = kickAnnouncement.replaceAll("%kickedPlayer%", target.getName()).replaceAll("%sender%", sender.getName()).replaceAll("%reason%", reason).replaceAll("%newLine%", "\n");
                    this.sendKickMessage = kickMessage.replaceAll("%kickedPlayer%", target.getName()).replaceAll("%sender%", sender.getName()).replaceAll("%reason%", reason).replaceAll("%newLine%", "\n");
                    target.kickPlayer(ChatColor.translateAlternateColorCodes('&', sendKickMessage));
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', sendKickAnnouncement));
                }
            }
        }
        return true;
    }
}

