GoogleReaderClient - a Google Reader Client in Java for Processing
==================================================================

Buildr
------

This project uses [Apache buildr](http://github.com/apache/buildr) to compile,
test, and distribute itself.  If you have a reasonable (Ruby 1.8.7+, recent
RubyGems) Ruby environment, you should be able to install buildr with:

    $ sudo env JAVA_HOME=$JAVA_HOME gem install buildr

You'll then be able to run tasks like `buildr compile`, `buildr test`, and
`buildr package`.  The last command creates a JAR file for you include in your
Processing project's `code/` directory.

Support Jars
------------

GoogleReaderClient requires several support JARs to work.  It relies heavily
upon the [Jackson JSON parser](http://jackson.codehaus.org/) and [Apache
HTTPClient 3.1](http://hc.apache.org/httpclient-3.x/).

Buildr will download these automatically to `$HOME/.m2/repository/`.  You'll 
need to find all JAR files in this directory and include them in your 
Processing project's `code/` directory manually.  Hopefully this can be 
cleaned up in a future release.

Usage
-----

    include com.creatingwithcode.greader.*;
    GoogleReaderClient grc = new GoogleReaderClient(username, password);
    RecentItemsFeed rif = grc.getRecentItemsFeed();
    for(FeedItem fi : rif.getItems()){
      // do something with the FeedItem
    }

