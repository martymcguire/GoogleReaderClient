import junit.framework.TestCase;

import com.creatingwithcode.greader.GoogleReaderClient;
import com.creatingwithcode.greader.RecentItemsFeed;
import java.util.Map;

public class GoogleReaderClientTest extends TestCase {
  String g_user = null;
  String g_pass = null;
  String g_sid  = null;
  GoogleReaderClient grc = null;

  public void setUp() {
    Map<String, String> env = System.getenv();
    //g_user = env.get("GOOGLE_USERNAME");
    //g_pass = env.get("GOOGLE_PASSWORD");
    //if((g_user == null) || (g_pass == null)){
    //  throw new RuntimeException("Please specify GOOGLE_USERNAME and GOOGLE_PASSWORD environment vars to run these tests.");
    //}
    //grc = new GoogleReaderClient(g_user, g_pass);
    g_sid = env.get("GOOGLE_READER_SID");
    if(g_sid == null){
      throw new RuntimeException("Please specify GOOGLE_READER_SID environment var to run these tests.");
    }
    grc = new GoogleReaderClient(g_sid);
  }

  public void testInitialization() {
    assertNotNull(grc);
    assertEquals(g_user, grc.getUsername());
    assertEquals(g_pass, grc.getPassword());
    assertEquals(g_sid,  grc.getSID());
  }

  public void testGetRecentItemsFeed() {
    RecentItemsFeed rif = grc.getRecentItemsFeed();
    assertNotNull(rif);
    assertNotNull(rif.getTitle());
    assertNotNull(rif.getItems());
    System.out.println("Title: " + rif.getTitle());
  }
} 
