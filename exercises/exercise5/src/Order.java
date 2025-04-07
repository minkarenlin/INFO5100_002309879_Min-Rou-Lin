public abstract class Order {
    protected String orderType;

    public String getOrderType() {
        return orderType;
    }

    public abstract void processOrder();
}
