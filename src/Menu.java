import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by joonas on 4.02.16.
 */
public class Menu {

    final int MAX_X_SIZE = 10;
    final int MAX_Y_SIZE = 10;
    private Stage window;
    private Main main;
    private Scene scene;
    @FXML
    Button button = new Button();
    @FXML
    TextField xSize = new TextField();
    @FXML
    TextField ySize = new TextField();
    @FXML
    TextField treasureCount = new TextField();

    public Menu(Stage window, Main main){
        System.out.println("Menu initalized!");
        this.window = window;
        this.main = main;
    }

    public void setActive(){
        window.setTitle("Aardejaht");
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("menu.fxml"));
        fxml.setController(this);
        try {
            Parent root = fxml.load();
            scene = new Scene(root, 700, 500);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("!!!Failed to load menu.fxml!!!");
        }
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void startGame(){
        int xInt;
        int yInt;
        int treasureCountInt;
        try {
            xInt = Integer.parseInt(xSize.getText());
            yInt = Integer.parseInt(ySize.getText());
            treasureCountInt = Integer.parseInt(treasureCount.getText());
            main.getGame().setActive(xInt, yInt, treasureCountInt);
        }catch(NumberFormatException e){
            System.out.println("Wrong input!");
        }
    }

}
