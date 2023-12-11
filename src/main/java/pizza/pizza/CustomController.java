package pizza.pizza;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.Optional;
import java.text.DecimalFormat;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the GUI for making custom-made pizzas
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class CustomController {
    /**
     * Access to the main controller
     */
    private MainController mainController;
    /**
     * Check Box for adding shrimp as a topping
     */
    @FXML private CheckBox shrimp = new CheckBox("Shrimp"),
    /**
     * Check Box for adding crab meats as a topping
     */
    crabMeats = new CheckBox("Crab Meats"),
    /**
     * Check Box for adding pepperoni as a topping
     */
    pepperoni = new CheckBox("Pepperoni"),
    /**
     * Check Box for adding sausage as a topping
     */
    sausage = new CheckBox("Sausage"),
    /**
     * Check Box for adding ham as a topping
     */
    ham = new CheckBox("Ham"),
    /**
     * Check Box for adding beef as a topping
     */
    beef = new CheckBox("Beef"),
    /**
     * Check Box for adding green peppers as a topping
     */
    greenPeppers = new CheckBox("Green Peppers"),
    /**
     * Check Box for adding black olives as a topping
     */
    blackOlives = new CheckBox("Black Olives"),
    /**
     * Check Box for adding onions as a topping
     */
    onion = new CheckBox("Onion"),
    /**
     * Check Box for adding mushrooms as a topping
     */
    mushroom = new CheckBox("Mushroom"),
    /**
     * Check Box for adding squid as a topping
     */
    squid = new CheckBox("Squid"),
    /**
     * Check Box for adding extra cheese to the pizza
     */
    extraCheese,
    /**
     * Check Box for adding extra sauce to the pizza
     */
    extraSauce;

    /**
     * Radio Button for selecting small pizzas
     */
    @FXML private RadioButton small,
    /**
     * Radio Button for selecting medium pizzas
     */
    medium,
    /**
     * Radio Button for selecting large pizzas
     */
    large,
    /**
     * Radio Button for selecting alfredo sauce
     */
    alfredo,
    /**
     * Radio Button for selecting tomato sauce
     */
    tomato;

    /**
     * Text Field for displaying the price
     */
    @FXML private TextField tf;

    /**
     * List View for displaying selected toppings
     */
    @FXML private ListView<String> selectedToppings;

    /**
     * ToggleGroup for the size of the pizza
     */
    private ToggleGroup sizes = new ToggleGroup(),
    /**
     * ToggleGroup for the pizza sauce
     */
    sauces = new ToggleGroup();

    /**
     * Sets the main controller
     * @param controller the main controller
     */
    public void setMainController(MainController controller){mainController = controller;}

    /**
     * Handles initializing the GUI menu, automatically called by the Main Controller
     */
    public void initialize(){
        deselectToppings();
        small.setToggleGroup(sizes);
        large.setToggleGroup(sizes);
        medium.setToggleGroup(sizes);
        alfredo.setToggleGroup(sauces);
        tomato.setToggleGroup(sauces);

    }

    /**
     * Displays an error alert
     * @param text the text for the alert to display
     */
    private void showAlert(String text){
        Alert a = new Alert(AlertType.ERROR);
        a.setHeaderText("Error");
        a.setContentText(text);
        a.showAndWait();
    }

    /**
     * Makes a pizza depending on input data
     * @return the pizza depending on input data
     */
    private Pizza makePizza(){
        StringBuilder sb = new StringBuilder("custom,");
        ObservableList<String> items = selectedToppings.getItems();
        if (small.isSelected())
            sb.append("small,");
        else if (medium.isSelected())
            sb.append("medium,");
        else
            sb.append("large,");
        if (extraCheese.isSelected())
            sb.append("extra cheese,");
        if (extraSauce.isSelected())
            sb.append("extra sauce,");
        if (alfredo.isSelected())
            sb.append("alfredo,");
        else
            sb.append("tomato,");
        for (String s : items)
            sb.append(s).append(",");
        return PizzaMaker.createPizza(sb.toString());
    }

    /**
     * Places a pizza order once customer is set and has inputted all necessary data as they
     * see fit
     */
    @FXML private void placeOrder(){
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setHeaderText("Place Order?");
        a.setContentText("Are you sure you want to place order?");
        a.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> response = a.showAndWait();
        if (response.isEmpty() || response.get() == ButtonType.NO || response.get() ==
                ButtonType.CANCEL)
            return;
        Alert a2 = new Alert(AlertType.INFORMATION);
        a2.setHeaderText("Order Placed");
        a2.setContentText("Your order has been placed.");
        a2.showAndWait();
        giveToFile();
        tf.setText("0.00");
        deselectToppings();
        ObservableList<String> items = selectedToppings.getItems();
        items.clear();
        selectedToppings.setItems(items);
    }

    /**
     * Deselects all the toppings
     */
    private void deselectToppings(){
        shrimp.setSelected(false);
        crabMeats.setSelected(false);
        greenPeppers.setSelected(false);
        blackOlives.setSelected(false);
        squid.setSelected(false);
        pepperoni.setSelected(false);
        ham.setSelected(false);
        beef.setSelected(false);
        onion.setSelected(false);
        mushroom.setSelected(false);
        sausage.setSelected(false);
        extraCheese.setSelected(false);
        extraSauce.setSelected(false);
    }

    /**
     * Handles altering the toppings when a {@link javafx.scene.control.CheckBox} is
     * selected or deselected
     * @param e the Event handling the effects
     */
    @FXML private void alterToppings(ActionEvent e){
        if (e.getSource() == shrimp)
            handleToppingChange(shrimp);
        else if (e.getSource() == beef)
            handleToppingChange(beef);
        else if (e.getSource() == ham)
            handleToppingChange(ham);
        else if (e.getSource() == crabMeats)
            handleToppingChange(crabMeats);
        else if (e.getSource() == squid)
            handleToppingChange(squid);
        else if (e.getSource() == pepperoni)
            handleToppingChange(pepperoni);
        else if (e.getSource() == greenPeppers)
            handleToppingChange(greenPeppers);
        else if (e.getSource() == onion)
            handleToppingChange(onion);
        else if (e.getSource() == mushroom)
            handleToppingChange(mushroom);
        else if (e.getSource() == sausage)
            handleToppingChange(sausage);
        else if (e.getSource() == blackOlives)
            handleToppingChange(blackOlives);
    }

    /**
     * Handles changing the topping to obey the coding standard
     * @param topping the topping to add or remove from the list-view
     */
    private void handleToppingChange(CheckBox topping){
        if (topping.isSelected())
            addToppings(topping);
        else
            removeToppings(topping.getText());
    }

    /**
     * Handles adding the specified topping to the list view
     * @param toppingName the topping to add
     */
    private void removeToppings(String toppingName){
        ObservableList<String> tops = selectedToppings.getItems();
        tops.remove(toppingName.toLowerCase());
        selectedToppings.setItems(tops);
        DecimalFormat df = new DecimalFormat("#.##");
        tf.setText(df.format(makePizza().price()));
    }

    /**
     * Handles removing the specified topping from the list view
     * @param cb the CheckBox containing the topping name
     */
    private void addToppings(CheckBox cb){
        final int MAX_TOPPINGS_ALLOWED = 7;
        ObservableList<String> tops = selectedToppings.getItems();
        if (tops.size() + 1 > MAX_TOPPINGS_ALLOWED){
            showAlert("Maximum of 7 toppings allowed on custom pizza");
            cb.setSelected(false);
            return;
        }
        if (!tops.contains(cb.getText().toLowerCase()))
            tops.add(cb.getText().toLowerCase());
        selectedToppings.setItems(tops);
        DecimalFormat df = new DecimalFormat("#.##");
        tf.setText(df.format(makePizza().price()));
    }

    /**
     * Handles changing the price based on the size of the pizza
     * @param e the Event from where the Radio Buttons are from
     */
    @FXML private void changePriceBasedOnSize(ActionEvent e){
        Pizza p = makePizza();
        if (e.getSource() == small)
            p.setSize(Size.SMALL);
        else if (e.getSource() == medium)
            p.setSize(Size.MEDIUM);
        else
            p.setSize(Size.LARGE);
        DecimalFormat df = new DecimalFormat("#.##");
        tf.setText(df.format(p.price()));
    }

    /**
     * Handles changing the price based on extra cheese or sauce
     */
    @FXML private void changePriceBasedOnExtras(){
        Pizza p = makePizza();
        p.setExtraSauce(extraSauce.isSelected());
        p.setExtraCheese(extraCheese.isSelected());
        DecimalFormat df = new DecimalFormat("#.##");
        tf.setText(df.format(p.price()));
    }

    /**
     * Prints the current pizza to a file for the Order Controller class to handle
     */
    private void giveToFile(){
        File f = new File("customs.txt");
        try{
            PrintWriter out = new PrintWriter(new FileWriter(f, true), true);
            out.println(makePizza());
            out.close();
        }catch (Exception e){
            showAlert("An error occurred");
        }
    }
}
