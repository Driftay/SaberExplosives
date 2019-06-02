package me.driftay.cegg.command;

import me.driftay.cegg.utils.Message;
import me.driftay.cegg.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdEgg implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("give")) {
                if (!sender.hasPermission("dcegg.give")) {
                    sender.sendMessage(Message.NO_PERMISSION.getMessage());
                    return true;
                }
                if (Bukkit.getPlayer(args[1]) == null || !Bukkit.getPlayer(args[1]).isOnline()) {
                    sender.sendMessage(Message.PLAYER_NOT_FOUND.getMessage().replace("%player%", args[1]));
                    return true;
                }
                int amount = Integer.parseInt(args[2]);
                if (amount >= 1) {
                    if (Bukkit.getServer().getPlayer(args[1]).isOnline()) {
                        Bukkit.getServer().getPlayer(args[1]).getInventory().addItem(Util.createCreeperEggItem(amount));
                        Bukkit.getServer().getPlayer(args[1]).sendMessage(Message.CEGG_RECEIVED_MESSAGE.getMessage());
                        return true;
                    }
                }
            }
        } else if (sender.hasPermission("dcegg.give")) {
            sender.sendMessage(Message.CEGG_COMMAND_USAGE.getMessage());
        }
        return false;
    }
}
