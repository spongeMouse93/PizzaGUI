package pizza.pizza;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main controller for the main class
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class MainController {
    /**
     * Handles opening the specialty GUI
     * @throws Exception if anything happens
     */
    @FXML private void openSpecialtyGUI() throws Exception{
        Stage s = new Stage();
        final double width = 496, height = 596;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SpecialtyView.fxml"));
        AnchorPane root= (AnchorPane) loader.load();
        Scene scene = new Scene(root, width, height);
        s.setScene(scene);
        s.setTitle("Our Specialties");
        s.setResizable(false);
        s.show();
        SpecialtyController controller = loader.getController();
        controller.setMainController(this);
    }

    /**
     * Handles opening the current order GUI
     * @throws Exception if anything happens
     */
    @FXML private void openCurrentOrdersGUI() throws Exception{
        Stage s = new Stage();
        final double width = 639, height = 501;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentOrderView.fxml"));
        AnchorPane root= (AnchorPane) loader.load();
        Scene scene = new Scene(root, width, height);
        s.setScene(scene);
        s.setTitle("Your pizzas for this order");
        s.setResizable(false);
        s.show();
        CurrentOrderController controller = loader.getController();
        controller.setMainController(this);
    }

    /**
     * Handles opening the customizable pizza GUI
     * @throws Exception if anything happens
     */
    @FXML private void openCustomsGUI() throws Exception{
        Stage s = new Stage();
        final double width = 761, height = 551;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomView.fxml"));
        AnchorPane root= (AnchorPane) loader.load();
        Scene scene = new Scene(root, width, height);
        s.setScene(scene);
        s.setTitle("Build Your Own Pizza");
        s.setResizable(false);
        s.show();
        CustomController controller = loader.getController();
        controller.setMainController(this);
    }

    /**
     * Handles opening the all-orders GUI
     * @throws Exception if anything happens
     */
    @FXML private void openAllOrdersGUI() throws Exception{
        Stage s = new Stage();
        final double width = 600, height = 400;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllOrdersView.fxml"));
        AnchorPane root= (AnchorPane) loader.load();
        Scene scene = new Scene(root, width, height);
        s.setScene(scene);
        s.setTitle("All Orders Placed");
        s.setResizable(false);
        s.show();
        AllOrdersController controller = loader.getController();
        controller.setMainController(this);
    }
}
