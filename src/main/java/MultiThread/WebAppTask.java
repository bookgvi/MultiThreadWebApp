package MultiThread;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebAppTask implements Runnable {
  private String responseMsg;
  private String relative_URL;
  private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.ms");
  private int responseCode;

  WebAppTask(String relative_URL) {
    if (!relative_URL.startsWith("/")) {
      this.relative_URL = "/" + relative_URL.toLowerCase();
    }
    this.relative_URL = relative_URL.toLowerCase();
  }

  public void run() {
    try {
      InputStream inputStream = this.request(Definitions.BASE_URL + relative_URL);
      if (inputStream != null) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
        JsonObject jsonObject = JsonParser.parseReader(buffer).getAsJsonObject();
        responseMsg = jsonObject.get("msg").getAsString();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (responseMsg != null)
      System.out.printf("[%s] Task %s, response msg: %s%n", sdf.format(new Date()), Thread.currentThread().getName(), responseMsg);
    else
      System.out.printf("[%s] Status Code: %s%n", sdf.format(new Date()), responseCode);

  }

  private InputStream request(String url) throws IOException {
    URL createdURL = new URL(url);
    HttpURLConnection con = (HttpURLConnection) createdURL.openConnection();
    con.setRequestMethod("GET");
    responseCode = con.getResponseCode();
    if (responseCode != Response.Status.OK.getStatusCode()) return null;
    return con.getInputStream();
  }
}
