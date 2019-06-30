package me.driftay.explosive.utils;

import me.driftay.explosive.SaberExplosives;
import org.bukkit.ChatColor;

public class Logger {

    public static void print(String message, PrefixType type) {
        SaberExplosives.instance.getServer().getConsoleSender().sendMessage(type.getPrefix() + message);
    }

    public enum PrefixType {

        WARNING(ChatColor.RED + "WARNING: "), NONE(""), DEFAULT(ChatColor.GOLD + "[SaberExplosives] "), FAILED(ChatColor.RED + "FAILED: ");

        private String prefix;

        PrefixType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return this.prefix;
        }

    }

}
