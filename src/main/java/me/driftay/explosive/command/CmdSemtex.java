package me.driftay.explosive.command;

import me.driftay.explosive.utils.Message;
import me.driftay.explosive.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@SuppressWarnings("Duplicates")
public class CmdSemtex implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("give")) {
                if (!sender.hasPermission("saberexplosives.give")) {
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
                        Bukkit.getServer().getPlayer(args[1]).getInventory().addItem(Util.createSnowballItem(amount));
                        Bukkit.getServer().getPlayer(args[1]).sendMessage(Message.SEMTEX_RECEIVED_MESSAGE.getMessage());
                        return true;
                    }
                }
            }
        } else if (sender.hasPermission("saberexplosives.give")) {
            sender.sendMessage(Message.SEMTEX_COMMAND_USAGE.getMessage());
        }
        return false;
    }
}
