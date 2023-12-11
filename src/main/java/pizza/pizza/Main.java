package pizza.pizza;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 * This is the main class for running the GUI
 * @author Siddharth Sircar
 * @author Yash Shah
 * @since November 6, 2023
 */
public class Main extends Application{
    /**
     * Creates a GUI for the main pizza menu
     * @param s the stage for it to take place
     * @throws Exception if anything happens
     */
    @Override
    public void start(Stage s) throws Exception {
        File f = new File("specialties.txt");
        try (FileWriter fw = new FileWriter(f)){}catch (IOException ignored){}
        f = new File("customs.txt");
        try (FileWriter fw = new FileWriter(f)){}catch (IOException ignored){}
        double width = 660, height = 400;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainFXML.fxml"));
        Scene sc = new Scene((AnchorPane) loader.load(), width, height);
        s.setScene(sc);
        s.setTitle("Pizza GUI");
        s.setResizable(false);
        s.show();
    }

    /**
     * The main method for running the GUI
     * @param args the command-line arguments
     */
    public static void main(String[] args){launch(args);}
}
