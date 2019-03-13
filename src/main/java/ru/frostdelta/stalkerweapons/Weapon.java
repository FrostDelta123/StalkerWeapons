package ru.frostdelta.stalkerweapons;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Weapon {


    private ItemStack itemStack;
    private String name;
    private double damage;
    private int ammo;
    private Effect effect;
    private double accuracy;
    private Player player;
    private int recoil;
    private double run;
    private double texture;
    private double aim;
    private List<String> lore = new ArrayList<String>();

    public Weapon(String name, Player player){
        this.name = name;
        FileConfiguration cfg = StalkerWeapons.inst().getConfig();
        ConfigurationSection section = cfg.getConfigurationSection("weapons." + name);
        damage = section.getDouble("damage");
        ammo = section.getInt("ammo");
        effect = Effect.getById(section.getInt("effect"));
        accuracy = section.getDouble("accuracy");
        this.player = player;
        recoil = section.getInt("recoil");
        texture = section.getDouble("texture");
        run = section.getDouble("run");
        aim = section.getDouble("aim");
        itemStack = new ItemStack(Material.DIAMOND_HOE, 1, (short) texture);
        lore.add("Боезапас: " + ammo);
        itemStack.getItemMeta().setLore(lore);
    }

    public boolean isAiming(){
        return itemStack.getDurability() == aim;
    }

    public boolean isRunning(){
        return itemStack.getDurability() == run;
    }

    public void shot() {
        ammo--;

        SmallFireball fireball = player.launchProjectile(SmallFireball.class);
        double d = new Random().nextDouble();
        Vector vector = player.getEyeLocation().getDirection();
        //TODO сделать рандом, каккую величину менять (X, Y или Z)
        int num = new Random().nextInt(2);
        switch (num){
            case 0: vector.setX(vector.getX() * accuracy);
                    break;
            case 1: vector.setX(vector.getY() * accuracy);
                    break;
            case 2: vector.setX(vector.getZ() * accuracy);
        }

        if(d < accuracy) {
            fireball.setDirection(player.getEyeLocation().getDirection());
        }else fireball.setDirection(vector);
        Bukkit.broadcastMessage("launch");
        //TODO поменять лор
    }

    public int getRecoil() {
        return recoil;
    }

    public Player getPlayer() {
        return player;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public Effect getEffect() {
        return effect;
    }

    public int getAmmo() {
        return ammo;
    }
}
