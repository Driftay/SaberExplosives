package me.driftay.explosive.listener;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import me.driftay.explosive.SaberExplosives;
import me.driftay.explosive.hooks.impl.WorldGuardHook;
import me.driftay.explosive.managers.HookManager;
import me.driftay.explosive.utils.Message;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static me.driftay.explosive.utils.Util.color;
import static me.driftay.explosive.utils.Util.config;

@SuppressWarnings("Duplicates")
public class ThrowManagerCeggs implements Listener {


    @EventHandler
    public void onThrow(PlayerInteractEvent e) {
        Faction faction = Board.getInstance().getFactionAt(new FLocation(e.getPlayer()));
        if (e.getPlayer().getItemInHand() == null) return;
        if (e.getPlayer().getItemInHand().getItemMeta() == null) return;
        if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName() == null) return;
        if (!e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(color(config.getString("Throwable.CreeperEgg.Item.Name"))))
            return;

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Message.CEGG_CANNOT_PLACE.getMessage());
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (HookManager.getPluginMap().get("WorldGuard") != null) {
                WorldGuardHook wgHook = ((WorldGuardHook) HookManager.getPluginMap().get("WorldGuard"));
                if (!wgHook.canBuild(e.getPlayer(), e.getPlayer().getLocation())) {
                    e.getPlayer().sendMessage(Message.CEGG_CANNOT_THROW_HERE.getMessage().replace("%faction%", faction.getTag()));
                    e.setCancelled(true);
                    return;
                }

                if (faction == Factions.getInstance().getSafeZone() || faction == Factions.getInstance().getWarZone()) {
                    e.getPlayer().sendMessage(Message.CEGG_CANNOT_THROW_HERE.getMessage().replace("%faction%", faction.getTag()));
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

                item.setPickupDelay((int) (25L * config.getDouble("Throwable.Creeper-Manager.Explode-Timer")));

                new BukkitRunnable() {
                    public void run() {
                        SaberExplosives.instance.getNMS().spawnCreeper(item.getLocation().getBlock());
                        item.remove();
                    }
                }.runTaskLater(SaberExplosives.instance, (long) (20L * config.getDouble("Throwable.Creeper-Manager.Explode-Timer")));
            }
        }
    }

    @EventHandler
    public void onHopperGlitch(InventoryPickupItemEvent e) {
        if (e.getInventory().getType() != InventoryType.HOPPER) return;
        if (e.getItem().getItemStack().getType() != Material.SNOW_BALL || e.getItem().getItemStack().getType() != Material.MONSTER_EGG)
            return;

        if (e.getItem().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(color(config.getString("Throwable.Snowball.Item.Name")))
                || e.getItem().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(color(config.getString("Throwable.CreeperEgg.Item.Name")))) {
            e.setCancelled(true);
        }
    }
}


