package fr.skilldev.uhc.methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils
{
  public static void copyFolder(File src, File dest)
    throws IOException
  {
    if (src.isDirectory())
    {
      if (!dest.exists()) {
        dest.mkdir();
      }
      String[] files = src.list();
      String[] arrayOfString1;
      int j = (arrayOfString1 = files).length;
      for (int i = 0; i < j; i++)
      {
        String file = arrayOfString1[i];
        File srcFile = new File(src, file);
        File destFile = new File(dest, file);
        copyFolder(srcFile, destFile);
      }
    }
    else
    {
      InputStream in = new FileInputStream(src);
      OutputStream out = new FileOutputStream(dest);
      byte[] buffer = new byte['w'];
      int length;
      while ((length = in.read(buffer)) > 0)
      {
        out.write(buffer, 0, length);
      }
      in.close();
      out.close();
    }
  }
  
  public static boolean delete(File path)
  {
    if (path.exists())
    {
      File[] files = path.listFiles();
      File[] arrayOfFile1;
      int j = (arrayOfFile1 = files).length;
      for (int i = 0; i < j; i++)
      {
        File file = arrayOfFile1[i];
        if (file.isDirectory()) {
          delete(file);
        } else {
          file.delete();
        }
      }
    }
    return path.delete();
  }
}
