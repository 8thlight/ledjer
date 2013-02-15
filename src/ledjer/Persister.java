package ledjer;

public interface Persister
{
  void save(Object o);
  Object load();
}
