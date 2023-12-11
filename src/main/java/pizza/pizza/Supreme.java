package pizza.pizza;

/**
 * Object class that handles the creation of supreme pizza
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class Supreme extends Pizza{

    /**
     * Parametrized constructor that initializes a supreme pizza with tomato sauce, pepperoni,
     * mushrooms, green peppers, onions, sausages, ham, and black olives
     *
     * @param size        the size of the pizza
     * @param extraSauce  whether the customer wanted extra sauce
     * @param extraCheese whether the customer wanted extra cheese
     */
    public Supreme(Size size, boolean extraSauce, boolean extraCheese) {
        super(size, Sauce.TOMATO, extraSauce, extraCheese, Toppings.GREEN_PEPPER,
                Toppings.PEPPERONI, Toppings.MUSHROOM, Toppings.ONION, Toppings.SAUSAGE,
                Toppings.BLACK_OLIVE, Toppings.HAM);
    }

    /**
     * Determines the price of a supreme pizza
     * @return the price of the pizza
     */
    @Override
    public double price() {
        final double small = 15.99;
        double price = 0.0;
        switch (size){
            case SMALL:
                price += small;
                break;
            case MEDIUM:
                price += small + 2;
                break;
            case LARGE:
                price += small + 4;
                break;
        }
        if (extraCheese)
            price++;
        if (extraSauce)
            price++;
        return price;
    }

    /**
     * Creates a String representation of this supreme pizza
     * @return a String representation of this supreme pizza
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("supreme,");
        sb.append(this.size.toString().toLowerCase()).append(",");
        if (extraCheese)
            sb.append("extra cheese,");
        if (extraSauce)
            sb.append("extra sauce,");
        return sb.toString();
    }

    /**
     * Checks if this supreme pizza is equal to another
     * @param o the supposed "supreme pizza" to check
     * @return true if the two "supreme pizzas" are equal, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof Supreme))
            return false;
        return super.equals(o);
    }
}
