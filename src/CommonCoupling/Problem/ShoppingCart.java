package CommonCoupling.Problem;

import java.util.List;

public class ShoppingCart {
    private List<Item> items;

    public void addItem(Item item) {
        items.add(item);
        Logger.log("Added item " + item.getName() + " to shopping cart.");
    }

    public void removeItem(Item item) {
        items.remove(item);
        Logger.log("Removed item " + item.getName() + " from shopping cart.");
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getPrice();
        }
        Logger.log("Calculated total price of items in shopping cart.");
        return total;
    }
}
