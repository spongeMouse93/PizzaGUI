package pizza.pizza;

/**
 * A helper class made to generate the next available order number
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class NextAvailableOrderNumber {
    /**
     * The next available order number
     */
    private static int nextAvailableOrderNum = 1;

    /**
     * Retrieves the next available order number
     * @return the next available order number
     */
    public static int getNextAvailableOrderNum(){
        int temp = nextAvailableOrderNum;
        nextAvailableOrderNum++;
        return temp;
    }
}
