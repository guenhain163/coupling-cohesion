package CommonCoupling.Solution;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items;
    private LoggerInterface logger;

    public ShoppingCart(LoggerInterface logger) {
        this.items = new ArrayList<>();
        this.logger = logger;
    }

    public void addItem(Item item) {
        items.add(item);
        logger.log("Added item " + item.getName() + " to shopping cart.");
    }

    public void removeItem(Item item) {
        items.remove(item);
        logger.log("Removed item " + item.getName() + " from shopping cart.");
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getPrice();
        }
        logger.log("Calculated total price of items in shopping cart.");
        return total;
    }
}