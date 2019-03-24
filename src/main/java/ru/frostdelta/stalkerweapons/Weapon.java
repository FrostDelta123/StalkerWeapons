package ru.frostdelta.stalkerweapons;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Weapon {

    private static List<Player> reloadingPlayers = new ArrayList<Player>();

    private ItemStack itemStack;
    private String name;
    private double damage;
    private int ammo;
    private Effect effect;
    private double accuracy;
    private Player player;
    private int recoil;
    private int maxAmmo;
    private double run;
    private double texture;
    private double aim;
    private List<String> lore = new ArrayList<String>();
    private int reloadingTime;
    private long speed;
    private boolean explosion;

    public Weapon(Player player, ItemStack item){
        this.name = item.getItemMeta().getDisplayName();
        this.player = player;
        FileConfiguration cfg = StalkerWeapons.inst().getConfig();
        ConfigurationSection section = cfg.getConfigurationSection("weapons." + name);
        damage = section.getDouble("damage");
        lore = item.getItemMeta().getLore();
        String[] lor = lore.get(0).split(":");
        ammo = Integer.parseInt(lor[1].trim());
        maxAmmo = section.getInt("max-ammo");
        effect = Effect.getById(section.getInt("effect"));
        accuracy = section.getDouble("accuracy");
        recoil = section.getInt("recoil");
        texture = section.getDouble("texture");
        run = section.getDouble("run");
        aim = section.getDouble("aim");
        reloadingTime = section.getInt("reload");
        speed = section.getLong("speed");
        explosion = section.getBoolean("explosion");
        itemStack = item;
    }

    public boolean isAiming(){
        return player.getItemInHand().getDurability() == aim * 1562;
    }


    public static ItemStack create(String name){
        ItemStack item = new ItemStack(Material.DIAMOND_HOE, 1);
        FileConfiguration cfg = StalkerWeapons.inst().getConfig();
        ConfigurationSection section = cfg.getConfigurationSection("weapons." + name);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(section.getString("name"));
        List<String> lore = new ArrayList<String>();
        lore.add("Боезапас: " + section.getString("ammo"));
        lore.add("Урон: " + section.getDouble("damage"));
        lore.add("Сила отдачи: " + section.getInt("recoil"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        item.setDurability((short) (section.getDouble("texture") * 1562));
        return item;
    }

    public void shot() {
        if(Cooldown.isInCooldown(player, name)){
            return;
        }
        if(isReload(player)){
            player.sendMessage("У тебя релоад");
            return;
        }
        if(ammo <= 0){
            int amount = 0;
            ItemStack itemStack;
            Inventory inv = player.getInventory();
            if(inv.contains(Material.ARROW)){
            setReloading(player, true);
            new Scheduler(player).runTaskLater(StalkerWeapons.inst(), reloadingTime * 20);
            player.sendMessage("No ammo! Reloading...");
            for(ItemStack bullet : inv.getContents()){
                if(bullet != null) {
                    if (bullet.getType().equals(Material.ARROW)) {
                        amount += bullet.getAmount();
                        if (amount > maxAmmo) {
                            itemStack = new ItemStack(Material.ARROW, maxAmmo);
                            inv.removeItem(itemStack);
                            reload(maxAmmo);
                            return;
                        } else {
                            itemStack = new ItemStack(Material.ARROW, amount);
                            inv.removeItem(itemStack);
                            reload(amount);
                            return;
                        }
                    }
                }
            }
            }else {
                player.sendMessage("No ammo!!!");
                return;
            }
        }
        FileConfiguration cfg = StalkerWeapons.inst().getConfig();
        ConfigurationSection section = cfg.getConfigurationSection("weapons." + name);
        ammo--;
        Arrow bullet = player.launchProjectile(Arrow.class);
        bullet.setGravity(false);
        bullet.setPickupStatus(Arrow.PickupStatus.CREATIVE_ONLY);
        bullet.setCustomName(name);

        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> list = new ArrayList<String>();
        list.add("Боезапас: " + ammo);
        list.add("Урон: " + section.getDouble("damage"));
        list.add("Сила отдачи: " + section.getInt("recoil"));

        itemMeta.setLore(list);
        player.getItemInHand().setItemMeta(itemMeta);
        Location loc = player.getLocation();
        loc.setPitch(player.getLocation().getPitch() + recoil);
        player.teleport(loc);
        new Cooldown(player, name, speed).start();

    }

    private void reload(int bullets){
        FileConfiguration cfg = StalkerWeapons.inst().getConfig();
        ConfigurationSection section = cfg.getConfigurationSection("weapons." + name);

        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> list = new ArrayList<String>();
        list.add("Боезапас: " + bullets);
        list.add("Урон: " + section.getDouble("damage"));
        list.add("Сила отдачи: " + section.getInt("recoil"));

        itemMeta.setLore(list);
        player.getItemInHand().setItemMeta(itemMeta);
    }

    public static void setReloading(Player reload, boolean b){
        if(b){
            getReloadingPlayers().add(reload);
        }else getReloadingPlayers().remove(reload);
    }

    public double getTexture() {
        return texture;
    }

    public double getAimTexture() {
        return aim;
    }

    public double getRunTexture() {
        return run;
    }

    private boolean isReload(Player player){
        return getReloadingPlayers().contains(player);
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

    public static List<Player> getReloadingPlayers() {
        return reloadingPlayers;
    }
}
