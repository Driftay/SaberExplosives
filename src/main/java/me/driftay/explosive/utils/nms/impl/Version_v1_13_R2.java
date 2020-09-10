package me.driftay.explosive.utils.nms.impl;

import me.driftay.explosive.SaberExplosives;
import me.driftay.explosive.utils.Util;
import me.driftay.explosive.utils.nms.NMSHandler;
import net.minecraft.server.v1_13_R2.Entity;
import net.minecraft.server.v1_13_R2.EntityLiving;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftCreeper;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static me.driftay.explosive.utils.Util.config;

public class Version_v1_13_R2 implements NMSHandler {

    private SaberExplosives instance;

    public Version_v1_13_R2(SaberExplosives instance){
        this.instance = instance;
    }

    public void spawnCreeper(Block block) {
        CraftCreeper creeper = block.getWorld().spawn(block.getLocation().add(0.5, 0, 0.5), CraftCreeper.class);
        creeper.setCustomName(Util.color("&2Throwable Creeper"));
        creeper.setCustomNameVisible(false);
        creeper.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 3));
        Entity nms = ((CraftEntity) creeper).getHandle();
        NBTTagCompound nbttag = new NBTTagCompound();
        nms.c(nbttag);
        nbttag.setInt("Fuse", 0);
        nbttag.setInt("ExplosionRadius", config.getInt("Throwable.Creeper-Manager.Explode-Radius"));
        EntityLiving livingcreeper = (EntityLiving) nms;
        livingcreeper.a(nbttag);
    }

    @Override
    public String getVersion() {
        return "v1_13_R2";
    }
}
