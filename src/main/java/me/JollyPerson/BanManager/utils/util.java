package me.JollyPerson.BanManager.utils;

import org.bukkit.ChatColor;

public class util {

    public static String format(String format, Character replace) {
        return ChatColor.translateAlternateColorCodes(replace, format);
    }

    public static String colorize(String msg) {
        String coloredMsg = "";
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '&')
                coloredMsg += '§';
            else
                coloredMsg += msg.charAt(i);
        }
        return coloredMsg;
    }
}
