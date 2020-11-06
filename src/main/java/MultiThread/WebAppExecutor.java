package MultiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebAppExecutor {
  public WebAppExecutor() {
    ExecutorService executorService = Executors.newFixedThreadPool(Definitions.MAX_THREAD);
    WebAppTask task = new WebAppTask();
    List<WebAppTask> webAppTaskList = new ArrayList<WebAppTask>();

    for (int i = 0; i < Definitions.MAX_THREAD; i++) {
      executorService.submit(task);
    }

    executorService.shutdown();
  }
}
