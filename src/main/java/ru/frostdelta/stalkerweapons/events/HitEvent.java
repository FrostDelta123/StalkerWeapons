package ru.frostdelta.stalkerweapons.events;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ru.frostdelta.stalkerweapons.StalkerWeapons;

public class HitEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void hit(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Arrow && event.getEntity() instanceof Player){
            Arrow bullet = (Arrow) event.getDamager();
            Player player = (Player) event.getEntity();
            if(bullet.getCustomName() != null) {
                FileConfiguration cfg = StalkerWeapons.inst().getConfig();
                ConfigurationSection section = cfg.getConfigurationSection("weapons." + bullet.getCustomName());
                player.damage(section.getDouble("damage"));
            }
        }
    }

}
