import junit.framework.TestCase;

import com.creatingwithcode.greader.FeedItem;
import java.util.*;

public class FeedItemTest extends TestCase {

  FeedItem fi;
  Map<String,String> origin;
  Map<String,String> summary;
  List<Map<String,String>> alternate;
  List<Map<String,String>> likingUsers;

  public void setUp() {
    origin  = new HashMap<String,String>();
    origin.put("title", "Achewood");
    origin.put("htmlUrl", "http://www.achewood.com/");
    origin.put("streamId", "feed/http://achewood.com/rss.php");

    summary = new HashMap<String,String>();
    summary.put("content", "Achewood strip for Saturday, February 6, 2010");
    summary.put("direction", "ltr");

    alternate = new ArrayList<Map<String,String>>();
    Map<String,String> alt1 = new HashMap<String,String>();
    alt1.put("href", "http://www.achewood.com/index.php?date=02062010");
    alt1.put("type", "text/html");
    alternate.add(alt1);

    likingUsers = new ArrayList<Map<String,String>>();
    Map<String,String> user = new HashMap<String,String>();
    user.put("userId","10777762610824274422");
    likingUsers.add(user);
    user = new HashMap<String,String>();
    user.put("userId","09104676981019636646");
    likingUsers.add(user);
    user = new HashMap<String,String>();
    user.put("userId","16226429383649763733");
    likingUsers.add(user);
    user = new HashMap<String,String>();
    user.put("userId","10524677572570061861");
    likingUsers.add(user);
    user = new HashMap<String,String>();
    user.put("userId","06445544830765361104");
    likingUsers.add(user);
    user = new HashMap<String,String>();
    user.put("userId","11308277705536133199");
    likingUsers.add(user);

    fi = new FeedItem();
    fi.setId("tag:google.com,2005:reader/item/2ce169aee63c1caf");
    fi.setTitle("Doppleganger.");
    fi.setSummary(summary);
    fi.setOrigin(origin);
    fi.setAlternate(alternate);
    fi.setLikingUsers(likingUsers);
    fi.setPublished(1265443200);
    fi.setCrawlTimeMsec("1265448174873");
  }

  public void testInitialization() {
    assertEquals("tag:google.com,2005:reader/item/2ce169aee63c1caf", fi.getId());
    assertEquals("Doppleganger.", fi.getTitle());
    assertEquals(summary, fi.getSummary());
    assertEquals(origin, fi.getOrigin());
    assertEquals(alternate, fi.getAlternate());
    assertEquals(1265443200, fi.getPublished());
    assertEquals("1265448174873", fi.getCrawlTimeMsec());
    assertEquals(6, fi.likingUsersCount());
  }

} 
