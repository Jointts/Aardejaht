import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by joonas on 4.02.16.
 */
public class Game {

    private Stage window;
    private Actions actions;
    Button button = new Button();
    StackPane layout = new StackPane();
    Scene scene = new Scene(layout, 300, 250);

    public Game(Stage window, Actions actions) {
        this.window = window;
        this.actions = actions;
    }

    public void getGame(){
        button.setText("It worked!");
        button.setOnAction(actions);
        layout.getChildren().add(button);
    }

    public void setActive(){
        window.setScene(scene);
        window.show();
    }
}
