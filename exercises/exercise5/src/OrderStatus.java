import java.util.*;

public class OrderStatus {
    private List<Observer> observers = new ArrayList<>();
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(order);
        }
    }
}
