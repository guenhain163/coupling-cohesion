package CommonCoupling;


import CommonCoupling.Solution.FileLogger;
import CommonCoupling.Solution.Item;
import CommonCoupling.Solution.LoggerInterface;
import CommonCoupling.Solution.ShoppingCart;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File logFile = new File("log.txt");
        LoggerInterface logger = new FileLogger(logFile);
        ShoppingCart cart = new ShoppingCart(logger);
        cart.addItem(new Item("Shoes", 100.0));
        cart.addItem(new Item("Shirt", 50.0));
        cart.addItem(new Item("Pants", 75.0));
        cart.addItem(new Item("Hat", 25.0));
        cart.removeItem(new Item("Shoes", 100.0));
        System.out.println("Total: " + cart.calculateTotal());
    }
}
