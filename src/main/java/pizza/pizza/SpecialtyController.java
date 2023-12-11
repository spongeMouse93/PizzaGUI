package pizza.pizza;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.util.Optional;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;

/**
 * Handles the GUI for making specialty pizzas
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class SpecialtyController {
    /**
     * Access to the main controller
     */
    private MainController mainController;

    /**
     * Text Field for displaying the price
     */
    @FXML private TextField priceField;

    /**
     * Radio Button for selecting small pizzas
     */
    @FXML private RadioButton small,
    /**
     * Radio Button for selecting medium pizzas
     */
    medium,
    /**
     * Radio Button for selecting small pizzas
     */
    large;

    /**
     * Button for placing order
     */
    @FXML private Button orderUp;

    /**
     * ToggleGroup for the size of the pizza
     */
    private ToggleGroup sizes = new ToggleGroup();

    /**
     * Check Box for adding extra cheese to the pizza
     */
    @FXML private CheckBox extraCheese,
    /**
     * Check Box for adding extra sauce to the pizza
     */
    extraSauce;

    /**
     * List View displaying the toppings associated with each specialty type pizza
     */
    @FXML private ListView<String> toppingsList;

    /**
     * Combo Box for displaying the types of specialty types: deluxe, supreme, pepperoni,
     * meatzza, and seafood
     */
    @FXML private ComboBox<String> specialtyTypes;

    /**
     * Label for displaying the sauce associated with each specialty type: alfredo for seafood
     * pizza and tomato for everything else
     */
    @FXML private Label sauceLabel;

    /**
     * Image View for displaying an image of a specialty pizza
     */
    @FXML private ImageView imageView;

    /**
     * Handles initializing the GUI menu, automatically called by the Main Controller
     */
    public void initialize(){
        ObservableList<String> specialties = FXCollections.observableArrayList("Deluxe", "Supreme", "Pepperoni", "Meatzza", "Seafood");
        specialtyTypes.setItems(specialties);
        small.setToggleGroup(sizes);
        medium.setToggleGroup(sizes);
        large.setToggleGroup(sizes);
        small.setSelected(true);
        extraCheese.setSelected(false);
        extraSauce.setSelected(false);
        sauceLabel.setText("xxx");
        specialtyTypes.setValue(null);
        extraCheese.setDisable(true);
        extraSauce.setDisable(true);
        small.setDisable(true);
        medium.setDisable(true);
        large.setDisable(true);
        orderUp.setDisable(true);
    }

    /**
     * Sets the main controller
     * @param controller the main controller
     */
    public void setMainController(MainController controller){mainController = controller;}

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
     * Handles setting the type of specialty pizza the user wishes to make
     */
    @FXML private void setType(){
        if (specialtyTypes.getValue() == null){
            extraCheese.setDisable(true);
            extraSauce.setDisable(true);
            small.setDisable(true);
            medium.setDisable(true);
            large.setDisable(true);
        }else {
            String type = switch (specialtyTypes.getValue()) {
                case "Deluxe" -> "deluxe";
                case "Supreme" -> "supreme";
                case "Seafood" -> "seafood";
                case "Meatzza" -> "meatzza";
                default -> "pepperoni";
            };
            try {
                Image i = new Image(new FileInputStream("src/main/resources/pizza/pizza/" +
                        type + ".png"));
                imageView.setImage(i);
            } catch (IOException e) {
                showAlert("There was an error in loading the image.");
                return;
            }
            ObservableList<String> toppings = toppingsList.getItems();
            toppings.clear();
            for (Toppings t : makePizza().getToppings())
                toppings.add(ToppingsToString.toppingToString(t));
            toppingsList.setItems(toppings);
            sauceLabel.setText(makePizza().getSauce().toString().toLowerCase());
            extraCheese.setDisable(false);
            extraSauce.setDisable(false);
            small.setDisable(false);
            medium.setDisable(false);
            large.setDisable(false);
            orderUp.setDisable(false);
            DecimalFormat df = new DecimalFormat("#.##");
            priceField.setText(df.format(makePizza().price()));
        }
    }

    /**
     * Makes a pizza depending on input data
     * @return the pizza depending on input data
     */
    private Pizza makePizza(){
        StringBuilder sb = new StringBuilder();
        sb.append(specialtyTypes.getValue().toLowerCase()).append(",");
        if (small.isSelected())
            sb.append("small,");
        else if (medium.isSelected())
            sb.append("medium,");
        else if (large.isSelected())
            sb.append("large,");
        if (extraCheese.isSelected())
            sb.append("extra cheese,");
        if (extraSauce.isSelected())
            sb.append("extra sauce,");
        return PizzaMaker.createPizza(sb.toString());
    }

    /**
     * Handles placing the order
     */
    @FXML private void placeOrder(){
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setHeaderText("Place Order?");
        a.setContentText("Are you sure you want to place order?");
        a.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> response = a.showAndWait();
        if (response.get() == ButtonType.NO || response.get() == ButtonType.CANCEL)
            return;
        Alert a2 = new Alert(AlertType.INFORMATION);
        a2.setHeaderText("Order Placed");
        a2.setContentText("Your order has been placed.");
        a2.showAndWait();
        giveToFile();
        specialtyTypes.setValue(null);
        extraCheese.setDisable(true);
        extraSauce.setDisable(true);
        small.setDisable(true);
        medium.setDisable(true);
        large.setDisable(true);
        orderUp.setDisable(true);
        sauceLabel.setText("xxx");
        ObservableList<String> list = toppingsList.getItems();
        list.clear();
        toppingsList.setItems(list);
        priceField.setText("");
        extraCheese.setSelected(false);
        extraSauce.setSelected(false);
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
        priceField.setText(df.format(p.price()));
    }

    /**
     * Handles changing the price based on extra cheese or sauce
     */
    @FXML private void changePriceBasedOnExtras(){
        Pizza p = makePizza();
        p.setExtraSauce(extraSauce.isSelected());
        p.setExtraCheese(extraCheese.isSelected());
        DecimalFormat df = new DecimalFormat("#.##");
        priceField.setText(df.format(p.price()));
    }

    /**
     * Prints the current pizza to a file for handling
     */
    private void giveToFile(){
        File f = new File("specialties.txt");
        try{
            PrintWriter out = new PrintWriter(new FileWriter(f, true), true);
            out.println(makePizza());
            out.close();
        }catch (Exception e){
            showAlert("An error occurred");
        }
    }
}
