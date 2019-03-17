package ru.frostdelta.stalkerweapons.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import ru.frostdelta.stalkerweapons.StalkerWeapons;
import ru.frostdelta.stalkerweapons.Weapon;

public class FireEventListener implements Listener {

    @EventHandler
    public void interact(PlayerInteractEvent event){
        Player player = event.getPlayer();


        if(player.getItemInHand() != null && player.getItemInHand().getData().getItemType().equals(Material.AIR) && !player.getItemInHand().getData().getItemType().equals(Material.DIAMOND_HOE)){
            return;
        }
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            double damage = ((double) player.getItemInHand().getDurability());
            double d = damage / 1562;
            if(StalkerWeapons.getWeapons().containsKey(d)){
                //TODO логика выстрела
            }
            Bukkit.broadcastMessage(String.valueOf(d));
            //shot(event.getPlayer());
            //event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.BLAZE_SHOOT, 20);
        }
    }

    @EventHandler
    public void fire(FireEvent event){
        if(event.getWeapon().getAmmo() > 0) {
            event.getWeapon().shot(event.getPlayer());
        }else {
            event.getPlayer().sendMessage("No ammo!!!");
            event.setCancelled(true);
        }
    }

}
