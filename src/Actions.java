import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Created by joonas on 4.02.16.
 */
public class Actions implements EventHandler<ActionEvent>{

    private Main main;

    private Game game;

    private Menu menu;

    public Actions(Main main){
        this.main = main;
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource()==menu.button){
            game.getGame();
            game.setActive();
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
