
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

public class HttpPost {
  private final boolean DEBUGIT = false;
  private final String USER_AGENT = "Mozilla/5.0";

  public static void main(String[] args) throws Exception {
    HttpPost http = new HttpPost();
    System.out.println("Testing - Send Http POST request");
    http.sendPost();
  }

  // HTTP POST request
  private void sendPost() throws Exception {
    final String FILENAME = "HttpSandbox_HttpPost.txt";
    String url = "https://selfsolve.apple.com/wcResults.do";
    // url = "https://www.amazon.com/s/browse/ref=gbps_img_s-3_e9fb_8bf7b34f";

    URL obj = new URL(url);
    HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

    // add request header
    con.setRequestMethod("POST");
    con.setRequestProperty("User-Agent", USER_AGENT);
    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

    String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
    // urlParameters = "ie=UTF8&node=15676227011&smid=ATVPDKIKX0DER&pf_rd_p=4a61b383-8767-4471-a83a-ab3d2bd4e9fb&pf_rd_s=slot-3&pf_rd_t=701&pf_rd_i=gb_main&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=JFXYS62R8SS62EEYTTJK";

    // Send post request
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes(urlParameters);
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();
    System.out.println("\nSending 'POST' request to URL : " + url);
    System.out.println("Post parameters : " + urlParameters);
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
    }
    in.close();
    bufferedWriter.flush();
    bufferedWriter.close();

    // print result
    if (DEBUGIT) System.out.println(response.toString());
  }
}