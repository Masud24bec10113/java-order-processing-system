import java.util.concurrent.BlockingQueue;

public class OrderProducer implements Runnable {
    private final BlockingQueue<Order> queue;
    private final int totalOrders;

    public OrderProducer(BlockingQueue<Order> queue, int totalOrders) {
        this.queue = queue;
        this.totalOrders = totalOrders;
    }

    @Override
    public void run() {
        String[] items = {"Laptop", "Mechanical Keyboard", "Monitor", "Headphones", "USB-C Hub"};
        try {
            for (int i = 1; i <= totalOrders; i++) {
                String item = items[i % items.length];
                double price = 25.0 + (i * 15.5);
                Order order = new Order(i, item, price);
                
                queue.put(order);
                System.out.println("[Producer] Generated: " + order);
                Thread.sleep(150);
            }
            queue.put(new Order(-1, "POISON_PILL", 0.0));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
