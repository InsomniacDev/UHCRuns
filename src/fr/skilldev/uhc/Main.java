package fr.skilldev.uhc;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.skilldev.uhc.methods.BiomeChanger;
import fr.skilldev.uhc.methods.FileUtils;
import fr.skilldev.uhc.methods.MySQL;

public class Main extends JavaPlugin implements Listener {

	public World world;
	public static WorldBorder border;
	public static Main instance;
	public static String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "UHCRanks" + ChatColor.DARK_GRAY + "]" + ChatColor.WHITE + " ";
	MySQL MySQL = new MySQL(instance, "localhost", "3306", "stats", "root", "Biscuit123");
	public static Connection c = null;
	public static int MaxJoueurs = 20;
	public static int NbJoueurRequis = 2;
	public static int Temps1 = 120; //DEBUT DU JEU
	public static int Temps2 = 60; //LES DEGATS
	public static int Temps3 = 1200; //TEMPS DE JEUX
	
	
	public void onEnable()
	{
		instance = this;
		World world = (World)Bukkit.getWorlds().get(0);
	    Location spawnLoc = new Location(world, 0.0D, 0.0D, 0.0D);
	    world.getChunkAt(spawnLoc).load(true);
	    world.setDifficulty(Difficulty.HARD);
	    spawnLoc.setY(world.getHighestBlockYAt(0, 0) + 36);
	    world.setSpawnLocation(spawnLoc.getBlockX(), spawnLoc.getBlockY(), spawnLoc.getBlockZ());
	    for (int x = -7; x <= 7; x++) {
	      for (int y = -1; y <= 0; y++) {
	        for (int z = -7; z <= 7; z++) {
	          if ((y == -1) && (z != -7) && (z != 7) && (x != -7) && (x != 7))
	          {
	            Block block = spawnLoc.clone().add(x, y, z).getBlock();
	            block.setType(Material.STAINED_GLASS);
	            block.setData(DyeColor.WHITE.getWoolData());
	          }
	          else if (((y == 0) && (z == 7)) || (z == -7) || (x == 7) || (x == -7))
	          {
	            Block block = spawnLoc.clone().add(x, y, z).getBlock();
	            block.setType(Material.BARRIER);
	            block.getRelative(BlockFace.UP).setType(Material.BARRIER);
	          }
	        }
	      }
	    }
	    Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
	    Objective health = board.getObjective("health");
	    if (health != null) {
	      health.unregister();
	    }
	    health = board.registerNewObjective("health", "health");
	    health.setDisplaySlot(DisplaySlot.PLAYER_LIST);
	    world.setSpawnLocation(0, spawnLoc.getBlockY(), 0);
	    world.setGameRuleValue("doDaylightCycle", "false");
	    world.setTime(6000L);
	    
		border = world.getWorldBorder();
		border.setSize(500.0);
		border.setCenter(0, 0);
	    border.setWarningDistance(5);
	    
	    clearCommands(Arrays.asList(new String[] { "kill", "me", "tell" }));

		getServer().getPluginManager().registerEvents(new MatchMaking(), this);
		getServer().getPluginManager().registerEvents(new Events(), this);
		getServer().getPluginManager().registerEvents(this, this);
	    
	    Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	
	public void onLoad()
	{
		try
		{
			File worldContainer = getServer().getWorldContainer();
			FileUtils.delete(new File(worldContainer, "world"));
			FileUtils.delete(new File(worldContainer, "world_nether"));
			FileUtils.delete(new File(worldContainer, "world_the_end"));
			BiomeChanger.changeBiome(0, 2);
			BiomeChanger.changeBiome(7, 4);
			BiomeChanger.changeBiome(11, 12);
			BiomeChanger.changeBiome(24, 2);
		}
		catch (Throwable $ex) {}
	}
	
	  public void clearCommands(final List<String> commandNames)
	  {
	    try
	    {
	      Field f = getCommandMap().getClass().getDeclaredField("knownCommands");
	      f.setAccessible(true);
	      final Object list = f.get(getCommandMap());
	      new BukkitRunnable()
	      {
	        public void run()
	        {
	          Map<String, Command> commands = (Map)list;
	          ArrayList<Command> deleted = new ArrayList();
	          for (Command cmd : commands.values()) {
	            if ((commandNames.contains(cmd.getName())) && (cmd.getPermission().startsWith("bukkit.command"))) {
	              deleted.add(cmd);
	            }
	          }
	          for (Command d : deleted) {
	            commands.values().remove(d);
	          }
	        }
	      }.runTaskLater(this, 1L);
	    }
	    catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e)
	    {
	      e.printStackTrace();
	    }
	  }
	  
	public static void teleportToLobby(Player player)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
	    out.writeUTF("lobby");
	    player.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
	}
	
	  public CommandMap getCommandMap()
	  {
	    try
	    {
	      Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
	      f.setAccessible(true);
	      return (CommandMap)f.get(Bukkit.getServer());
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    return null;
	  }
	  
	 /* private void register(Class<? extends MainListener>... classes)
	  {
	    try
	    {
	      Class[] arrayOfClass;
	      int j = (arrayOfClass = classes).length;
	      for (int i = 0; i < j; i++)
	      {
	        Class<? extends MainListener> clazz = arrayOfClass[i];
	        Constructor<? extends MainListener> constructor = clazz.getConstructor(new Class[] { Main.class });
	        Bukkit.getPluginManager().registerEvents((Listener)constructor.newInstance(new Object[] { this }), this);
	      }
	    }
	    catch (Throwable $ex) {}
	  }
	  */
	
}
