package me.driftay.cegg.file.impl;

import me.driftay.cegg.dCeggs;
import me.driftay.cegg.file.CustomFile;
import me.driftay.cegg.utils.Message;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageFile extends CustomFile {

    private JavaPlugin instance;

    public MessageFile(JavaPlugin instance) {
        super(instance, "", "messages");
        this.instance = instance;
        for (Message message : Message.values()) {
            getConfig().addDefault(message.getConfig(), message.getMessage());
        }
        getConfig().options().copyDefaults(true);
        saveConfig();
    }


    public void init() {
        for (Message message : Message.values()) {
            message.setMessage(getConfig().getString(message.getConfig()));
        }
    }
}
