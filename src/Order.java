public class Order {
    private final int id;
    private final String item;
    private final double amount;
    private String status;

    public Order(int id, String item, double amount) {
        this.id = id;
        this.item = item;
        this.amount = amount;
        this.status = "PENDING";
    }

    public int getId() { return id; }
    public String getItem() { return item; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Order[ID=%d, Item='%s', Amount=$%.2f, Status=%s]", 
                             id, item, amount, status);
    }
}
