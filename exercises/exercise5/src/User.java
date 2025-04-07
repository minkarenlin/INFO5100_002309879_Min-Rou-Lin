public class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(Order order) {
        System.out.println(name + " has been notified: " + order.getOrderType() + " status updated.");
    }
}
