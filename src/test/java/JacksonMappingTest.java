import junit.framework.TestCase;

import com.creatingwithcode.greader.RecentItemsFeed;
import com.creatingwithcode.greader.FeedItem;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.DeserializationConfig;
import java.util.*;
import java.io.*;

public class JacksonMappingTest extends TestCase {

  public void testMapSomeFeedItems() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                       false);
      RecentItemsFeed rif = mapper.readValue(new File("/Users/rmcguire/Desktop/feed_data.json"), RecentItemsFeed.class);
      assertNotNull(rif);
      List<FeedItem> items = rif.getItems();
      assertNotNull(items);
      assertEquals("Doppelganger.", items.get(0).getTitle());
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }
} 
