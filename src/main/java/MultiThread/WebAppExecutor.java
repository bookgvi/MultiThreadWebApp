package MultiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WebAppExecutor {
  WebAppExecutor() {
    ExecutorService executorService = Executors.newFixedThreadPool(Definitions.MAX_THREAD);
    WebAppTask task = new WebAppTask("");

    for (int i = 0; i < Definitions.MAX_THREAD; i++) {
      executorService.submit(task);
    }

    executorService.shutdown();
  }
}
