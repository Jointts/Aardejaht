import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    private Menu menu;
    private Game game;
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        //  Initialize classes
        this.window = window;
        setMenu(new Menu(window, this));
        //  By default we render out the main menu
        menu.setActive();
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }
}
