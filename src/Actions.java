import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Created by joonas on 4.02.16.
 */
public class Actions {

    private Main main;

    private Game game;

    private Menu menu;

    public Actions(Main main){
        this.main = main;
        this.game = main.getGame();
        this.menu = main.getMenu();
    }

    public void startGame() {
        menu.setActive();
    }
}
