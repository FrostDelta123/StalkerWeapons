package ru.frostdelta.stalkerweapons.events;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import ru.frostdelta.stalkerweapons.StalkerWeapons;
import ru.frostdelta.stalkerweapons.Weapon;

public class FireEventListener implements Listener {


    //@EventHandler
    //public void join(PlayerJoinEvent event){
    //   event.getPlayer().getInventory().addItem(Weapon.create("G36C"));
    //}

    @EventHandler(priority = EventPriority.HIGHEST)
    public void interact(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();

        if(item != null && item.getData().getItemType().equals(Material.AIR) && !item.getData().getItemType().equals(Material.DIAMOND_HOE)){
            return;
        }
        if(item.hasItemMeta() && !item.getItemMeta().hasLore()){
            return;
        }
        double damage = ((double) item.getDurability());
        double d = damage / 1562;
        if(StalkerWeapons.isWeapon(d)){
            Weapon weapon = new Weapon(player, item);
            Action action = event.getAction();
            if(action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR)) {
                weapon.shot();
            }
            if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)){
                if(weapon.isAiming()){
                    int durability = (int) (weapon.getTexture() * 1562);
                    item.setDurability((short) durability);
                }else {
                    int durability = (int) (weapon.getAimTexture() * 1562);
                    item.setDurability((short) durability);
                }
            }
        }
    }

}
