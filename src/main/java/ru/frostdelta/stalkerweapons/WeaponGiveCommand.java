package ru.frostdelta.stalkerweapons;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class WeaponGiveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String st, String[] args) {
        FileConfiguration cfg = StalkerWeapons.inst().getConfig();
        if(args.length != 2){
            sender.sendMessage(ChatColor.RED + "Неверное использование!");
            return true;
        }
        if(!Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
            sender.sendMessage(ChatColor.RED + "Игрок не онлайн!");
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if(!cfg.getConfigurationSection("weapons").getKeys(false).contains(args[1]) && !args[1].equals("all")){
            sender.sendMessage(ChatColor.RED + "Оружие не найдено!");
            return true;
        }
        if(args[1].equals("all")){
            for(String name : cfg.getConfigurationSection("weapons").getKeys(false)){
                player.getInventory().addItem(Weapon.create(name));
            }
            sender.sendMessage(ChatColor.GOLD + "Всё оружие выдано игроку: " + ChatColor.RED + player.getName());
        }else {
            player.getInventory().addItem(Weapon.create(args[1]));
            sender.sendMessage(ChatColor.GOLD + "Оружие " + ChatColor.RED + args[1] + ChatColor.RESET + " выдано игроку: " + ChatColor.RED + player.getName());
        }
        return true;
    }

}
