package pizza.pizza;

/**
 * Object class for handling custom-made pizza
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class Custom extends Pizza{
    /**
     * Parametrized constructor that initializes a custom-made pizza
     * @param size        the size of the pizza
     * @param sauce       what kind of sauce is on the pizza
     * @param extraSauce  whether the customer wanted extra sauce
     * @param extraCheese whether the customer wanted extra cheese
     * @param to          the toppings on the pizza
     */
    public Custom(Size size, Sauce sauce, boolean extraSauce, boolean extraCheese,
                  Toppings... to) {
        super(size, sauce, extraSauce, extraCheese, to);
    }

    /**
     * Determines the price of this custom-made pizza
     * @return the price of the pizza
     */
    @Override
    public double price() {
        double price;
        int minToppings = 3;
        price = switch (size) {
            case SMALL -> 8.99;
            case MEDIUM -> 10.99;
            case LARGE -> 12.99;
        };
        if (toppings.size() > minToppings)
            price += 1.49 * (toppings.size() - minToppings);
        if (extraCheese)
            price++;
        if (extraSauce)
            price++;
        return price;
    }

    /**
     * Creates a String representation of this custom-made pizza
     * @return a String representation of this custom-made pizza
     */
    @Override
    public String toString() {
        return "custom," + super.toString();
    }

    /**
     * Checks if this custom pizza is equal to another
     * @param o the supposed "custom pizza" to check
     * @return true if the two "custom pizzas" are equal, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof Custom))
            return false;
        return super.equals(o);
    }
}
