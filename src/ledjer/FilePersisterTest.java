package ledjer;

import org.junit.After;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;

public class FilePersisterTest
{
  private static String filename = "/tmp/file_persister_test_file";

  @After
  public void tearDown() throws Exception
  {
    new File(filename).delete();
  }

  @Test
  public void savesToSpecifiedFile() throws Exception
  {
    FilePersister persister = new FilePersister(filename);
    persister.save("My Object");
    assertEquals(true, new File(filename).exists());
  }

  @Test
  public void loadsFromSpecifiedFile() throws Exception
  {
    FilePersister persister = new FilePersister(filename);
    persister.save("My Object");
    assertEquals("My Object", persister.load());
  }
}
