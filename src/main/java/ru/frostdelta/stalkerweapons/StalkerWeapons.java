package ru.frostdelta.stalkerweapons;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.frostdelta.stalkerweapons.events.FireEventListener;
import ru.frostdelta.stalkerweapons.events.HitEvent;

import java.util.HashMap;
import java.util.Map;

public final class StalkerWeapons extends JavaPlugin {

    private static StalkerWeapons plugin;

    private static Map<Double, String> weaponMap = new HashMap<Double, String>();

    @Override
    public void onEnable() {

       this.saveDefaultConfig();
       getLogger().info("Developed by: " + getDescription().getAuthors());
       plugin = this;
       getServer().getPluginManager().registerEvents(new FireEventListener(), this);
       getServer().getPluginManager().registerEvents(new HitEvent(), this);
       FileConfiguration cfg = this.getConfig();

        for(String weapon : cfg.getConfigurationSection("weapons").getKeys(false)){
            ConfigurationSection section = cfg.getConfigurationSection("weapons." + weapon);
            StalkerWeapons.getWeapons().put(section.getDouble("texture"), section.getName().replaceAll("'", ""));
            StalkerWeapons.getWeapons().put(section.getDouble("aim"), section.getName().replaceAll("'", ""));
        }
    }

    public static boolean isWeapon(double d){
        return weaponMap.containsKey(d);
    }
    public static Map<Double, String> getWeapons() {
        return weaponMap;
    }

    public static String getWeapon(Double d) {
        return weaponMap.get(d);
    }

    public static StalkerWeapons inst(){
        return plugin;
    }

    @Override
    public void onDisable() {
    }
}
