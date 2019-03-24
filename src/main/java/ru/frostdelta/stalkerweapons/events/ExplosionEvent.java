package ru.frostdelta.stalkerweapons.events;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import ru.frostdelta.stalkerweapons.StalkerWeapons;

public class ExplosionEvent implements Listener {

    @EventHandler
    public void explosion(ProjectileHitEvent event){
        if(event.getEntity() instanceof Arrow){
            Arrow bullet = (Arrow) event.getEntity();
            if(bullet.getCustomName() != null){
                FileConfiguration cfg = StalkerWeapons.inst().getConfig();
                ConfigurationSection section = cfg.getConfigurationSection("weapons." + bullet.getCustomName());
                if(section.getBoolean("explosion")){
                    event.getHitBlock().getWorld().createExplosion(event.getHitBlock().getLocation(), (float) section.getDouble("exp-power"));
                }
                event.getEntity().remove();
            }
        }
    }

}
