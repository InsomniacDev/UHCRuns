package fr.skilldev.uhc;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {
	
	@EventHandler
	public void allDamage(EntityDamageEvent event) {
		if (!MatchMaking.started || !Countdown.degs) {
			if ((event.getCause() == DamageCause.FALL) || (event.getCause() == DamageCause.DROWNING) || (event.getCause() == DamageCause.LAVA) || (event.getCause() == DamageCause.SUFFOCATION)){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onChunkUnload(ChunkUnloadEvent event)
	{
		event.getChunk().load(true);
	}
	
	@EventHandler
	public void food(FoodLevelChangeEvent event) {
		if (!MatchMaking.started) {
		if (event.getEntity() instanceof Player) {
		Player p = (Player) event.getEntity();
		p.setFoodLevel(20);
		p.setHealth(20);
		}
		}
	}
	
	@EventHandler
	public void blockDamage(BlockDamageEvent event) {
		if (!MatchMaking.started) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void blockBreak(BlockBreakEvent event) {
		if (MatchMaking.started) {
			
			Block b = event.getBlock();
			
			if (b.getType() == Material.IRON_ORE) {
				b.setType(Material.AIR);
				b.breakNaturally();
				b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.IRON_INGOT,2));
			}
			
			if (b.getType() == Material.GOLD_ORE) {
				b.setType(Material.AIR);
				b.breakNaturally();
				b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.GOLD_INGOT,2));
			}
			
			if (b.getType() == Material.COAL_ORE) {
				b.setType(Material.AIR);
				b.breakNaturally();
				b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.TORCH,4));
			}
			
			if (b.getType() == Material.LAPIS_ORE) {
				b.setType(Material.AIR);
				b.breakNaturally();
				b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.EXP_BOTTLE,2));
			}
			
			if (b.getType() == Material.GRAVEL) {
				b.setType(Material.AIR);
				b.breakNaturally();
				b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.ARROW,2));
			}
			
			if (b.getType() == Material.REDSTONE_ORE) {
				b.setType(Material.AIR);
				b.breakNaturally();
				b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.EXP_BOTTLE,2));
			}
			
		} else {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		e.getPlayer().removeMetadata("Alive", Main.instance);
	}
	
	@EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
		
            if (e.getEntity() instanceof Sheep) {
                    e.getDrops().clear();
                    Random r = new Random();
            		int random = r.nextInt(2);
            		if (random == 1) {
                    e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.STRING));
            		}
            	}
            
            if (e.getEntity() instanceof Cow) {
                e.getDrops().clear();
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.COOKED_BEEF, 2));
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.LEATHER, 1));
            }
            
            if (e.getEntity() instanceof Spider) {
                e.getDrops().clear();
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.STRING, 2));
            }
            
            if (e.getEntity() instanceof CaveSpider) {
                e.getDrops().clear();
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.STRING, 4));
            }
            
            if (e.getEntity() instanceof Creeper) {
                e.getDrops().clear();
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.TNT));
            }
            
            if (e.getEntity() instanceof Chicken) {
                e.getDrops().clear();
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.COOKED_CHICKEN, 2));
            }
            
            if (e.getEntity() instanceof Squid) {
                e.getDrops().clear();
                Random r = new Random();
        		int random = r.nextInt(2);
        		if (random == 1) {
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.BOOK));
        		}
            }
            
            if (e.getEntity() instanceof Zombie) {
                e.getDrops().clear();
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.POTATO, 1));
            }
            
            if (e.getEntity() instanceof Skeleton) {
                e.getDrops().clear();
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.BOW, 1));
            }
            
            if (e.getEntity() instanceof Pig) {
                e.getDrops().clear();
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.GRILLED_PORK, 2));
                Random r = new Random();
        		int random = r.nextInt(2);
        		if (random == 1) {
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.GRILLED_PORK, 2));
        		}
            }
            
    }
	
	@EventHandler
	public void death(PlayerDeathEvent event) {
		event.getEntity().getWorld().strikeLightning(event.getEntity().getLocation());
		MatchMaking.vivants -= 1;
		event.getEntity().removeMetadata("Alive", Main.instance);
		event.getEntity().setGameMode(GameMode.SPECTATOR);
		event.setDeathMessage(ChatColor.GOLD + event.getEntity().getName() + ChatColor.RED + " est mort dans d'atroce souffrances!");
	}
}