package MultiThread;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebAppTask implements Runnable {
  private String response;

  public void run() {
    try {
      InputStream inputStream = this.request(Definitions.BASE_URL);
      BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
      JsonObject jsonObject = JsonParser.parseReader(buffer).getAsJsonObject();
      response = jsonObject.get("msg").getAsString();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.printf("Task %s, response msg: %s%n", Thread.currentThread().getName(), response);
  }

  private InputStream request(String url) throws IOException {
    URL createdURL = new URL(url);
    HttpURLConnection con = (HttpURLConnection) createdURL.openConnection();
    con.setRequestMethod("GET");
    return con.getInputStream();
  }
}
