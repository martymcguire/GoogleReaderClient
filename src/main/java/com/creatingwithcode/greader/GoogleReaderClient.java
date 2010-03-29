package com.creatingwithcode.greader;

import java.io.*;
import java.util.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.annotate.*;

public class GoogleReaderClient {
  protected String username;
  protected String password;
  protected String sid;

  protected HttpClient client;
  protected ObjectMapper mapper;

  protected int maxToFetch = 20;
  protected long lastFetch = 0;

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
    checkSID();
    String currTime = new Long(System.currentTimeMillis()/1000).toString();
    StringBuilder url = new StringBuilder("http://www.google.com/reader/api/0/stream/contents/user/-/state/com.google/reading-list");
    Map<String,String> q_opts = new HashMap<String,String>();
    q_opts.put("ot", new Long(lastFetch).toString());
    q_opts.put("r","n");
    q_opts.put("xt","user/-/state/com.google/read");
    q_opts.put("n", new Long(maxToFetch).toString());
    q_opts.put("ck", currTime);
    q_opts.put("client","RssInvaders");
    StringBuilder q = new StringBuilder();
    String sep = "";
    for(Map.Entry<String,String> entry : q_opts.entrySet()){
      q.append(sep); q.append(entry.getKey()); q.append(entry.getValue());
    }
    if(q.length() > 0){
      url.append("?"); url.append(q);
    }

    client = getHttpClient();
    GetMethod method = new GetMethod(url.toString());
    method.setRequestHeader("Cookie", "SID=" + sid);
    InputStream respStream = null;
    RecentItemsFeed rif = null;
    try {
      int statusCode = client.executeMethod(method);
      if (statusCode != HttpStatus.SC_OK) {
        throw new RuntimeException("Failed to retrieve feed: " + method.getStatusLine());
      }
      respStream = method.getResponseBodyAsStream();
      mapper = getObjectMapper();
      rif = mapper.readValue(respStream, RecentItemsFeed.class);
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving feed.", e);
    } finally {
      method.releaseConnection();
    }
    return rif;
  }

  protected HttpClient getHttpClient() {
    if(client == null) {
      client = new HttpClient();
    }
    return client;
  }

  protected ObjectMapper getObjectMapper() {
    if(mapper == null) {
      mapper = new ObjectMapper();
      mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                       false);
      mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }
    return mapper;
  }

  protected void checkSID() {
    if(sid == null) {
      if((username == null) || (password == null)){
        throw new RuntimeException("Cannot access Google Reader API without an SID or Username/Password combination.");
      } else {
        getSIDFromUsernameAndPass();
      }
    }
  }

  protected void getSIDFromUsernameAndPass(){
    String url = "https://www.google.com/accounts/ClientLogin?service=reader&Email=" + username + "&Passwd=" + password;
    client = getHttpClient();
    GetMethod method = new GetMethod(url);
    try {
      int statusCode = client.executeMethod(method);
      if (statusCode != HttpStatus.SC_OK) {
        throw new RuntimeException("Failed to retrieve SID: " + method.getStatusLine());
      }      
      String resp = method.getResponseBodyAsString();
      int indexSid = resp.indexOf("SID=") + 4;
      int indexLsid = resp.indexOf("LSID=");
      sid = resp.substring(indexSid, indexLsid);
      username = null; password = null;
    } catch (Exception e) {
      throw new RuntimeException("Error retrieving SID.", e);
    } finally {
      method.releaseConnection();
    }
  }
}
