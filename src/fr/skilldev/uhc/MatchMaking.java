package fr.skilldev.uhc;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class MatchMaking implements Listener {
	
	public static int count = Main.Temps1;
	public static boolean countdown = false;
	public static boolean started = false;
	public static int dead;
	public static int vivants = 0;
	public static World world = (World)Bukkit.getWorlds().get(0);
	
	final int taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
         @SuppressWarnings("deprecation")
		public void run() {
        	 
        	 vivants = vivants - dead;
        	 
        	 if (countdown == true) {
        	 
      		for(Player all:Bukkit.getServer().getOnlinePlayers()){
    			all.sendTitle(ChatColor.GOLD + Integer.toString(count) + "...", "");
     			all.playSound(all.getLocation(), Sound.CLICK, 1, 1);
     		}
        	 
        	 count = count - 1;
        	 
        	 if (count <= -1) {
        		//Game Started
        		Countdown.startTime();
        		started = true;
           		for(Player all:Bukkit.getServer().getOnlinePlayers()){
           			
         		all.playSound(all.getLocation(), Sound.EXPLODE, 1, 1);
     			all.sendTitle(ChatColor.GOLD + "La partie va commencé", " dans 2 min !");
    			all.setFoodLevel(20);
    			all.setHealth(20);
    			all.getWorld().setTime(0);
    			all.setGameMode(GameMode.SURVIVAL);
    			all.setMetadata("Alive", new FixedMetadataValue(Main.instance, all));
        		Bukkit.getScheduler().cancelTask(taskID);
        		countdown = false;
       			
        		Random r = new Random();
        		int randomX = r.nextInt(250) + 1;
        		int randomZ = r.nextInt(250) + 1;
        		Location StartLoc = new Location(world, randomX, 0.0D, randomZ);
        		StartLoc.setY(world.getHighestBlockYAt(0, 0) + 36);
        		all.teleport(new Location(world, StartLoc.getX(), StartLoc.getY(), StartLoc.getZ()));
        		
           		}
        	 }
        	 }
        	 
         }
 }, 20, 20);

	@SuppressWarnings("deprecation")
	public static void CreateMatch(){
		
	    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {

			@Override
			public void run() {
				countdown = true;
				count = Main.Temps1;
				dead = 0;
				vivants = Bukkit.getOnlinePlayers().size();
			}

	    	}, 100);
		
		for(Player all:Bukkit.getServer().getOnlinePlayers()){
			all.sendTitle(ChatColor.RED + "Debut du jeu", "dans " + Main.Temps1 + " secondes");
 			all.getInventory().clear();
 			all.closeInventory();
     		all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 0);
		}
		
	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		player.getInventory().clear();
		
		if (!started) {
			player.setGameMode(GameMode.ADVENTURE);
			player.sendMessage(Main.prefix + ChatColor.GOLD + "Bienvenue en" + ChatColor.LIGHT_PURPLE + " UHCRanks" + ChatColor.GOLD + " !");
		    Location LocInit = new Location(world, 0.0D, 0.0D, 0.0D);
		    LocInit.setY(world.getHighestBlockYAt(0, 0) + 38);
			player.teleport(LocInit);
			vivants += 1;
		} else {
			player.setGameMode(GameMode.SPECTATOR);
		}
		
		if (!started) {
		event.setJoinMessage(Main.prefix + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " a rejoint la partie!" + ChatColor.LIGHT_PURPLE + " (" + vivants + "/" + Main.MaxJoueurs + ")");
		} else {
			event.setJoinMessage("");
		}
		if (Bukkit.getServer().getOnlinePlayers().size() >= Main.NbJoueurRequis) {
			CreateMatch();
		}
				
	}
	
	@EventHandler
	public void playerLeave(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		
		if (started) {
			if (player.hasMetadata("Alive")) {
			player.removeMetadata("Alive", Main.instance);
			vivants -= 1;
			}
		}
		
		if (!started) {
		event.setQuitMessage(Main.prefix + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " a quitté la partie!" + ChatColor.LIGHT_PURPLE + " (" + vivants + "/" + Main.MaxJoueurs + ")");
		} else {
		event.setQuitMessage("");
		}
				
	}

}
