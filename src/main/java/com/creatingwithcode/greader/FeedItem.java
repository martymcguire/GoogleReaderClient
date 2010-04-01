package com.creatingwithcode.greader;

import java.util.List;
import java.util.Map;

public class FeedItem {
  protected String id;
  protected String title;
  protected Map<String, String> summary;
  protected Map<String, String> content;
  protected Map<String, String> origin;
  protected List<Map<String,String>> alternate;
  protected long published;
  protected String crawlTimeMsec;
  protected int likingUsersCount;

  public void setId(String _id) { id = _id; }
  public void setTitle(String _title) { title = _title; }
  public void setSummary(Map<String,String> _summary) {
    summary = _summary;
  }
  public void setContent(Map<String,String> _content) {
    content = _content;
  }
  public void setOrigin(Map<String,String> _origin) {
    origin = _origin;
  }
  public void setAlternate(List<Map<String,String>> _alternate) {
    alternate = _alternate;
  }
  public void setPublished(long _published) { published = _published; }
  public void setCrawlTimeMsec(String _crawlTimeMsec) { 
    crawlTimeMsec = _crawlTimeMsec; 
  }
  public void setLikingUsers(List<Map<String,String>> likingUsers) {
    likingUsersCount = likingUsers.size();
  }

  public String getId() { return id; }
  public String getTitle() { return title; }
  public Map<String, String> getSummary() { return summary; }
  public Map<String, String> getContent() { return content; }
  public Map<String, String> getOrigin() { return origin; }
  public List<Map<String, String>> getAlternate() { return alternate; }
  public long getPublished() { return published; }
  public String getCrawlTimeMsec() { return crawlTimeMsec; }
  public int likingUsersCount() { return likingUsersCount; }
}
