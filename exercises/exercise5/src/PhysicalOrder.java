public class PhysicalOrder extends Order {
    public PhysicalOrder() {
        this.orderType = "Physical Order";
    }

    @Override
    public void processOrder() {
        System.out.println("Processing physical order");
    }
}
