import com.sun.org.apache.xml.internal.security.Init;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by joonas on 4.02.16.
 */
public class Menu implements Initializable {

    private Stage window;
    private Actions actions;
    private StackPane layout;
    private Parent root;
    private Scene scene;
    @FXML
    Button button = new Button();
    Label xSizeLabel = new Label();
    Label ySizeLabel = new Label();
    TextArea xSize = new TextArea();
    TextArea ySize = new TextArea();

    public Menu(Stage window, Actions actions){
        this.window = window;
        this.actions = actions;
    }

    public void getMenu(){
        window.setTitle("Aardejaht");
        try {
            root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            scene = new Scene(root, 300, 250);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("!!!Failed to load menu.fxml!!!");
        }
    }

    public void setActive(){
        window.setScene(scene);
        window.show();
    }

    public void startGame(){
        button.setText("OMG IT WORKS!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Menu initialized!");
    }
}
