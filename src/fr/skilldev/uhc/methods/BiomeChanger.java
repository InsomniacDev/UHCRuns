package fr.skilldev.uhc.methods;

import java.lang.reflect.Field;
import java.util.Arrays;

public class BiomeChanger
{
  private static Class<?> biomeBase;
  private static Field biomesField;
  private static Object[] biomesCopy;
  
  static
  {
    try
    {
      biomeBase = ReflectionHandler.getClass("BiomeBase", ReflectionHandler.PackageType.MINECRAFT_SERVER);
      biomesField = biomeBase.getDeclaredField("biomes");
      biomesField.setAccessible(true);
      biomesCopy = Arrays.copyOf(getBiomes(), 256);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static void changeBiome(int id, int toId)
  {
    Object[] biomes = getBiomes();
    biomes[id] = biomesCopy[toId];
  }
  
  public static Object[] getBiomes()
  {
    try
    {
      return (Object[])biomesField.get(null);
    }
    catch (IllegalArgumentException|IllegalAccessException e)
    {
      e.printStackTrace();
    }
    return new Object['w'];
  }
}
