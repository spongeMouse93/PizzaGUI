package pizza.pizza;

/**
 * Object class that handles the creation of pepperoni pizza
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class Pepperoni extends Pizza{

    /**
     * Parametrized constructor that initializes a pepperoni pizza with tomato sauce
     * @param size        the size of the pizza
     * @param extraSauce  whether the customer wanted extra sauce
     * @param extraCheese whether the customer wanted extra cheese
     */
    public Pepperoni(Size size, boolean extraSauce, boolean extraCheese) {
        super(size, Sauce.TOMATO, extraSauce, extraCheese, Toppings.PEPPERONI);
    }

    /**
     * Determines the price of a pepperoni pizza
     * @return the price of the pizza
     */
    @Override
    public double price() {
        final double small = 10.99;
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
     * Creates a String representation of this pepperoni pizza
     * @return a String representation of this pepperoni pizza
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("pepperoni,");
        sb.append(this.size.toString().toLowerCase()).append(",");
        if (extraCheese)
            sb.append("extra cheese,");
        if (extraSauce)
            sb.append("extra sauce,");
        return sb.toString();
    }

    /**
     * Checks if this pepperoni pizza is equal to another
     * @param o the supposed "pepperoni pizza" to check
     * @return true if the two "pepperoni pizzas" are equal, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof Pepperoni))
            return false;
        return super.equals(o);
    }
}
