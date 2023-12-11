package pizza.pizza;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A JUnit Test class made for testing the price method for custom-made pizzas
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2024
 */
class CustomTest {

    /**
     * Tests the base price of each sized pizza - no toppings, no extra cheese, no extra
     * sauce
     */
    @Test
    @DisplayName("Testing Base Pizza Price With 0 Toppings And No Extra Cheese or Sauce")
    void testBasePrice() {
        double price1 = 9, price2 = 11, price3 = 13;
        Custom cu1 = new Custom(Size.SMALL, Sauce.TOMATO, false, false);
        Custom cu2 = new Custom(Size.MEDIUM, Sauce.TOMATO, false, false);
        Custom cu3 = new Custom(Size.LARGE, Sauce.TOMATO, false, false);
        assertEquals(Math.ceil(cu1.price()), price1);
        assertEquals(Math.ceil(cu2.price()), price2);
        assertEquals(Math.ceil(cu3.price()), price3);
    }

    /**
     * Tests the toppings price of each sized pizza - 7 toppings, but no extra cheese and
     * no extra sauce
     */
    @Test
    @DisplayName("Testing Pizza Price With Maximum 7 Toppings")
    void testMaxToppingsPrice(){
        double price1 = 15, price2 = 17, price3 = 19;
        Custom cu1 = new Custom(Size.SMALL, Sauce.TOMATO, false, false,
                Toppings.MUSHROOM, Toppings.ONION, Toppings.PEPPERONI, Toppings.SQUID,
                Toppings.CRAB_MEATS, Toppings.SHRIMP, Toppings.HAM);
        Custom cu2 = new Custom(Size.MEDIUM, Sauce.TOMATO, false, false,
                Toppings.MUSHROOM, Toppings.ONION, Toppings.PEPPERONI, Toppings.SQUID,
                Toppings.CRAB_MEATS, Toppings.SHRIMP, Toppings.HAM);
        Custom cu3 = new Custom(Size.LARGE, Sauce.TOMATO, false, false,
                Toppings.MUSHROOM, Toppings.ONION, Toppings.PEPPERONI, Toppings.SQUID,
                Toppings.CRAB_MEATS, Toppings.SHRIMP, Toppings.HAM);
        assertEquals(Math.ceil(cu1.price()), price1);
        assertEquals(Math.ceil(cu2.price()), price2);
        assertEquals(Math.ceil(cu3.price()), price3);
    }

    /**
     * Tests the extras price of each sized pizza - no toppings, but extra cheese and extra
     * sauce
     */
    @Test
    @DisplayName("Testing Pizza Price With No Toppings With Extra Cheese And Sauce")
    void testNoToppingsWithExtraCheeseAndSaucePrice(){
        double price1 = 11, price2 = 13, price3 = 15;
        Custom cu1 = new Custom(Size.SMALL, Sauce.TOMATO, true, true);
        Custom cu2 = new Custom(Size.MEDIUM, Sauce.TOMATO, true, true);
        Custom cu3 = new Custom(Size.LARGE, Sauce.TOMATO, true, true);
        assertEquals(Math.ceil(cu1.price()), price1);
        assertEquals(Math.ceil(cu2.price()), price2);
        assertEquals(Math.ceil(cu3.price()), price3);
    }

    /**
     * Tests the max price of each sized pizza - 7 toppings, extra cheese, and extra
     * sauce
     */
    @Test
    @DisplayName("Testing Max Price: 7 Toppings With Extra Cheese And Sauce")
    void testMaxPrice(){
        double price1 = 17, price2 = 19, price3 = 21;
        Custom cu1 = new Custom(Size.SMALL, Sauce.TOMATO, true, true,
                Toppings.MUSHROOM, Toppings.ONION, Toppings.PEPPERONI, Toppings.SQUID,
                Toppings.CRAB_MEATS, Toppings.SHRIMP, Toppings.HAM);
        Custom cu2 = new Custom(Size.MEDIUM, Sauce.TOMATO, true, true,
                Toppings.MUSHROOM, Toppings.ONION, Toppings.PEPPERONI, Toppings.SQUID,
                Toppings.CRAB_MEATS, Toppings.SHRIMP, Toppings.HAM);
        Custom cu3 = new Custom(Size.LARGE, Sauce.TOMATO, true, true,
                Toppings.MUSHROOM, Toppings.ONION, Toppings.PEPPERONI, Toppings.SQUID,
                Toppings.CRAB_MEATS, Toppings.SHRIMP, Toppings.HAM);
        assertEquals(Math.ceil(cu1.price()), price1);
        assertEquals(Math.ceil(cu2.price()), price2);
        assertEquals(Math.ceil(cu3.price()), price3);
    }

    /**
     * Tests the price method for randomly-made pizzas
     */
    @Test
    @DisplayName("Testing Prices For Randomly-Made Custom-Made Pizzas")
    void testRandomPrice(){
        double price1 = 10, price2 = 14, price3 = 19;
        Custom cu1 = new Custom(Size.SMALL, Sauce.TOMATO, false, true,
                Toppings.MUSHROOM, Toppings.ONION, Toppings.PEPPERONI);
        Custom cu2 = new Custom(Size.MEDIUM, Sauce.TOMATO, true, false,
                Toppings.MUSHROOM, Toppings.ONION, Toppings.PEPPERONI, Toppings.SQUID);
        Custom cu3 = new Custom(Size.LARGE, Sauce.TOMATO, false, true,
                Toppings.MUSHROOM, Toppings.ONION, Toppings.PEPPERONI, Toppings.SQUID,
                Toppings.CRAB_MEATS, Toppings.SHRIMP);
        assertEquals(Math.ceil(cu1.price()), price1);
        assertEquals(Math.ceil(cu2.price()), price2);
        assertEquals(Math.ceil(cu3.price()), price3);
    }
}
