package me.driftay.cegg.hooks.impl;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.driftay.cegg.dCeggs;
import me.driftay.cegg.hooks.PluginHook;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class WorldGuardHook implements PluginHook<WorldGuardHook> {

    private static boolean instantiated = false;
    private WorldGuardPlugin worldGuardPlugin;

    public WorldGuardHook() {
        super();
    }

    @Override
    public WorldGuardHook setup(dCeggs plugin) {
        this.worldGuardPlugin = WorldGuardPlugin.inst();
        instantiated = true;
        return this;
    }

    public boolean canBuild(Player player, Location block) {
        if (!instantiated) {
            return true;
        }
        return worldGuardPlugin.canBuild(player, block);
    }



    @Override
    public String getName() {
        return "WorldGuard";
    }

}
