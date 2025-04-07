public class DesignPatternExample {
    public static void main(String[] args) {
        // Singleton pattern: OrderManager
        OrderManager orderManager = OrderManager.getInstance();

        // Factory Method: Create different types of orders
        Order physicalOrder = OrderFactory.createOrder("physical");
        Order onlineOrder = OrderFactory.createOrder("online");  


        orderManager.addOrder(physicalOrder);
        orderManager.addOrder(onlineOrder);

   
        User user1 = new User("Amy");
        User user2 = new User("Karen");

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.addObserver(user1);
        orderStatus.addObserver(user2);

        // Update order status and notify users
        orderStatus.setOrder(physicalOrder);
        physicalOrder.processOrder();

        orderStatus.setOrder(onlineOrder);  
        onlineOrder.processOrder();
    }
}
