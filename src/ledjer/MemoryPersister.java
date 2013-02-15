package ledjer;

public class MemoryPersister implements Persister
{
  public Object savedObject;

  public void save(Object o)
  {
    savedObject = o;
  }

  public Object load()
  {
    return savedObject;
  }
}
