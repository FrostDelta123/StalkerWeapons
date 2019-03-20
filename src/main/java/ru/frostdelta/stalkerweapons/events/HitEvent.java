package ru.frostdelta.stalkerweapons.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class HitEvent implements Listener {

    @EventHandler
    public void hit(EntityDamageByEntityEvent event){
        //TODO Логика попадания
    }

}
