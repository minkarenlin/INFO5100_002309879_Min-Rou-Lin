public class OrderFactory {
    public static Order createOrder(String type) {
        if (type.equalsIgnoreCase("physical")) {
            return new PhysicalOrder();
        } else if (type.equalsIgnoreCase("online")) {
            return new OnlineOrder();  
        } else {
            throw new IllegalArgumentException("Unknown order type");
        }
    }
}
