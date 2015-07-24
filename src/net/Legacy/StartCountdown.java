package net.Legacy;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class StartCountdown
{
    private static int countdownTimer;
    
    public static ScoreboardManager manager = Bukkit.getScoreboardManager();
    public static Scoreboard board = manager.getNewScoreboard();
    public static Objective objective = board.registerNewObjective("timer", "dummy");
 
    public static void start(final int time, final String msg)
    {
        countdownTimer = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable()
        {
            int i = time;
 
            @SuppressWarnings("deprecation")
			public void run()
            {
            	
            	int minutes = (i % 3600) / 60;
            	int seconds = i % 60;
            	
            	board.resetScores("Temps : " + 14123);
            	
            	for(Player all : Bukkit.getServer().getOnlinePlayers()){
            		all.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);

            		all.setScoreboard(board);
            	}
        		
        		Score score = objective.getScore("Temps : " + String.format("%02d:%02d", minutes, seconds));
        		
        		score.setScore(0); //Integer only!
            	
                this.i--;
                if (this.i <= 0)
                {
                    cancel();
                }
                if (this.i == 420)
                {
                	for(Player all : Bukkit.getServer().getOnlinePlayers()){
                		all.sendTitle(ChatColor.GREEN + "Il reste 7 minutes!", "");
                	}
                }
                if (this.i == 300)
                {
                	for(Player all : Bukkit.getServer().getOnlinePlayers()){
                		all.sendTitle(ChatColor.GREEN + "Il reste 5 minutes!", "");
                	}
                }
                if (this.i == 120)
                {
                	for(Player all : Bukkit.getServer().getOnlinePlayers()){
                		all.sendTitle(ChatColor.GREEN + "Il reste 2 minutes!", "");
                	}
                }
                if (this.i == 60)
                {
                	for(Player all : Bukkit.getServer().getOnlinePlayers()){
                		all.sendTitle(ChatColor.GREEN + "Il reste 1 minute!", "");
                	}
                }
                if (this.i == 30)
                {
                	for(Player all : Bukkit.getServer().getOnlinePlayers()){
                		all.sendTitle(ChatColor.GREEN + "Il reste 30 secondes!", "");
                	}
                }
                if (this.i == 15)
                {
                	for(Player all : Bukkit.getServer().getOnlinePlayers()){
                		all.sendTitle(ChatColor.GREEN + "Il reste 15 secondes!", "");
                	}
                }
                if (this.i == 5)
                {
                	for(Player all : Bukkit.getServer().getOnlinePlayers()){
                		all.sendTitle(ChatColor.GREEN + "Il reste 5 secondes!", "");
                	}
                }
            }
        }
        , 0L, 20L);
    }
 
    public static void cancel()
    {
        Bukkit.getScheduler().cancelTask(countdownTimer);
    }
}
