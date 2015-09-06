package fr.skilldev.uhc;

import java.beans.ConstructorProperties;
import org.bukkit.event.Listener;

public class MainListener
  implements Listener
{
  protected Main plugin;
  
  @ConstructorProperties({"plugin"})
  protected MainListener(Main plugin)
  {
    this.plugin = plugin;
  }
}

