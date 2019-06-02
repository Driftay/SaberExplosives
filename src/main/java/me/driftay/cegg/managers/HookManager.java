package me.driftay.cegg.managers;

import me.driftay.cegg.dCeggs;
import me.driftay.cegg.hooks.PluginHook;
import me.driftay.cegg.hooks.impl.WorldGuardHook;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class HookManager {

    private dCeggs plugin;
    private static Map<String, PluginHook<?>> pluginMap = new HashMap<>();

    public HookManager(dCeggs plugin) {
        this.plugin = plugin;
        hookPlugin(new WorldGuardHook());

    }

    private void hookPlugin(PluginHook pluginHook) {
        if (plugin.getServer().getPluginManager().getPlugin(pluginHook.getName()) != null) {
            pluginMap.put(pluginHook.getName(), (PluginHook<?>) pluginHook.setup(plugin));
        } else {
            plugin.getServer().getLogger().log(Level.SEVERE, "Plugin failed to find " + pluginHook.getName());
        }
    }

    public static Map<String, PluginHook<?>> getPluginMap() {
        return pluginMap;
    }

}
