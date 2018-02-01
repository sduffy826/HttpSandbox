import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;

public class JavaHttpsExample {

	public static void main(String[] args) throws Exception {
		String address = "https://www.google.com";
		URL myUrl = new URL(address);
		HttpsURLConnection con = (HttpsURLConnection)myUrl.openConnection();
		InputStream iStream = con.getInputStream();
		InputStreamReader iStreamReader = new InputStreamReader(iStream);
        BufferedReader buffReader = new BufferedReader(iStreamReader);
        String inputLine;
        while ((inputLine = buffReader.readLine()) != null) {
        	System.out.println(inputLine);
        }
        buffReader.close();
	}

}
