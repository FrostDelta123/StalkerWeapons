package ru.frostdelta.stalkerweapons;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import ru.frostdelta.stalkerweapons.events.FireEvent;

import java.util.ArrayList;
import java.util.List;

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


    //TODO идентификация патрон по лору, если оружие поднято с земли и всё такое, предположим он уже есть у оружия, изначально оно не заряжено

    public Weapon(Player player, ItemStack item){
        this.name = item.getItemMeta().getDisplayName();
        this.player = player;
        FileConfiguration cfg = StalkerWeapons.inst().getConfig();
        ConfigurationSection section = cfg.getConfigurationSection("weapons." + name);
        damage = section.getDouble("damage");
        lore = item.getItemMeta().getLore();
        String[] lor = lore.get(0).split(":");
        ammo = Integer.parseInt(lor[1].trim());
        effect = Effect.getById(section.getInt("effect"));
        accuracy = section.getDouble("accuracy");
        recoil = section.getInt("recoil");
        texture = section.getDouble("texture");
        run = section.getDouble("run");
        aim = section.getDouble("aim");
        itemStack = item;
    }

    public boolean isAiming(){
        return itemStack.getDurability()/1562 == aim;
    }

    public boolean isRunning(){
        return itemStack.getDurability()/1562 == run;
    }

    public void shot() {
        ammo--;
        Arrow bullet = player.launchProjectile(Arrow.class);
        bullet.setGravity(false);
        bullet.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
        bullet.setCustomName(name);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getLore().clear();
        itemMeta.getLore().add("Боезапас: " + 222);
        player.getItemInHand().setItemMeta(itemMeta);
        //TODO отдача и изменение боезапаса(подсмотреть в Join ивенте )
        //FireEvent event = new FireEvent(this);
        //Bukkit.getServer().getPluginManager().callEvent(event);
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
