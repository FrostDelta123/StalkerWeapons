package ru.frostdelta.stalkerweapons;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import ru.frostdelta.stalkerweapons.events.MoveEvent;

public class SprintScheduler extends BukkitRunnable {

    private Player player;

    public SprintScheduler(Player player){
        this.player = player;
    }

    @Override
    public void run() {
        ItemStack item = player.getItemInHand();
        double damage = ((double) item.getDurability());
        double d = damage / 1562;
        if(StalkerWeapons.isWeapon(d)) {
            if (player.isSprinting()) {
                int durability = (int) (new Weapon(player, item).getRunTexture() * 1562);
                item.setDurability((short) durability);
            } else {
                int durability = (int) (new Weapon(player, item).getTexture() * 1562);
                item.setDurability((short) durability);
                MoveEvent.players.remove(player);
                this.cancel();
            }
        }
    }

}
