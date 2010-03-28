package com.creatingwithcode.greader;

import java.io.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.annotate.*;

public class GoogleReaderClient {
  protected String username;
  protected String password;
  protected String sid;

  public String getUsername() { return username; }
  public String getPassword() { return password; }
  public String getSID() { return sid; }

  public GoogleReaderClient(String g_user, String g_pass) {
    username = g_user;
    password = g_pass;
  }

  public GoogleReaderClient(String _sid) {
    sid = _sid;
  }

  public RecentItemsFeed getRecentItemsFeed() {
    String currTime = new Long(System.currentTimeMillis()/1000).toString();
    String url = "http://www.google.com/reader/api/0/stream/contents/user/-/state/com.google/reading-list?ot=0&r=n&xt=user/-/state/com.google/read&n=20&ck=" + currTime + "&client=RssInvaders";
    HttpClient client = new HttpClient();
    GetMethod method = new GetMethod(url);
    method.setRequestHeader("Cookie", "SID=" + sid);
    InputStream respStream = null;
    RecentItemsFeed rif = null;
    try {
      int statusCode = client.executeMethod(method);
      if (statusCode != HttpStatus.SC_OK) {
        throw new RuntimeException("Failed to retrieve feed: " + method.getStatusLine());
      }
      respStream = method.getResponseBodyAsStream();
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                       false);
      rif = mapper.readValue(respStream, RecentItemsFeed.class);
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving feed.", e);
    } finally {
      method.releaseConnection();
    }
    return rif;
  }
}
