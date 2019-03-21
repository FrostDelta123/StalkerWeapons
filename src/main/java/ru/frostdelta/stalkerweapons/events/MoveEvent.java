package ru.frostdelta.stalkerweapons.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import ru.frostdelta.stalkerweapons.SprintScheduler;
import ru.frostdelta.stalkerweapons.StalkerWeapons;

import java.util.ArrayList;
import java.util.List;

public class MoveEvent implements Listener {

    public static List<Player> players = new ArrayList<Player>();

    @EventHandler(priority = EventPriority.LOWEST)
    public void sprint(PlayerMoveEvent event){
        if (event.getPlayer().isSprinting() && !players.contains(event.getPlayer())){
            players.add(event.getPlayer());
            new SprintScheduler(event.getPlayer()).runTaskTimer(StalkerWeapons.inst(), 0, 20);
        }
    }

}
