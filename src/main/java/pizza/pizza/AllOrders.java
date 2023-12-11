package pizza.pizza;
import java.util.ArrayList;

/**
 * Class to keep track of all orders
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class AllOrders {
    /**
     * List of orders
     */
    private static ArrayList<Order> orders = new ArrayList<>();

    /**
     * Order number
     */
    private static int nextOrderNum = Integer.MAX_VALUE;

    /**
     * Statically adds order to order database
     * @param o the order to add
     */
    public static void addOrder(Order o){
        if (!orders.contains(o))
            orders.add(o);
        if (!orders.isEmpty())
            nextOrderNum = orders.get(0).getOrderNumber();
    }

    /**
     * Returns the list of orders
     * @return the list of orders
     */
    public static ArrayList<Order> getOrders(){return orders;}
}
