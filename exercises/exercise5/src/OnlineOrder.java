public class OnlineOrder extends Order {
    public OnlineOrder() {
        this.orderType = "Online Order";
    }

    @Override
    public void processOrder() {
        System.out.println("Processing online order");
    }
}
