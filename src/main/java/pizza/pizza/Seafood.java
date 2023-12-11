package pizza.pizza;

/**
 * Object class that handles the creation of seafood pizza
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class Seafood extends Pizza{

    /**
     * Parametrized constructor that initializes a seafood pizza with shrimp, squid, and
     * crab meats, with alfredo sauce
     * @param size the size of the pizza
     * @param extraSauce whether the customer wanted extra sauce
     * @param extraCheese whether the customer wanted extra cheese
     */
    public Seafood(Size size, boolean extraSauce, boolean extraCheese){
        super(size, Sauce.ALFREDO, extraSauce, extraCheese, Toppings.SHRIMP, Toppings.SQUID,
                Toppings.CRAB_MEATS);
    }

    /**
     * Determines the price of the seafood pizza
     * @return the price of the pizza
     */
    @Override
    public double price() {
        final double small = 17.99;
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
     * Creates a String representation of this seafood pizza
     * @return a String representation of this seafood pizza
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("seafood,");
        sb.append(this.size.toString().toLowerCase()).append(",");
        if (extraCheese)
            sb.append("extra cheese,");
        if (extraSauce)
            sb.append("extra sauce,");
        return sb.toString();
    }

    /**
     * Checks if this seafood pizza is equal to another
     * @param o the supposed "seafood pizza" to check
     * @return true if the two "seafood pizzas" are equal, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof Seafood))
            return false;
        return super.equals(o);
    }
}
