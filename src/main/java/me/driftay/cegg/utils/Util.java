package me.driftay.cegg.utils;

import me.driftay.cegg.dCeggs;
import me.driftay.cegg.itemnbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static FileConfiguration config = dCeggs.instance.getConfig();

    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> color(List<String> string) {
        List<String> colored = new ArrayList<>();
        for (String line : string) {
            colored.add(color(line));
        }
        return colored;
    }


    public static ItemStack createCreeperEggItem(int amt) {
        ItemStack sponge = new ItemStack(Material.MONSTER_EGG, amt, (short) 50);
        if(config.getBoolean("Throwable.Item.Glow")){
            sponge.addEnchantment(Glow.getGlow(), 1);
        }
        ItemMeta spongeItemMeta = sponge.getItemMeta();
        spongeItemMeta.setDisplayName(color(config.getString("Throwable.Item.Name")));
        spongeItemMeta.setLore(color(config.getStringList("Throwable.Item.Lore")));
        sponge.setItemMeta(spongeItemMeta);
        NBTItem spongeNBTItem = new NBTItem(sponge);
        spongeNBTItem.setBoolean("throwable", true);
        return spongeNBTItem.getItem();
    }


}
