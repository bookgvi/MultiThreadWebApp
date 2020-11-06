package MultiThread;

public class WebAppTask implements Runnable {
  public void run() {
    System.out.printf("Task %s%n", Thread.currentThread().getName());
  }
}
