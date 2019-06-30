package me.driftay.explosive.hooks.impl;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.driftay.explosive.SaberExplosives;
import me.driftay.explosive.hooks.PluginHook;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WorldGuardHook implements PluginHook<WorldGuardHook> {

    private static boolean instantiated = false;
    private WorldGuardPlugin worldGuardPlugin;

    public WorldGuardHook() {
        super();
    }

    @Override
    public WorldGuardHook setup(SaberExplosives plugin) {
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
