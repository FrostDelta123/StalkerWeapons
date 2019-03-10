package ru.frostdelta.stalkerweapons.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FireEventListener implements Listener {

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
