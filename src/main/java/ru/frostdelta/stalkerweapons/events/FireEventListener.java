package ru.frostdelta.stalkerweapons.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.frostdelta.stalkerweapons.StalkerWeapons;
import ru.frostdelta.stalkerweapons.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireEventListener implements Listener {


    @EventHandler
    public void join(PlayerJoinEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Калаш");
        List<String> list = new ArrayList<String>();
        list.add("Боезапас: 30");
        meta.setLore(list);

        item.setItemMeta(meta);
    }

    @EventHandler
    public void interact(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();

        if(item != null && item.getData().getItemType().equals(Material.AIR) && !item.getData().getItemType().equals(Material.DIAMOND_HOE)){
            return;
        }
        if(item.hasItemMeta() && !item.getItemMeta().hasLore()){
            return;
        }
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            double damage = ((double) item.getDurability());
            double d = damage / 1562;

            //for(Map.Entry<Double, String> entry : StalkerWeapons.getWeapons().entrySet()) {
               //Bukkit.broadcastMessage(String.valueOf(entry.getKey()) + " value " + entry.getValue());
           //}

            if(StalkerWeapons.isWeapon(d)){
                Bukkit.broadcastMessage("Shot");

                //TODO логика выстрела
            }
            //Bukkit.broadcastMessage(ChatColor.YELLOW + String.valueOf(damage));
            //Bukkit.broadcastMessage(ChatColor.GOLD + String.valueOf(d));
            //shot(event.getPlayer());
            //event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.BLAZE_SHOOT, 20);
            new Weapon(player, item).shot();
        }
    }

    @EventHandler
    public void fire(FireEvent event){
        if(event.getWeapon().getAmmo() > 0) {
            //event.getWeapon().shot();
        }else {
            event.getPlayer().sendMessage("No ammo!!!");
            event.setCancelled(true);
        }
    }

}
