package me.driftay.explosive.listener;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import me.driftay.explosive.SaberExplosives;
import me.driftay.explosive.hooks.impl.WorldGuardHook;
import me.driftay.explosive.managers.HookManager;
import me.driftay.explosive.utils.Message;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.projectiles.ProjectileSource;

import static me.driftay.explosive.utils.Util.color;
import static me.driftay.explosive.utils.Util.config;

@SuppressWarnings("Duplicates")
public class ThrowManagerSnowBalls implements Listener {

    @EventHandler
    public void onThrowSnowBall(PlayerInteractEvent e) {
        Faction faction = Board.getInstance().getFactionAt(new FLocation(e.getPlayer()));
        if (faction == null) return;
        ItemStack i = e.getPlayer().getItemInHand();
        if(i == null || !i.hasItemMeta() || !i.getItemMeta().hasDisplayName() || !i.getItemMeta().getDisplayName().equalsIgnoreCase((color(config.getString("Throwable.Snowball.Item.Name"))))) return;

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Message.SEMTEX_CANNOT_PLACE.getMessage());
        } else if (e.getAction() == Action.RIGHT_CLICK_AIR) {

            if (HookManager.getPluginMap().get("WorldGuard") != null) {
                WorldGuardHook wgHook = ((WorldGuardHook) HookManager.getPluginMap().get("WorldGuard"));
                if (!wgHook.canBuild(e.getPlayer(), e.getPlayer().getLocation())) {
                    e.getPlayer().sendMessage(Message.SEMTEX_CANNOT_THROW_HERE.getMessage().replace("%faction%", faction.getTag()));
                    e.setCancelled(true);
                    return;
                }

                if (faction == Factions.getInstance().getSafeZone() || faction == Factions.getInstance().getWarZone()) {
                    e.getPlayer().sendMessage(Message.SEMTEX_CANNOT_THROW_HERE.getMessage().replace("%faction%", faction.getTag()));
                    e.setCancelled(true);
                    return;
                }

                if (e.getPlayer().getItemInHand().getAmount() == 1) {
                    e.getPlayer().setItemInHand(null);
                } else {
                    e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
                }
            }
        }
    }



    @EventHandler
    public void onThrow2(ProjectileLaunchEvent e) {
        ProjectileSource shooter = e.getEntity().getShooter();
        if(!(shooter instanceof Player)) return;

        if (e.getEntity() instanceof Snowball) {
            if (((Player) shooter).getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(color(config.getString("Throwable.Snowball.Item.Name")))) {
                e.getEntity().setMetadata("throwable", new FixedMetadataValue(SaberExplosives.instance, true));
            }
        }
    }

    @EventHandler
    public void onHit(ProjectileHitEvent e) {
        Faction faction = Board.getInstance().getFactionAt(new FLocation(e.getEntity().getLocation()));

        if (faction.isWarZone() || faction.isSafeZone())
            return;

        if (e.getEntity() instanceof Snowball) {
            if (e.getEntity().hasMetadata("throwable")) {
                SaberExplosives.instance.getNMS().spawnCreeper(e.getEntity().getLocation().getBlock());
                e.getEntity().remove();
            }
        }
    }
}
