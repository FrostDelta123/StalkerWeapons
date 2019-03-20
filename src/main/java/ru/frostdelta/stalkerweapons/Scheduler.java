package ru.frostdelta.stalkerweapons;

import org.bukkit.scheduler.BukkitRunnable;

public class Scheduler extends BukkitRunnable {

    private Weapon weapon;

    Scheduler(Weapon weapon){
        this.weapon = weapon;
    }

    @Override
    public void run() {
        weapon.setReloading(false);
    }

}
