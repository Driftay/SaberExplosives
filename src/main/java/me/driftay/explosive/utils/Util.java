package me.driftay.explosive.utils;

import me.driftay.explosive.SaberExplosives;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static FileConfiguration config = SaberExplosives.instance.getConfig();

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
        Glow glow = new Glow(1);
        if(config.getBoolean("Throwable.CreeperEgg.Item.Glow")){
            sponge.addUnsafeEnchantment(glow, 1);
        }

        ItemMeta spongeItemMeta = sponge.getItemMeta();
        spongeItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        spongeItemMeta.setDisplayName(color(config.getString("Throwable.CreeperEgg.Item.Name")));
        spongeItemMeta.setLore(color(config.getStringList("Throwable.CreeperEgg.Item.Lore")));
        sponge.setItemMeta(spongeItemMeta);
        return sponge;
    }

    public static ItemStack createSnowballItem(int amt) {
        ItemStack snowball = new ItemStack(Material.SNOW_BALL, amt);
        Glow glow = new Glow(1);
        if(config.getBoolean("Throwable.Snowball.Item.Glow")){
            snowball.addUnsafeEnchantment(glow, 1);
        }
        ItemMeta snowballItemMeta = snowball.getItemMeta();
        snowballItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        snowballItemMeta.setDisplayName(color(config.getString("Throwable.Snowball.Item.Name")));
        snowballItemMeta.setLore(color(config.getStringList("Throwable.Snowball.Item.Lore")));
        snowball.setItemMeta(snowballItemMeta);
        return snowball;
    }
}
