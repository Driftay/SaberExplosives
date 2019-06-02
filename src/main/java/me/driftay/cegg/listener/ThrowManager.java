package me.driftay.cegg.listener;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import me.driftay.cegg.dCeggs;
import me.driftay.cegg.hooks.impl.WorldGuardHook;
import me.driftay.cegg.managers.HookManager;
import me.driftay.cegg.utils.Message;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftCreeper;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static me.driftay.cegg.utils.Util.color;
import static me.driftay.cegg.utils.Util.config;

@SuppressWarnings("Duplicates")
public class ThrowManager implements Listener {


    //@EventHandler
    //public void explode(BlockExplodeEvent e){
    //    FLocation floc = new FLocation(e.getBlock().getLocation());
    //    Faction fac = Board.getInstance().getFactionAt(floc);
    //    if (fac.isSafeZone() || fac.isWarZone()) {
    //        e.setCancelled(true);
    //    }
    //}



    //List<Block> block = e.blockList();
    //for (Block b : block) {
    //    if (b.getType().equals(Material.MOB_SPAWNER)) {
    //        CreatureSpawner cs = (CreatureSpawner)b.getState();
    //        ItemStack spawnerItem = new ItemStack(b.getType(), 1);
    //        Bukkit.getServer().broadcastMessage("its not null");
    //        spawnerItem.getItemMeta().setDisplayName(ChatColor.RESET + cs.getCreatureTypeName() + " Spawner");
    //        // spawnerMeta.setDisplayName(color(config.getString("Spawner-Name").replaceAll("%type%", capital(cs.getSpawnedType().getName().replaceAll("_", " ")))));
    //        BlockStateMeta blockMeta = (BlockStateMeta)spawnerItem.getItemMeta();
    //        spawnerItem.setItemMeta(blockMeta);
    //        e.getBlock().getLocation().getWorld().dropItemNaturally(b.getLocation(), spawnerItem);
    //    }
    //}

    @EventHandler
    public void onThrow(PlayerInteractEvent e) {
        Faction faction = Board.getInstance().getFactionAt(new FLocation(e.getPlayer()));
        if (faction == null) return;
        if (e.getPlayer().getItemInHand() == null) return;
        if (e.getPlayer().getItemInHand().getItemMeta() == null) return;
        if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName() == null) return;
        if (!e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(color(config.getString("Throwable.Item.Name"))))
            return;

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Message.CANNOT_PLACE.getMessage());
        } else if (e.getAction() == Action.RIGHT_CLICK_AIR) {

            //WorldGuard
            if (HookManager.getPluginMap().get("WorldGuard") != null) {
                WorldGuardHook wgHook = ((WorldGuardHook) HookManager.getPluginMap().get("WorldGuard"));
                if (!wgHook.canBuild(e.getPlayer(), e.getPlayer().getLocation())) {
                    e.getPlayer().sendMessage(Message.CREEPER_CANNOT_THROW_HERE.getMessage().replace("%faction%", faction.getTag()));
                    e.setCancelled(true);
                    return;
                }

                //Factions
                if (faction == Factions.getInstance().getSafeZone() || faction == Factions.getInstance().getWarZone()) {
                    e.getPlayer().sendMessage(Message.CREEPER_CANNOT_THROW_HERE.getMessage().replace("%faction%", faction.getTag()));
                    e.setCancelled(true);
                    return;
                }


                Item item = e.getPlayer().getWorld().dropItem(e.getPlayer().getEyeLocation(), new ItemStack(Material.MONSTER_EGG, 1, (short) 50));

                item.setVelocity(e.getPlayer().getEyeLocation().getDirection());

                if (e.getPlayer().getItemInHand().getAmount() == 1) {
                    e.getPlayer().setItemInHand(null);
                } else {
                    e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
                }

                item.setPickupDelay(Integer.MAX_VALUE);


                new BukkitRunnable() {
                    public void run() {
                       dCeggs.instance.getNMS().spawnCreeper(item.getLocation().getBlock());
                        item.remove();
                    }
                }.runTaskLater(dCeggs.instance, (long) (20L * config.getDouble("Throwable.Creeper-Manager.Explode-Timer")));
            }
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if (e.getPlayer().getType() != EntityType.PLAYER) {
            return;
        }
        if (e.getItem().getItemStack().getType() != Material.MONSTER_EGG) {
            return;
        }

        if (e.getItem().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(color(config.getString("Throwable.Item.Name")))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHopperGlitch(InventoryPickupItemEvent e) {
        if (e.getInventory().getType() != InventoryType.HOPPER) return;
        if (e.getItem().getItemStack().getType() != Material.MONSTER_EGG) return;

        if (e.getItem().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(color(config.getString("Throwable.Item.Name")))) {
            e.setCancelled(true);
        }
    }

}


