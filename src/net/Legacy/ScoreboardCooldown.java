package net.Legacy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public abstract class ScoreboardCooldown implements Plugin {
	
	static ScoreboardManager manager = Bukkit.getScoreboardManager();
	static Scoreboard board = manager.getNewScoreboard();
	static Objective objective = board.registerNewObjective("test", "dummy");
	
	public static int count1 = 1200;
	public static String timer = "";
	 
    public static void startCounter(Player player) {
    
    	new BukkitRunnable() {
    	  @Override
    	  public void run() {
    		  
    		int minutes = (count1 % 3600) / 60;
    		int seconds = (count1 % 60);
    		  
    	      if(count1 == 0) {
    	          //Start game method
    	          Bukkit.broadcastMessage("Teleporting players to arena");
    	          cancel(); //Cancels the timer
    	      }else{
    	    	  if (timer != "") {
    	    	board.resetScores(timer);
    	    	  }
    	        count1 = count1 - 1;
    	      	player.setScoreboard(board);
    	    	Score score = objective.getScore(ChatColor.GREEN + "Temps: " + String.format("%02d:%02d", minutes, seconds)); //Get a fake offline player
    	    	score.setScore(0);
    	    	timer = (ChatColor.GREEN + "Temps: " + String.format("%02d:%02d", minutes, seconds));
    	      }
    	  }
    	}.runTaskTimer(Main.instance, 20L, 20L);
    }
	
}