package net.Legacy;

import java.util.logging.Logger;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

public class Main extends JavaPlugin implements Listener {
	
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public static Main instance = null;
	
	@Override
	public void onEnable(){
		
		ScoreboardCooldown.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		ScoreboardCooldown.objective.setDisplayName(ChatColor.GOLD + "=== UHC Run ===");
		
		instance = this;

		getServer().getPluginManager().registerEvents(this, this);
		
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " has been enabled!");
	
}
	
	public static Main getInstance() {
		  return instance;
		}
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " has been disabled!");
}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		Player player = (Player) sender;
		
		if (label.equalsIgnoreCase("force-start")) {
			if (player.isOp()) {
				ScoreboardCooldown.startCounter(player);
			}
		}
		
		return true;
		
	}
	
}