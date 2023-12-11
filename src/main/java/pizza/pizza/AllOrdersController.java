package pizza.pizza;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.Optional;

/**
 * GUI for showing all the orders made so far
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class AllOrdersController {
    /**
     * The main controller
     */
    private MainController controller;
    /**
     * The list of orders
     */
    private ArrayList<Order> orders;
    /**
     * Displays the price of the order
     */
    @FXML private TextField priceField;
    /**
     * Combo Box for showing all the order numbers
     */
    @FXML private ComboBox<Integer> orderNumbers;
    /**
     * List View for displaying all the pizzas currently part of the order
     */
    @FXML private ListView<String> pizzaList;
    /**
     * Button for cancelling the currently-selected order
     */
    @FXML private Button cancel,
    /**
     * Button for exporting all orders to a GUI file
     */
    export;

    /**
     * Handles initializing the GUI menu, automatically called by the Main Controller
     */
    public void initialize(){
        ArrayList<Integer> ai = new ArrayList<Integer>();
        orders = AllOrders.getOrders();
        for (Order o : orders)
            ai.add(o.getOrderNumber());
        orderNumbers.setItems(FXCollections.observableArrayList(ai));
        orderNumbers.setValue(null);
        orderNumbers.getSelectionModel().selectedItemProperty().addListener(((observableValue,
                                                                              integer, t1) -> {
            cancel.setDisable(t1 == null);
        }));
    }

    /**
     * Sets the main controller
     * @param controller the main controller
     */
    public void setMainController(MainController controller){this.controller = controller;}

    /**
     * Displays an error alert
     * @param text the text for the alert to display
     */
    private void showAlert(String text){
        Alert a = new Alert(AlertType.ERROR);
        a.setContentText(text);
        a.setHeaderText("Error");
        a.showAndWait();
    }

    /**
     * Displays all the pizzas part of the order number, if one is selected
     */
    @FXML private void showOrder(){
        if (orderNumbers.getValue() == null)
            return;
        int x = orderNumbers.getValue();
        Order o = orders.get(0);
        for (Order r : orders)
            if (r.getOrderNumber() == x){
                o = r;
                break;
            }
        ArrayList<Pizza> ap = o.getAp();
        ObservableList<String> pizzas = pizzaList.getItems();
        pizzas.clear();
        for (Pizza p : o.getAp())
            pizzas.add(p.toString());
        pizzaList.setItems(pizzas);
        final double NJ_TAX_RATE = 0.06625;
        double subtotal = 0.0;
        for (Pizza p : ap)
            subtotal += p.price();
        double salesTax = NJ_TAX_RATE * subtotal, totalPrice = salesTax + subtotal;
        DecimalFormat df = new DecimalFormat("#.##");
        priceField.setText(df.format(totalPrice));
    }

    /**
     * Handles cancelling any order they wish
     */
    @FXML private void cancelOrder(){
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setHeaderText("Cancel Order");
        a.setContentText("Are you sure you wish to cancel this order?");
        a.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> res = a.showAndWait();
        if (res.isEmpty() || res.get() == ButtonType.NO || res.get() == ButtonType.CANCEL)
            return;
        int x = orderNumbers.getSelectionModel().getSelectedItem();
        for (Order r : orders)
            if (r.getOrderNumber() == x){
                orders.remove(r);
                break;
            }
        ObservableList<Integer> nums = orderNumbers.getItems();
        nums.clear();
        for (Order o : orders)
            nums.add(o.getOrderNumber());
        orderNumbers.setItems(nums);
        priceField.setText("");
        orderNumbers.setValue(null);
        pizzaList.getItems().clear();
        cancel.setDisable(true);
    }

    /**
     * Handles exporting all the orders to a file
     */
    @FXML private void exportToFile(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Set where to save and export your orders");
        ExtensionFilter ef = new ExtensionFilter("Text File", "*.txt");
        fc.getExtensionFilters().add(ef);
        Stage s = new Stage();
        File f = fc.showSaveDialog(s);
        PrintWriter out;
        try{
            out = new PrintWriter(new FileOutputStream(f), true);
        }catch (IOException e){
            showAlert("Error printing to file");
            return;
        }
        for (Order o : orders)
            out.print(o);
        out.close();
        export.setDisable(true);
        pizzaList.getItems().clear();
        orderNumbers.getItems().clear();
        priceField.setText("");
    }
}
