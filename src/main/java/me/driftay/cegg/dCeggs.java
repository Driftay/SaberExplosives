package me.driftay.cegg;

import me.driftay.cegg.command.CmdEgg;
import me.driftay.cegg.listener.ThrowManager;
import me.driftay.cegg.managers.FileManager;
import me.driftay.cegg.managers.HookManager;
import me.driftay.cegg.managers.NMSManager;
import me.driftay.cegg.utils.nms.NMSHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class dCeggs extends JavaPlugin {

    public static dCeggs instance;
    private NMSManager nmsManager;
    private FileManager fileManager;
    private HookManager hookManager;

    public void onEnable(){
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        getCommand("dcegg").setExecutor(new CmdEgg());

        hookManager = new HookManager(this);
        fileManager = new FileManager(this);
        nmsManager = new NMSManager(this);

        getServer().getPluginManager().registerEvents(new ThrowManager(), this);
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public NMSHandler getNMS() {
        return this.nmsManager.getNMSMap().get(getVersion());
    }

    public final String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }
}
