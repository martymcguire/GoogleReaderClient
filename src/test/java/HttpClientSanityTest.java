import junit.framework.TestCase;

import java.io.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpClientSanityTest extends TestCase {

  public void testFetchApacheOrg() {
    // Create an instance of HttpClient.
    HttpClient client = new HttpClient();

    // Create a method instance.
    GetMethod method = new GetMethod("http://www.apache.org/");
    
    // Provide custom retry handler is necessary
    method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
        new DefaultHttpMethodRetryHandler(3, false));

    try {
      // Execute the method.
      int statusCode = client.executeMethod(method);

      if (statusCode != HttpStatus.SC_OK) {
        System.err.println("Method failed: " + method.getStatusLine());
      }

      // Read the response body.
      String responseBody = method.getResponseBodyAsString();

      // Deal with the response.
      // Use caution: ensure correct character encoding and is not binary data
      assertTrue(responseBody.indexOf("Apache Software Foundation") > 0);

    } catch (HttpException e) {
      System.err.println("Fatal protocol violation: " + e.getMessage());
      e.printStackTrace();
      fail();
    } catch (IOException e) {
      System.err.println("Fatal transport error: " + e.getMessage());
      e.printStackTrace();
      fail();
    } finally {
      // Release the connection.
      method.releaseConnection();
    }  
  }
} 
