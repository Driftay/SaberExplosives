package me.driftay.cegg.utils;

import me.driftay.cegg.dCeggs;
import org.bukkit.ChatColor;

public class Logger {

    public static void print(String message, PrefixType type) {
        dCeggs.instance.getServer().getConsoleSender().sendMessage(type.getPrefix() + message);
    }

    public enum PrefixType {

        WARNING(ChatColor.RED + "WARNING: "), NONE(""), DEFAULT(ChatColor.GOLD + "[dCeggs] "), FAILED(ChatColor.RED + "FAILED: ");

        private String prefix;

        PrefixType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return this.prefix;
        }

    }

}
