package ru.frostdelta.stalkerweapons;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Scheduler extends BukkitRunnable {

    private Player player;

    Scheduler(Player player){
        this.player = player;
    }

    @Override
    public void run() {
        Weapon.setReloading(player, false);
    }

}
