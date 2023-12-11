package pizza.pizza;
import java.util.StringTokenizer;
import java.util.ArrayList;
/**
 * A class used for making the pizzas
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class PizzaMaker {
    /**
     * A static method used for creating the pizzas
     * @param pizzaType the String detailing the type of pizza
     * @return the Pizza associated with the type
     */
    public static Pizza createPizza(String pizzaType){
        StringTokenizer st = new StringTokenizer(pizzaType, ",");
        ArrayList<String> as = new ArrayList<>();
        while (st.hasMoreTokens())
            as.add(st.nextToken());
        String type = as.get(0);
        Size si;
        if (as.get(1).equalsIgnoreCase("small"))
            si = Size.SMALL;
        else if (as.get(1).equalsIgnoreCase("medium"))
            si = Size.MEDIUM;
        else
            si = Size.LARGE;
        boolean extraCheese = as.contains("extra cheese"), extraSauce = as.contains("extra " +
                "sauce");
        if (type.equalsIgnoreCase("deluxe"))
            return new Deluxe(si, extraSauce, extraCheese);
        else if (type.equalsIgnoreCase("supreme"))
            return new Supreme(si, extraSauce, extraCheese);
        else if (type.equalsIgnoreCase("meatzza"))
            return new Meatzza(si, extraSauce, extraCheese);
        else if (type.equalsIgnoreCase("pepperoni"))
            return new Pepperoni(si, extraSauce, extraCheese);
        else if (type.equalsIgnoreCase("seafood"))
            return new Seafood(si, extraSauce, extraCheese);
        else{
            Sauce sa = as.contains("tomato") ? Sauce.TOMATO : Sauce.ALFREDO;
            Custom cu = new Custom(si, sa, extraSauce, extraCheese);
            int i = as.size() - 1;
            while (!as.get(i).equalsIgnoreCase("tomato") &&
                    !as.get(i).equalsIgnoreCase("alfredo")) {
                cu.addTopping(as.get(i));
                i--;
            }
            return cu;
        }
    }
}
