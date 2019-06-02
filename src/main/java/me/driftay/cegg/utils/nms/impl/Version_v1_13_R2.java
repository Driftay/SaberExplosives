package me.driftay.cegg.utils.nms.impl;

import me.driftay.cegg.dCeggs;
import me.driftay.cegg.utils.nms.NMSHandler;
import net.minecraft.server.v1_13_R2.Entity;
import net.minecraft.server.v1_13_R2.EntityLiving;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftCreeper;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;

import static me.driftay.cegg.utils.Util.config;

public class Version_v1_13_R2 implements NMSHandler {

    private dCeggs instance;

    public Version_v1_13_R2(dCeggs instance){
        this.instance = instance;
    }

    public void spawnCreeper(Block block) {
        CraftCreeper creeper = block.getWorld().spawn(block.getLocation().add(0.5, 1, 0.5), CraftCreeper.class);
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
