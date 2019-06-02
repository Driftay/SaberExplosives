package me.driftay.cegg.utils;

import org.bukkit.ChatColor;

public enum Message {

    NO_PERMISSION("no-permission", "&c&l[!] &7You do not have permission."),
    PLAYER_NOT_FOUND("player-not-found", "&c&l[!] &b%player% &7is not online!"),
    CEGG_COMMAND_USAGE("command-usage", "&c&l[!] &7Try /dcegg give <player> <amount>."),
    CEGG_RECEIVED_MESSAGE("received-message","&c&l[!] &7You have received a throwable creeper egg."),
    CREEPER_CANNOT_THROW_HERE("cannot-throw-here", "&c&l[!] &7You cannot throw a creeper egg in %faction%!"),
    CANNOT_PLACE("cannot-place", "&c&l[!] &7You cannot place a throwable creeper, try right clicking!");

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

