import com.sun.javafx.event.EventHandlerManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

    Button button = new Button();
    Button button2 = new Button();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        //  Initalize classes
        Actions actions = new Actions(this);
        Menu menu = new Menu(window, actions);
        Game game = new Game(window, actions);
        //  Actions need to get their referenced Views
        actions.setGame(game);
        actions.setMenu(menu);
        //  By default we render out the main menu
        menu.getMenu();
        menu.setActive();
    }
}
