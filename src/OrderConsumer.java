import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class OrderConsumer implements Runnable {
    private final BlockingQueue<Order> queue;
    private final String logFilePath;

    public OrderConsumer(BlockingQueue<Order> queue, String logFilePath) {
        this.queue = queue;
        this.logFilePath = logFilePath;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            while (true) {
                Order order = queue.take();
                
                if (order.getId() == -1) {
                    queue.put(order);
                    break;
                }

                Thread.sleep(200);
                order.setStatus("PROCESSED");
                
                String record = String.format("Processed: Order #%d | Item: %s | Total: $%.2f%n",
                                              order.getId(), order.getItem(), order.getAmount());
                System.out.print("[Consumer " + Thread.currentThread().getName() + "] " + record);
                
                synchronized (writer) {
                    writer.write(record);
                    writer.flush();
                }
            }
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
        }
    }
}
