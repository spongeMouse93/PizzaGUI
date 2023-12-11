package pizza.pizza;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A general class used for making pizzas
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public abstract class Pizza {
    /**
     * Used to designated all possible pizza toppings
     */
    protected ArrayList<Toppings> toppings;
    /**
     * Used to designate the size of the pizza
     */
    protected Size size;
    /**
     * Used to designate the sauce on the pizza (usually tomato)
     */
    protected Sauce sauce;
    /**
     * Determines if customer wanted extra sauce on their pizza
     */
    protected boolean extraSauce,
    /**
     * Determines if customer wanted extra cheese on their pizza
     */
    extraCheese;

    /**
     * Parametrized constructor for making a general pizza
     * @param size the size of the pizza
     * @param sauce what kind of sauce is on the pizza
     * @param extraSauce whether the customer wanted extra sauce
     * @param extraCheese whether the customer wanted extra cheese
     * @param to the toppings on the pizza
     */
    public Pizza(Size size, Sauce sauce, boolean extraSauce, boolean extraCheese,
                 Toppings... to){
        this.size = size;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
        toppings = new ArrayList<>();
        toppings.addAll(Arrays.asList(to));
    }

    /**
     * General method for determining the price of the pizza
     * @return the price of the pizza
     */
    public abstract double price();

    /**
     * Creates a String representation of a pizza
     * @return a String representation of a pizza
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(size.toString().toLowerCase() + ",");
        if (extraCheese)
            sb.append("Extra Cheese,");
        if (extraSauce)
            sb.append("Extra Sauce,");
        sb.append(sauce.toString().toLowerCase()).append(",");
        for (Toppings t : toppings)
            sb.append(ToppingsToString.toppingToString(t)).append(",");
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * Accessor method for the pizza's toppings
     * @return the pizza's toppings
     */
    public ArrayList<Toppings> getToppings() {return toppings;}

    /**
     * Accessor method for the pizza's size
     * @return the pizza's size (small, medium or large)
     */
    public Size getSize() {return size;}

    /**
     * Accessor method for the pizza's sauce
     * @return the pizza's sauce (alfredo or tomato)
     */
    public Sauce getSauce() {return sauce;}

    /**
     * Mutator method for setting the size of the pizza
     * @param size the size of the pizza
     */
    public void setSize(Size size) {this.size = size;}

    /**
     * Mutator method for setting whether the pizza has extra sauce
     * @param extraSauce whether the pizza has extra sauce
     */
    public void setExtraSauce(boolean extraSauce){this.extraSauce = extraSauce;}

    /**
     * Mutator method for setting whether the pizza has extra cheese
     * @param extraCheese whether the pizza has extra cheese
     */
    public void setExtraCheese(boolean extraCheese){this.extraCheese = extraCheese;}

    /**
     * Adds a topping to the pizza depending on the value of the String
     * @param topping the string detailing the to-be-added topping
     */
    public void addTopping(String topping){
        if (topping == null)
            return;
        Toppings t = ToppingsToString.stringToToppings(topping);
        if (!toppings.contains(t))
            toppings.add(t);
    }

    /**
     * Checks if this pizza is equal to another
     * @param o the supposed "pizza" to check
     * @return true if the "pizzas" are the same, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof Pizza p))
            return false;
        return sauce.equals(p.sauce) && size.equals(p.size) && extraCheese == p.extraCheese
                && extraSauce == p.extraSauce && toppings.equals(p.toppings);
    }

    /**
     * This operation is not supported since pizzas are mutable
     * @return nothing
     * @throws UnsupportedOperationException if called
     */
    @Override
    public int hashCode() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
