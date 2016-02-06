import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Created by joonas on 4.02.16.
 */
public class Game {

    private Stage window;
    private Main main;
    private int xSize;
    private int ySize;
    private int treasureCount;
    private Tile [][] board;
    Button button = new Button();
    StackPane layout = new StackPane();
    Scene scene = new Scene(layout, 300, 250);
    GridPane boardPane = new GridPane();

    public Game(Stage window, Main main) {
        this.window = window;
        this.main = main;
    }

    public void getGame(int xSize, int ySize, int treasureCount){
        this.xSize = xSize;
        this.ySize = ySize;
        this.treasureCount = treasureCount;
        button.setText("It worked!");
        board = new Tile[xSize][ySize];
        layout.getChildren().add(TileBoard());
    }

    public void setActive(){
        window.setScene(scene);
        window.show();
    }

    private Node TileBoard() {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                board[i][j] = new Tile(i, j);
                Tile newTile = board[i][j];
                newTile.setOnMouseEntered(event -> newTile.hoverTile());
                newTile.setOnMouseExited(event -> newTile.exitTile());
                newTile.setOnMouseClicked(event -> {
                    newTile.revealTile();
                    exposeTile(newTile);
                });
                boardPane.add(new StackPane(newTile), i, j);
            }
        }
        return boardPane;
    }

    private void exposeTile(Tile tile){
        Label tileNumber = new Label();
        tileNumber.setText(String.valueOf(tile.adjacency.getValue()));
        tileNumber.setTextFill(Color.web("#0099ff"));
        tileNumber.setTextAlignment(TextAlignment.CENTER);
        boardPane.add(tileNumber, tile.xPos.getValue(), tile.yPos.getValue());
    }

}
