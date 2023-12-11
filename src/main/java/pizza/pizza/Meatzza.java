package pizza.pizza;

/**
 * Object class that handles the creation of meatzza
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class Meatzza extends Pizza{

    /**
     * Parametrized constructor that initializes a meatzza with tomato sauce, pepperoni, ham,
     * sausage, and beef
     * @param size        the size of the pizza
     * @param extraSauce  whether the customer wanted extra sauce
     * @param extraCheese whether the customer wanted extra cheese
     */
    public Meatzza(Size size, boolean extraSauce, boolean extraCheese) {
        super(size, Sauce.TOMATO, extraSauce, extraCheese, Toppings.PEPPERONI,
                Toppings.BEEF, Toppings.SAUSAGE, Toppings.HAM);
    }

    /**
     * Determines the price of a meatzza
     * @return the price of the pizza
     */
    @Override
    public double price() {
        final double small = 16.99;
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
     * Creates a String representation of this meatzza
     * @return a String representation of this meatzza
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("meatzza,");
        sb.append(this.size.toString().toLowerCase()).append(",");
        if (extraCheese)
            sb.append("extra cheese,");
        if (extraSauce)
            sb.append("extra sauce,");
        return sb.toString();
    }

    /**
     * Checks if this meatzza is equal to another
     * @param o the supposed "meatzza" to check
     * @return true if the two "meatzzas" are equal, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof Meatzza))
            return false;
        return super.equals(o);
    }
}
