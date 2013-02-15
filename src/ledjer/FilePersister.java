package ledjer;

import java.io.*;

public class FilePersister implements Persister
{
  private String filename;

  public FilePersister(String filename)
  {
    this.filename = filename;
  }

  public void save(Object thing)
  {
    try
    {
      ObjectOutputStream output =
        new ObjectOutputStream(
          new FileOutputStream(filename));
      output.writeObject(thing);
      output.close();
    }
    catch(IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  public Object load()
  {
    try
    {
      ObjectInputStream input =
        new ObjectInputStream(
          new FileInputStream(filename));
      Object result = input.readObject();
      input.close();
      return result;
    }
    catch(Exception e)
    {
      throw new RuntimeException(e);
    }
  }
}
