package me.driftay.cegg.managers;

import me.driftay.cegg.dCeggs;
import me.driftay.cegg.utils.nms.NMSHandler;
import me.driftay.cegg.utils.nms.impl.*;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class NMSManager {


    private dCeggs plugin;
    private Map<String, NMSHandler> nmsMap = new HashMap<>();

    public NMSManager(dCeggs plugin) {
        this.plugin = plugin;
        if(Bukkit.getServer().getVersion().contains("1.8.8")) {
            addVersion(new Version_1_8_R3(plugin));
        }
        if(Bukkit.getServer().getVersion().contains("1.11")) {
            addVersion(new Version_v1_11_R1(plugin));
        }
        if(Bukkit.getServer().getVersion().contains("1.12")) {
            addVersion(new Version_v1_12_R1(plugin));
        }
        if(Bukkit.getServer().getVersion().contains("1.13.1")) {
            addVersion(new Version_v1_13_R1(plugin));
        }
        if(Bukkit.getServer().getVersion().contains("1.13.2")) {
            addVersion(new Version_v1_13_R2(plugin));
        }
    }

    private void addVersion(NMSHandler nms) {
        nmsMap.put(nms.getVersion(), nms);
    }

    public Map<String, NMSHandler> getNMSMap() {
        return nmsMap;
    }
}
