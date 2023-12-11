package pizza.pizza;

/**
 * Object class that handles the creation of deluxe pizza
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class Deluxe extends Pizza{
    /**
     * Parametrized constructor that initializes a deluxe pizza with tomato sauce, sausages,
     * pepperoni, mushrooms, green peppers, and onions
     * @param size        the size of the pizza
     * @param extraSauce  whether the customer wanted extra sauce
     * @param extraCheese whether the customer wanted extra cheese
     */
    public Deluxe(Size size, boolean extraSauce, boolean extraCheese) {
        super(size, Sauce.TOMATO, extraSauce, extraCheese, Toppings.SAUSAGE,
                Toppings.PEPPERONI, Toppings.GREEN_PEPPER, Toppings.MUSHROOM, Toppings.ONION);
    }

    /**
     * Determines the price of a deluxe pizza
     * @return the price of the pizza
     */
    @Override
    public double price() {
        final double small = 14.99;
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
     * Creates a String representation of this deluxe pizza
     * @return a String representation of this deluxe pizza
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("deluxe,");
        sb.append(this.size.toString().toLowerCase()).append(",");
        if (extraCheese)
            sb.append("extra cheese,");
        if (extraSauce)
            sb.append("extra sauce,");
        return sb.toString();
    }

    /**
     * Checks if this deluxe pizza is equal to another
     * @param o the supposed "deluxe pizza" to check
     * @return true if the two "deluxe pizzas" are equal, false otherwise
     */
    public boolean equals(Object o){
        if (o == null || !(o instanceof Deluxe))
            return false;
        return super.equals(o);
    }
}
