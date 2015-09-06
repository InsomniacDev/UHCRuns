package fr.skilldev.uhc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Countdown implements Listener {
	
	static ScoreboardManager manager = Bukkit.getScoreboardManager();
	static Scoreboard board = manager.getNewScoreboard();
	public static Objective objective = board.registerNewObjective("time", "dummy");
	
	public static String time = "";
	public static String bord = "";
	public static String play = "";
	
	public static String degats = "";
	
	public static boolean hasWinner = false;
	
	public static Score score;
	public static int amount = 60;
	public static boolean degs;
	static int id;
	static int idwin;
	
	public static void winner() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (all.hasMetadata("Alive") && !hasWinner) {
				Bukkit.getServer().broadcastMessage(Main.prefix + ChatColor.GOLD + all.getName() + " a gagné!");
				hasWinner = true;
				waitforkick();
				return;
			}
		}
	}
	
	public static void waitforkick() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.setGameMode(GameMode.ADVENTURE);
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			  public void run() {
					for (Player all : Bukkit.getOnlinePlayers()) {
						Main.teleportToLobby(all);
						all.kickPlayer(Main.prefix + ChatColor.LIGHT_PURPLE + "Merci d'avoir joué!");
					}
			  }
			}, 100L);
	}
	
	public static void bordure() {

		idwin = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			
				@Override
	            public void run() {
					
					if (MatchMaking.vivants == 1) {
						winner();
	            		Bukkit.getScheduler().cancelTask(idwin);
	            		Bukkit.getScheduler().cancelTask(id);
					}
					
	        		if (bord != "") {
	            		board.resetScores(bord);
	            		}
	        		
	        		if (play != "") {
	            		board.resetScores(play);
	            		}
	        		
	        		score = objective.getScore(ChatColor.LIGHT_PURPLE + "Bordure: " + "-" + Math.round(Main.border.getSize()) + " | " + Math.round(Main.border.getSize()));
	        		score.setScore(0);
	        		
	        		score = objective.getScore(ChatColor.YELLOW + "Joueurs: " + ChatColor.RED + " (" + MatchMaking.vivants + "/" + Main.MaxJoueurs + ")");
	        		score.setScore(0);
	        		
	        		bord = (ChatColor.LIGHT_PURPLE + "Bordure: " + "-" + Math.round(Main.border.getSize()) + " | " + Math.round(Main.border.getSize()));
	        		play = (ChatColor.YELLOW + "Joueurs: " + ChatColor.RED + " (" + MatchMaking.vivants + "/" + Main.MaxJoueurs + ")");
	        		
	            }
				
	         }, 0L, 20L);
	            
	}
	
	public static void startTime() {

		 id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
            @SuppressWarnings("deprecation")
			@Override
            public void run() {
            	
				if (MatchMaking.vivants == 1) {
					winner();
            		Bukkit.getScheduler().cancelTask(id);
				}
				
				if (amount == 1200 && degs) {
               		for(Player all:Bukkit.getServer().getOnlinePlayers()){
             		all.playSound(all.getLocation(), Sound.CLICK, 1, 1);
         			all.sendTitle(ChatColor.GREEN + "20 Minutes restantes!", "");
               		}
            	}
            	
            	if (amount == 300 && degs) {
               		for(Player all:Bukkit.getServer().getOnlinePlayers()){
             		all.playSound(all.getLocation(), Sound.CLICK, 1, 1);
         			all.sendTitle(ChatColor.GREEN + "5 Minutes restantes!", "");
               		}
            	}
            	
            	if (amount == 60 && degs) {
               		for(Player all:Bukkit.getServer().getOnlinePlayers()){
             		all.playSound(all.getLocation(), Sound.CLICK, 1, 1);
         			all.sendTitle(ChatColor.GREEN + "1 Minutes restante!", "");
               		}
            	}
            	
            	if (amount == 30 && degs) {
               		for(Player all:Bukkit.getServer().getOnlinePlayers()){
             		all.playSound(all.getLocation(), Sound.CLICK, 1, 1);
         			all.sendTitle(ChatColor.GREEN + "30 Secondes restantes!", "");
               		}
            	}
            	
            	if (amount == 15 && degs) {
               		for(Player all:Bukkit.getServer().getOnlinePlayers()){
             		all.playSound(all.getLocation(), Sound.CLICK, 1, 1);
         			all.sendTitle(ChatColor.GREEN + "15 Secondes restantes!", "");
               		}
            	}
            	
            	if (amount < 1 && degs) {
               		for(Player all:Bukkit.getServer().getOnlinePlayers()){
             		all.playSound(all.getLocation(), Sound.EXPLODE, 1, 1);
         			all.sendTitle(ChatColor.LIGHT_PURPLE + "Attention à", "la réduction de la bordure !");
         			Main.border.setSize(25, 600);
         			bordure();
            		Bukkit.getScheduler().cancelTask(id);
               		}
            	}
            	
            	if (amount == 30 && !degs) {
               		for(Player all:Bukkit.getServer().getOnlinePlayers()){
             		all.playSound(all.getLocation(), Sound.EXPLODE, 1, 1);
         			all.sendTitle(ChatColor.GOLD + "Dégats actifs dans 30 secondes!", "");
               		}
            	}
            	
            	if (amount == 10 && !degs) {
               		for(Player all:Bukkit.getServer().getOnlinePlayers()){
             		all.playSound(all.getLocation(), Sound.EXPLODE, 1, 1);
         			all.sendTitle(ChatColor.GOLD + "Dégats actifs dans 10 secondes!", "");
               		}
            	}
            	
            	if (amount < 1 && !degs) {
               		for(Player all:Bukkit.getServer().getOnlinePlayers()){
             		all.playSound(all.getLocation(), Sound.EXPLODE, 1, 1);
         			all.sendTitle(ChatColor.RED + "Dégâts Activés!", "");
               		}
            		amount = Main.Temps3;
            		degs = true;
            	}
            	
            	if (amount <= 5 && amount > 0) {
              		for(Player all:Bukkit.getServer().getOnlinePlayers()){
            			all.sendTitle(ChatColor.RED + Integer.toString(amount), "");
             			all.playSound(all.getLocation(), Sound.CLICK, 1, 1);
             		}
            	}
            	
        		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        		objective.setDisplayName(ChatColor.GOLD + "=== UHCRanks ===");
        		
        		if (time != "") {
        		board.resetScores(time);
        		}
        		
        		if (degats != "") {
        		board.resetScores(degats);
        		}
        		
        		if (bord != "") {
        		board.resetScores(bord);
        		}
        		
        		if (play != "") {
        		board.resetScores(play);
        		}
        		
        		int minutes = (amount % 3600) / 60;
        		int seconds = amount % 60;
        		
        		Score score = objective.getScore(ChatColor.LIGHT_PURPLE + "Bordure: " + "-500 | 500");
        		score.setScore(0);
        		
        		if (degs) {
        		
        		score = objective.getScore(ChatColor.GREEN + "Temps: " + String.format("%02d:%02d", minutes, seconds));
        		score.setScore(0);
        		
        		} else {
        			
            		score = objective.getScore(ChatColor.RED + "Dégâts Activés dans: " + String.format("%02d:%02d", minutes, seconds));
            		score.setScore(0);
        			
        		}
        		
        		score = objective.getScore(ChatColor.YELLOW + "Joueurs: " + ChatColor.RED + " (" + MatchMaking.vivants + "/" + Main.MaxJoueurs + ")");
        		score.setScore(0);
        		
        		time = (ChatColor.GREEN + "Temps: " + String.format("%02d:%02d", minutes, seconds));
        		degats = (ChatColor.RED + "Dégâts Activés dans: " + String.format("%02d:%02d", minutes, seconds));
        		bord = (ChatColor.LIGHT_PURPLE + "Bordure: " + "-500 | 500");
        		play = (ChatColor.YELLOW + "Joueurs: " + ChatColor.RED + " (" + MatchMaking.vivants + "/" + Main.MaxJoueurs + ")");
        		
        		for (Player all : Bukkit.getOnlinePlayers()) {
        			all.setScoreboard(board);
        		}
        		
        		amount -= 1;
        		
            }
        }, 0L, 20L);
        
	}

}
