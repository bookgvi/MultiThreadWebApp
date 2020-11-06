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
  private String response;
  private String relative_URL;

  WebAppTask(String relative_URL) {
    this.relative_URL = relative_URL;
  }

  public void run() {
    try {
      InputStream inputStream = this.request(Definitions.BASE_URL + relative_URL);
      if (inputStream != null) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
        JsonObject jsonObject = JsonParser.parseReader(buffer).getAsJsonObject();
        response = jsonObject.get("msg").getAsString();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    SimpleDateFormat sdf = new SimpleDateFormat();
    if (response != null)
      System.out.printf("Date/Time: %s; Task %s, response msg: %s%n", sdf.format(new Date()), Thread.currentThread().getName(), response);
  }

  private InputStream request(String url) throws IOException {
    URL createdURL = new URL(url);
    HttpURLConnection con = (HttpURLConnection) createdURL.openConnection();
    con.setRequestMethod("GET");
    if (con.getResponseCode() != Response.Status.OK.getStatusCode()) {
      SimpleDateFormat sdf = new SimpleDateFormat();
      System.out.printf("Date/Time: %s; Status Code: %s%n", sdf.format(new Date()), con.getResponseCode());
      return null;
    }
    return con.getInputStream();
  }
}
