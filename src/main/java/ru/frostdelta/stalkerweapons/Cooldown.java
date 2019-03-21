package ru.frostdelta.stalkerweapons;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
public class Cooldown {

    public static Map<String, Cooldown> cooldowns = new HashMap<String, Cooldown>();
    private long start;
    private final long timeInSeconds;
    private final Player id;
    private final String cooldownName;

    public Cooldown(Player id, String cooldownName, long timeInSeconds){
        this.id = id;
        this.cooldownName = cooldownName;
        this.timeInSeconds = timeInSeconds;
    }

    public static boolean isInCooldown(Player id, String cooldownName){
        if(!cooldowns.containsKey(id.getName()+cooldownName)){
            return false;
        }
        if(getTimeLeft(id, cooldownName) >= 0){
            return true;
        } else {
            stop(id, cooldownName);
            return false;
        }
    }

    private static void stop(Player id, String cooldownName){
        Cooldown.cooldowns.remove(id.getName()+cooldownName);
    }

    private static Cooldown getCooldown(Player id, String cooldownName){
        return cooldowns.get(id.getName()+cooldownName);
    }

    public static long getTimeLeft(Player id, String cooldownName){
        Cooldown cooldown = getCooldown(id, cooldownName);
        long f = 0;
        if(cooldown!=null){
            long now = System.currentTimeMillis();
            long cooldownTime = cooldown.start;
            long totalTime = cooldown.timeInSeconds;
            f = (totalTime + cooldownTime) - now;
        }
        return f;
    }

    public void start(){
        this.start = System.currentTimeMillis();
        cooldowns.put(this.id.getName()+this.cooldownName, this);
    }

}
