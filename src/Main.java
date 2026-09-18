import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Multi-Threaded Order Processing Engine ===");

        File logDir = new File("logs");
        if (!logDir.exists()) logDir.mkdir();

        String logPath = "logs/order_log.txt";
        BlockingQueue<Order> queue = new ArrayBlockingQueue<>(10);
        int totalOrders = 15;

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new OrderProducer(queue, totalOrders));
        executor.submit(new OrderConsumer(queue, logPath));
        executor.submit(new OrderConsumer(queue, logPath));

        executor.shutdown();
        try {
            if (executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("=== All orders successfully processed and logged to " + logPath + " ===");
            } else {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
