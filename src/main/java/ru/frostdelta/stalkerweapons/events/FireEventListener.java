package ru.frostdelta.stalkerweapons.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.Random;

public class FireEventListener implements Listener {



    private Vector change(Vector vector, int num, int second){

        //TODO работает, настроить точность
        double accuracy = 0.2;

        if(second == 0) {
            switch (num) {
                case 0:
                    vector.setX(vector.getX() + accuracy);
                    break;
                case 1:
                    vector.setY(vector.getY() + accuracy);
                    break;
                case 2:
                    vector.setZ(vector.getZ() + accuracy);
            }
        }else {
            switch (num) {
                case 0:
                    vector.setX(vector.getX() - accuracy);
                    break;
                case 1:
                    vector.setY(vector.getY() - accuracy);
                    break;
                case 2:
                    vector.setZ(vector.getZ() - accuracy);
            }
        }
        return vector;
    }


    private void shot(Player player) {
        double accuracy = 0.2;
        Fireball bullet = player.launchProjectile(Fireball.class);
        double d = new Random().nextDouble();
        Vector vector = player.getLocation().getDirection();
        //TODO сделать рандом, каккую величину менять (X, Y или Z)
        int num = new Random().nextInt(3);

        //TODO ПОЛНЫЙ ПИЗДЕЦ НАХУЙ БЛЯТЬ, ТУТ ПЕРЕДЕЛАТЬ К ХУЯМ НИХУЯ НЕ РАБОТАЕТ ЕБАНЫЙ В РОТ
        //КОСТЫЛЬ ЕБУЧИЙ, ПЕРЕДЕЛАТЬ
        if(d < accuracy) {

            bullet.setDirection(player.getLocation().getDirection());
        }else bullet.setDirection(change(vector, num, new Random().nextInt(2)));

    }

    @EventHandler
    public void fire11(PlayerInteractEvent event){
        if(event.getAction().equals(Action.LEFT_CLICK_AIR)) {
            shot(event.getPlayer());
            event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.BLAZE_SHOOT, 0);
        }
    }

    @EventHandler
    public void fire(FireEvent event){
        if(event.getWeapon().getAmmo() > 0) {
            event.getWeapon().shot();
        }else {
            event.getPlayer().sendMessage("No ammo!!!");
            event.setCancelled(true);
        }

        //TODO Сделать логику выстрела в Weapon.shot с учетом разброса и отдачи
    }

}
