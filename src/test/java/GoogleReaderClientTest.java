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
    
  }

  public void testInitialization() {
    grc = grc_from_env_sid();
    assertNotNull(grc);
    assertEquals(g_user, grc.getUsername());
    assertEquals(g_pass, grc.getPassword());
    assertEquals(g_sid,  grc.getSID());
  }

  public void testGetRecentItemsFeed() {
    grc = grc_from_env_sid();
    RecentItemsFeed rif = grc.getRecentItemsFeed();
    assertNotNull(rif);
    assertNotNull(rif.getTitle());
    assertNotNull(rif.getItems());
    System.out.println("Title: " + rif.getTitle());
  }

  public void testLoginViaUserAndPassword() {
    grc = grc_from_env_user_and_pass();
    assertNull(grc.getSID());
    RecentItemsFeed rif = grc.getRecentItemsFeed();
    assertNotNull(rif);
    assertNotNull(rif.getTitle());
    assertNull(grc.getUsername());
    assertNull(grc.getPassword());
    assertNotNull(grc.getSID());
  }

  protected GoogleReaderClient grc_from_env_sid(){
    Map<String, String> env = System.getenv();
    g_sid = env.get("GOOGLE_READER_SID");
    if(g_sid == null){
      throw new RuntimeException("Please specify GOOGLE_READER_SID environment var to run this test.");
    }
    return (new GoogleReaderClient(g_sid));
  }

  protected GoogleReaderClient grc_from_env_user_and_pass() {
    Map<String, String> env = System.getenv();
    g_user = env.get("GOOGLE_READER_USER");
    g_pass = env.get("GOOGLE_READER_PASS");
    if((g_user == null) || (g_pass == null)) {
      throw new RuntimeException("Please specify GOOGLE_READER_USER and GOOGLE_READER_PASS environment vars to run this test.");
    }
    return (new GoogleReaderClient(g_user, g_pass));
  }
} 
