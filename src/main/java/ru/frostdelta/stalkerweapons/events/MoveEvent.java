package ru.frostdelta.stalkerweapons.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import ru.frostdelta.stalkerweapons.SprintScheduler;
import ru.frostdelta.stalkerweapons.StalkerWeapons;

import java.util.ArrayList;
import java.util.List;

public class MoveEvent implements Listener {

    public static List<Player> players = new ArrayList<Player>();

    @EventHandler(priority = EventPriority.LOWEST)
    public void sprint(PlayerMoveEvent event){
        ItemStack item = event.getPlayer().getItemInHand();
        double damage = ((double) item.getDurability());
        double d = damage / 1562;
        if(!StalkerWeapons.isWeapon(d)){
            return;
        }
        if (event.getPlayer().isSprinting() && !players.contains(event.getPlayer())){
            players.add(event.getPlayer());
            new SprintScheduler(event.getPlayer()).runTaskTimerAsynchronously(StalkerWeapons.inst(), 0, 10);
        }
    }

}
