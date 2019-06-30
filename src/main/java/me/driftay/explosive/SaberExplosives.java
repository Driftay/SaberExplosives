package me.driftay.explosive;

import me.driftay.explosive.command.CmdCegg;
import me.driftay.explosive.command.CmdSemtex;
import me.driftay.explosive.listener.ThrowManagerCeggs;
import me.driftay.explosive.listener.ThrowManagerSnowBalls;
import me.driftay.explosive.managers.FileManager;
import me.driftay.explosive.managers.HookManager;
import me.driftay.explosive.managers.NMSManager;
import me.driftay.explosive.utils.Glow;
import me.driftay.explosive.utils.nms.NMSHandler;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class SaberExplosives extends JavaPlugin {

    public static SaberExplosives instance;
    private NMSManager nmsManager;
    private FileManager fileManager;
    private HookManager hookManager;

    public void onEnable(){
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        registerGlow();

        if(getConfig().getBoolean("Use-Throwable-Creeper-Eggs")){
            getServer().getPluginManager().registerEvents(new ThrowManagerCeggs(),this);
            getCommand("scegg").setExecutor(new CmdCegg());
        }

        if(getConfig().getBoolean("Use-Semtexs")){
            getServer().getPluginManager().registerEvents(new ThrowManagerSnowBalls(), this);
            getCommand("semtex").setExecutor(new CmdSemtex());
        }

        hookManager = new HookManager(this);
        fileManager = new FileManager(this);
        nmsManager = new NMSManager(this);
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

    public void registerGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Glow glow = new Glow(70);
            Enchantment.registerEnchantment(glow);
        }
        catch (IllegalArgumentException e){
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
