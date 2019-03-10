package ru.frostdelta.stalkerweapons;

import org.bukkit.plugin.java.JavaPlugin;
import ru.frostdelta.stalkerweapons.events.FireEventListener;

public final class StalkerWeapons extends JavaPlugin {

    private static StalkerWeapons plugin;

    @Override
    public void onEnable() {
       this.saveDefaultConfig();
       getLogger().info("Developed by: " + getDescription().getAuthors());
       plugin = this;
       getServer().getPluginManager().registerEvents(new FireEventListener(), this);

       //TODO подгрузка оружия
    }


    public static StalkerWeapons inst(){
        return plugin;
    }

    @Override
    public void onDisable() {
    }
}
