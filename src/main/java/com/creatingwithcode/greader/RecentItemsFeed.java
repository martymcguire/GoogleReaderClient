package com.creatingwithcode.greader;

import java.util.*;

public class RecentItemsFeed {
  protected String id;
  protected String author;
  protected String title;
  protected long updated;
  protected List<FeedItem> feedItems;

  public String getId() { return id; }
  public String getAuthor() { return author; }
  public String getTitle() { return title; }
  public long getUpdated() { return updated; }
  public List<FeedItem> getItems() { return feedItems; }

  public void setId(String _id) { id = _id; }
  public void setAuthor(String _author) { author = _author; }
  public void setTitle(String _title) { title = _title; }
  public void setUpdated(long _updated) { updated = _updated; }
  public void setItems(List<FeedItem> _feedItems) { 
    feedItems = _feedItems; 
  }

}
