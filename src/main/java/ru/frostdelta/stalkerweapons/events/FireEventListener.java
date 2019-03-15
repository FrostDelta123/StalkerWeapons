package ru.frostdelta.stalkerweapons.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.Random;

public class FireEventListener implements Listener {


    private void shot(Player player) {
        double accuracy = 0.0;

        Arrow bullet = player.launchProjectile(Arrow.class);
        bullet.setGravity(false);



    }

    @EventHandler
    public void interact(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getItemInHand() != null && player.getItemInHand().getData().getItemType().equals(Material.AIR)){
            return;
        }
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Bukkit.broadcastMessage(String.valueOf(player.getItemInHand().getDurability()));
            shot(event.getPlayer());
            event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.BLAZE_SHOOT, 20);
        }
    }

    @EventHandler
    public void fire(FireEvent event){
        if(event.getWeapon().getAmmo() > 0) {
            event.getWeapon().shot();
        }else {
            event.getPlayer().sendMessage("No ammo!!!");
            event.setCancelled(true);
        }

        //TODO Сделать логику выстрела в Weapon.shot с учетом разброса и отдачи
    }

}
