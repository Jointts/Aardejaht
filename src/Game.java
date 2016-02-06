import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Random;

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
    StackPane layout = new StackPane();
    Scene scene = new Scene(layout, 300, 250);
    GridPane boardPane = new GridPane();

    public Game(Stage window, Main main) {
        this.window = window;
        this.main = main;
    }

    public void setActive(int xSize, int ySize, int treasureCount){
        this.xSize = xSize;
        this.ySize = ySize;
        this.treasureCount = treasureCount;
        board = new Tile[xSize][ySize];
        layout.getChildren().add(TileBoard());
        Random rand = new Random();
        for(int count = 0; count <= treasureCount; count++){
            int x = rand.nextInt(xSize);
            int y = rand.nextInt(ySize);
            AddTreasure(x, y);
        }
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

    public void AddTreasure(int x, int y){
        Tile treasureTile = board[x][y];
        treasureTile.isTreasure.setValue(Boolean.TRUE);
        for(int xScan = x-1; xScan <= x+1 && xScan < xSize; xScan++){
            for(int yScan = y-1; yScan <= y+1 && yScan < ySize; yScan++){
                if(yScan < 0 | xScan < 0){continue;}
                Tile populatedTile = board[xScan][yScan];
                populatedTile.adjacency.setValue(populatedTile.adjacency.getValue() + 1);
            }
        }
    }

    private void exposeTile(Tile tile){
        if(tile.isTreasure.getValue()){
            tile.setStyle("-fx-background-color: #a01018");
        }else {
            Label tileNumber = new Label();
            tileNumber.setText(String.valueOf(tile.adjacency.getValue()));
            tileNumber.setTextFill(Color.web("#0099ff"));
            tileNumber.setTextAlignment(TextAlignment.CENTER);
            boardPane.add(tileNumber, tile.xPos.getValue(), tile.yPos.getValue());
        }
    }

}
