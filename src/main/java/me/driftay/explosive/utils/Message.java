package me.driftay.explosive.utils;

import org.bukkit.ChatColor;

public enum Message {

    NO_PERMISSION("no-permission", "&c&l[!] &7You do not have permission."),
    CANNOT_DROP("cannot-drop", "&c&l[!] &cYou cannot drop a throwable semtex!"),
    PLAYER_NOT_FOUND("player-not-found", "&c&l[!] &b%player% &7is not online!"),


    SEMTEX_COMMAND_USAGE("Semtex.Usage", "&c&l[!] &7Try /semtex give <player> <amount>."),
    SEMTEX_RECEIVED_MESSAGE("Semtex.Received-Message","&c&l[!] &7You have received a semtex."),
    SEMTEX_CANNOT_THROW_HERE("Semtex.cannot-throw-here", "&c&l[!] &7You cannot throw a semtex in %faction%!"),
    SEMTEX_CANNOT_PLACE("Semtex.cannot-place", "&c&l[!] &7You cannot place a semtex's, try right clicking!"),

    CEGG_COMMAND_USAGE("CreeperEgg.Usage", "&c&l[!] &7Try /scegg give <player> <amount>."),
    CEGG_RECEIVED_MESSAGE("CreeperEgg.Received-Message","&c&l[!] &7You have received a throwable creeper egg."),
    CEGG_CANNOT_THROW_HERE("CreeperEgg.cannot-throw-here", "&c&l[!] &7You cannot throw a creeper egg in %faction%!"),
    CEGG_CANNOT_PLACE("CreeperEgg.cannot-place", "&c&l[!] &7You cannot place a throwable creeper, try right clicking!");

    String config, message;

    Message(String config, String message) {
        this.config = config;
        this.message = ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getConfig() {
        return config;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

