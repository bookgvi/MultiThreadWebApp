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
    System.out.printf("Task %s, response msg: %s%n", Thread.currentThread().getName(), response);
  }

  private InputStream request(String url) throws IOException {
    URL createdURL = new URL(url);
    HttpURLConnection con = (HttpURLConnection) createdURL.openConnection();
    con.setRequestMethod("GET");
    if (con.getResponseCode() != Response.Status.OK.getStatusCode()) return null;
    return con.getInputStream();
  }
}
