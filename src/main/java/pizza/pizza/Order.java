package pizza.pizza;
import java.util.ArrayList;

/**
 * Handles showing all the pizzas part of the current order
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class Order {
    /**
     * The order number
     */
    private int orderNumber;

    /**
     * The list of pizzas included in this order
     */
    private ArrayList<Pizza> ap;

    /**
     * Parametrized constructor for a pizza order
     * @param orderNumber the order number
     * @param ap the list of pizzas part of the order
     */
    public Order(int orderNumber, ArrayList<Pizza> ap){
        this.orderNumber = orderNumber;
        this.ap = ap;
    }

    /**
     * Removes a pizza from the order
     * @param p the pizza to remove
     */
    public void removePizza(Pizza p){ap.remove(p);}

    /**
     * Retrieves the order number
     * @return the order number
     */
    public int getOrderNumber(){return orderNumber;}

    /**
     * Returns the list of pizzas
     * @return the list of pizzas
     */
    public ArrayList<Pizza> getAp(){return ap;}

    /**
     * Sets the list of pizzas
     * @param ap the list of pizzas
     */
    public void setAp(ArrayList<Pizza> ap){this.ap = ap;}

    /**
     * Gets the number of pizzas that are in the order
     * @return the number of pizzas in the order
     */
    public int getNumberOfPizzas(){return ap.size();}

    /**
     * Creates a String representation of this order to make the file easier to read
     * @return a String representation of this order
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order #" + orderNumber + ": \n");
        for (Pizza p : ap)
            sb.append(p).append("\n");
        sb.append("\n");
        return sb.toString();
    }
}
