package pizza.pizza;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Handles the GUI for looking at the current order
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class CurrentOrderController {
    /**
     * The main controller for the class
     */
    private MainController mainController;

    /**
     * Button for placing the order
     */
    @FXML private Button placeOrder,
    /**
     * Button for removing the pizza from the order
     */
    remove;

    /**
     * Displays all the pizzas that are part of the order
     */
    @FXML private ListView<String> orders;

    /**
     * Displays the subtotal (price of all the pizzas)
     */
    @FXML private TextField pizzaPrice,
    /**
     * Displays the sales tax amount
     */
    salesTaxAmount,
    /**
     * Displays the total price
     */
    totalPrice,
    /**
     * Displays the order number
     */
    orderNum;

    /**
     * List of pizzas part of the order
     */
    private ArrayList<Pizza> pizzaList;

    /**
     * The order containing all of this
     */
    private Order o;

    /**
     * Automatically called by the main controller to start the GUI
     */
    public void initialize(){
        pizzaList = new ArrayList<>();
        pizzaList.addAll(loadSpecialties());
        pizzaList.addAll(loadCustoms());
        o = new Order(NextAvailableOrderNumber.getNextAvailableOrderNum(), pizzaList);
        ObservableList<String> items = orders.getItems();
        for (Pizza p : pizzaList)
            items.add(p.toString());
        orders.setItems(items);
        orders.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1)
                -> {
            remove.setDisable(t1 == null);
        });
        displayMoneyAmounts();
        placeOrder.setDisable(false);
        orderNum.setText(Integer.toString(o.getOrderNumber()));
    }

    /**
     * Loads all information about all specialty pizzas ordered by the user
     * @return the list of specialty pizzas
     */
    private ArrayList<Pizza> loadSpecialties(){
        ArrayList<Pizza> ap = new ArrayList<>();
        Scanner sc;
        File f = new File("specialties.txt");
        if (!f.exists())
            return ap;
        try{
            sc = new Scanner(f, StandardCharsets.UTF_8);
            sc.useLocale(Locale.US);
        }catch (IOException e){
            return null;
        }
        while (sc.hasNextLine())
            ap.add(PizzaMaker.createPizza(sc.nextLine()));
        return ap;
    }

    /**
     * Loads all information about all custom-made pizzas ordered by the user
     * @return the list of custom-made pizzas
     */
    private ArrayList<Pizza> loadCustoms(){
        ArrayList<Pizza> ap = new ArrayList<>();
        Scanner sc;
        File f = new File("customs.txt");
        if (!f.exists())
            return ap;
        try{
            sc = new Scanner(f, StandardCharsets.UTF_8);
            sc.useLocale(Locale.US);
        }catch (IOException e){
            return null;
        }
        while (sc.hasNextLine())
            ap.add(PizzaMaker.createPizza(sc.nextLine()));
        return ap;
    }

    /**
     * Sets the main controller
     * @param controller the main controller
     */
    public void setMainController(MainController controller){mainController = controller;}

    /**
     * Exports this order to staff order GUI
     */
    @FXML private void export(){
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setHeaderText("Export Order?");
        a.setContentText("Are you sure you want to export this order?");
        a.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> response = a.showAndWait();
        if (response.isEmpty() || response.get() == ButtonType.NO || response.get() ==
                ButtonType.CANCEL)
            return;
        o.setAp(pizzaList);
        AllOrders.addOrder(o);
        Alert a2 = new Alert(AlertType.INFORMATION);
        a2.setHeaderText("Order Exported");
        a2.setContentText("Your order has been exported");
        a2.showAndWait();
        orders.getItems().clear();
        placeOrder.setDisable(true);
        pizzaPrice.setText("0.00");
        salesTaxAmount.setText("0.00");
        orderNum.setText("");
        totalPrice.setText("0.00");
        clearFiles();
    }

    /**
     * Clears the helper file contents to prevent them from being "carried over" into
     * the next order
     */
    private void clearFiles(){
        File f = new File("specialties.txt");
        try (FileWriter fw = new FileWriter(f)){}catch (IOException ignored){}
        f = new File("customs.txt");
        try (FileWriter fw = new FileWriter(f)){}catch (IOException ignored){}
    }

    /**
     * Handles removing the pizza from the order
     */
    @FXML private void removePizza(){
        String s = orders.getSelectionModel().getSelectedItem();
        if (s != null){
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setHeaderText("Delete Pizza?");
            a.setContentText("Are you sure you want to delete this pizza?");
            a.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> response = a.showAndWait();
            if (response.isEmpty() || response.get() == ButtonType.NO ||
                    response.get() == ButtonType.CANCEL)
                return;
            ObservableList<String> items = orders.getItems();
            items.remove(s);
            orders.setItems(items);
            remove.setDisable(true);
            Pizza temp = PizzaMaker.createPizza(s);
            pizzaList.remove(temp);
        }
    }

    /**
     * Calculates all necessary prices and displays them in their proper text fields
     */
    private void displayMoneyAmounts(){
        displayPizzaPrice();
        displaySalesTax();
        displayTotalPrice();
    }

    /**
     * Calculates the sales tax and displays it to the proper text field
     * <p><p>
     * The sales tax is the number of pizzas multiplied by the specified tax rate, which for
     * New Jersey, is 6.625%
     */
    private void displaySalesTax(){
        final double NJ_TAX_RATE = 0.06625;
        double subtotal = 0.0;
        for (Pizza p : pizzaList)
            subtotal += p.price();
        DecimalFormat df = new DecimalFormat("#.##");
        double salesTax = subtotal * NJ_TAX_RATE;
        salesTaxAmount.setText(df.format(salesTax));
    }

    /**
     * Calculates the subtotal, which is the price of all the pizzas combined, and displays
     * it to the proper text field
     */
    private void displayPizzaPrice(){
        double price = 0.0;
        for (Pizza p : pizzaList)
            price += p.price();
        DecimalFormat df = new DecimalFormat("#.##");
        pizzaPrice.setText(df.format(price));
    }

    /**
     * Calculates total price -> sales tax + subtotal
     */
    private void displayTotalPrice(){
        final double NJ_TAX_RATE = 0.06625;
        double salesTax = NJ_TAX_RATE * orders.getItems().size(), price = 0.0;
        for (Pizza p : pizzaList)
            price += p.price();
        DecimalFormat df = new DecimalFormat("#.##");
        totalPrice.setText(df.format(price + salesTax));
    }
}
