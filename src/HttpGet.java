
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

public class HttpGet {
  private static final boolean DEBUGIT = false;
  private final String USER_AGENT = "Mozilla/5.0";

  public static void main(String[] args) throws Exception {
    HttpGet http = new HttpGet();
    System.out.println("Send Http GET request");
    http.sendGet();
  }

  // HTTP GET request
  private void sendGet() throws Exception {
    final String FILENAME = "HttpSandbox_HttpGet.txt";
    String url = "http://www.google.com/search?q=inserra";

    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    // optional default is GET
    con.setRequestMethod("GET");

    // add request header
    con.setRequestProperty("User-Agent", USER_AGENT);

    int responseCode = con.getResponseCode();
    System.out.println("Sending 'GET' request to URL : " + url);
    System.out.println("Response Code : " + responseCode);

    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILENAME)); 
    
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    int numLines = 0;
    
    while ((inputLine = in.readLine()) != null) {
      System.out.println((numLines != 0 ? "  " : "") + inputLine);
      if (DEBUGIT) TimeUnit.SECONDS.sleep((numLines > 7 ? 2 : 1));
      response.append(inputLine);
      
      bufferedWriter.write(inputLine);
      
      numLines++;
    }
    in.close();
    bufferedWriter.flush();
    bufferedWriter.close();
    
    // print result
    if (DEBUGIT) System.out.println("Lines: " + numLines + " " + response.toString());

  }
}