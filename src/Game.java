import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
    private Tile [][] board;
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
        button.setText("It worked!");
        board = new Tile[xSize][ySize];
        layout.getChildren().add(TileBoard());
    }

    public void setActive(){
        window.setScene(scene);
        window.show();
    }

    private Node TileBoard() {
        GridPane boardPane = new GridPane();
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                board[i][j] = new Tile();
                //  System.out.println(board[i][j]);
                //  System.out.println(board[i][j].get().discovered);
                board[i][j].discovered.setValue(Boolean.TRUE);
                board[i][j].setOnMouseEntered(event -> System.out.println("Hovered"));
                board[i][j].setOnMouseExited(event -> System.out.println("Exited"));
                board[i][j].setOnMouseClicked(event -> System.out.println("Clicked"));
                boardPane.add(new StackPane(board[i][j]), i, j);
            }
        }
        return boardPane;
    }

}
