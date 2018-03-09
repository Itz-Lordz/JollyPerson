package me.JollyPerson.BanManager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;


public class banCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ban")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("DiffusionBans.punishments.ban")) {
                    if (args.length >= 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            String reason = "";
                            for (int i = 1; i < args.length; i++) { //loop threw all the arguments
                                String arg = args[i] + " "; //get the argument, and add a space so that the words get spaced out
                                reason = reason + arg; //add the argument to myString
                            }
                            target.kickPlayer(ChatColor.DARK_RED + "PERMANENTLY BANNED!" + "\n" + ChatColor.RED + "Reason: " + reason + "\n" + ChatColor.GOLD + "Banned By: " + ChatColor.DARK_GREEN + player.getName() + "\n" + ChatColor.GREEN + "Apply at: ");
                            Bukkit.getServer().broadcastMessage("Player " + target + " has been banned for PERMANENT. Reason: " + reason);

                        } else {
                            player.sendMessage(ChatColor.RED + "Player " + args[0] + " cannot be found!");
                        }
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "/ban <player> <reason>");
                    }
                }
            }
            if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender console = (ConsoleCommandSender) sender;
                if (args.length >= 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        String reason = "";
                        for (int i = 1; i < args.length; i++) { //loop threw all the arguments
                            String arg = args[i] + " "; //get the argument, and add a space so that the words get spaced out
                            reason = reason + arg; //add the argument to myString
                        }
                        target.kickPlayer(ChatColor.DARK_RED + "PERMANENTLY BANNED!" + "\n" + ChatColor.RED + "Reason: " + reason + "\n" + ChatColor.GOLD + "Banned By: " + ChatColor.DARK_GREEN + console.getName() + "\n" + ChatColor.GREEN + "Apply at: ");
                    } else {
                        console.sendMessage(ChatColor.RED + "Player " + args[0] + "cannot be found!");
                    }
                } else {
                    console.sendMessage(ChatColor.YELLOW + "/ban <player> <reason>");
                }

            }
        }
        return true;
    }
}
