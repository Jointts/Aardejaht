import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by joonas on 4.02.16.
 */
public class Game {

    private Stage window;
    private Main main;
    private int xSize;
    private int ySize;
    private ObjectProperty<Tile>[][] board;
    Button button = new Button();
    StackPane layout = new StackPane();
    Scene scene = new Scene(layout, 300, 250);

    public Game(Stage window, Main main) {
        this.window = window;
        this.main = main;
    }

    public void getGame(int xSize, int ySize){
        this.xSize = xSize;
        this.ySize = ySize;
        board = new ObjectProperty[xSize][ySize];
        button.setText("It worked!");
        BoardModel();
        layout.getChildren().add(TileSet());
    }

    public void setActive(){
        window.setScene(scene);
        window.show();
    }

    private void BoardModel() {
        GridPane boardPane = new GridPane();
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                board[i][j] = new SimpleObjectProperty<>(new Tile());
                //  System.out.println(board[i][j]);
                //  System.out.println(board[i][j].get().discovered);
                board[i][j].get().discovered.setValue(Boolean.TRUE);
            }
        }
    }

    private Node TileSet(){
        GridPane board = new GridPane();
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Tile tile = new Tile();
                tile.discovered.bind(this.board[0][0].get().discovered);
                board.add(new StackPane(tile), i, j);
            }
        }
        return board;
    }

}
