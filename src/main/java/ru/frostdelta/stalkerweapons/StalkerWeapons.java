package ru.frostdelta.stalkerweapons;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.frostdelta.stalkerweapons.events.ExplosionEvent;
import ru.frostdelta.stalkerweapons.events.FireEventListener;
import ru.frostdelta.stalkerweapons.events.HitEvent;
import ru.frostdelta.stalkerweapons.events.MoveEvent;

import java.util.HashMap;
import java.util.Map;

public final class StalkerWeapons extends JavaPlugin {

    private static StalkerWeapons plugin;

    private static Map<Double, String> weaponMap = new HashMap<Double, String>();
    private static Map<Double, String> runGuns = new HashMap<Double, String>();

    @Override
    public void onEnable() {

       this.saveDefaultConfig();
       getLogger().info("Developed by: " + getDescription().getAuthors());
       plugin = this;
       getServer().getPluginManager().registerEvents(new FireEventListener(), this);
       getServer().getPluginManager().registerEvents(new HitEvent(), this);
       getServer().getPluginManager().registerEvents(new MoveEvent(), this);
       getServer().getPluginManager().registerEvents(new ExplosionEvent(), this);
       FileConfiguration cfg = this.getConfig();
       getCommand("weapongive").setExecutor(new WeaponGiveCommand());

        for(String weapon : cfg.getConfigurationSection("weapons").getKeys(false)){
            ConfigurationSection section = cfg.getConfigurationSection("weapons." + weapon);
            StalkerWeapons.getWeapons().put(section.getDouble("texture"), section.getName().replaceAll("'", ""));
            StalkerWeapons.getWeapons().put(section.getDouble("aim"), section.getName().replaceAll("'", ""));
            StalkerWeapons.runGuns.put(section.getDouble("run"), section.getName().replaceAll("'", ""));
        }
    }

    public static boolean isRunning(Double d){
        return runGuns.containsKey(d);
    }

    public static boolean isWeapon(double d){
        return weaponMap.containsKey(d);
    }
    private static Map<Double, String> getWeapons() {
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
        getLogger().info("Developed by: " + getDescription().getAuthors());
    }
}
