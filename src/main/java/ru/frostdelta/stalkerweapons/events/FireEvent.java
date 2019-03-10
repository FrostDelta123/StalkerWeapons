package ru.frostdelta.stalkerweapons.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import ru.frostdelta.stalkerweapons.Weapon;

public class FireEvent extends Event implements Cancellable {


    private Player player;
    private Weapon weapon;

    public FireEvent(Weapon weapon){
        this.weapon = weapon;
        player = weapon.getPlayer();
    }

    public Player getPlayer() {
        return player;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean arg0) {
    }

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
