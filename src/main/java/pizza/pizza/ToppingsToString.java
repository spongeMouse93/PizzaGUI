package pizza.pizza;

/**
 * A helper class to turn a topping name into a proper string and vice versa
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class ToppingsToString {
    /**
     * Converts each specific topping into its proper string literal
     * @param t the topping to "stringify"
     * @return the string version of the topping
     */
    public static String toppingToString(Toppings t){
        return switch (t) {
            case HAM -> "ham";
            case BEEF -> "beef";
            case ONION -> "onion";
            case SQUID -> "squid";
            case SHRIMP -> "shrimp";
            case SAUSAGE -> "sausage";
            case MUSHROOM -> "mushroom";
            case PEPPERONI -> "pepperoni";
            case CRAB_MEATS -> "crab meats";
            case BLACK_OLIVE -> "black olives";
            default -> "green peppers";
        };
    }

    /**
     * "Parses" a topping out of a string
     * @param t the string containing the topping
     * @return the topping once string is "destringified"
     */
    public static Toppings stringToToppings(String t){
        return switch (t.toLowerCase()) {
            case "ham" -> Toppings.HAM;
            case "beef" -> Toppings.BEEF;
            case "onion" -> Toppings.ONION;
            case "sausage" -> Toppings.SAUSAGE;
            case "mushroom" -> Toppings.MUSHROOM;
            case "black olives" -> Toppings.BLACK_OLIVE;
            case "pepperoni" -> Toppings.PEPPERONI;
            case "squid" -> Toppings.SQUID;
            case "crab meats" -> Toppings.CRAB_MEATS;
            case "shrimp" -> Toppings.SHRIMP;
            default -> Toppings.GREEN_PEPPER;
        };
    }
}
