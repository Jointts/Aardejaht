import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    Scene scene;
    GridPane boardPane = new GridPane();

    public Game(Stage window, Main main) {
        this.window = window;
        this.main = main;
    }

    public void setActive(int xSize, int ySize, int treasureCount){
        int sceneWidth = xSize * 50;
        int sceneHeight = ySize * 50;
        this.scene = new Scene(layout, sceneWidth, sceneHeight);
        this.xSize = xSize;
        this.ySize = ySize;
        this.treasureCount = treasureCount;
        int treasuresMade = 0;
        board = new Tile[xSize][ySize];
        layout.getChildren().add(TileBoard());
        Random rand = new Random();
        for(int count = 0; count < treasureCount; count++){
            int x = rand.nextInt(xSize);
            int y = rand.nextInt(ySize);
            AddTreasure(x, y);
            treasuresMade++;
        }
        System.out.println("Times called: " + treasuresMade);
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
                StackPane tilePane = new StackPane(newTile);
                tilePane.setAlignment(Pos.CENTER);
                boardPane.add(tilePane, i, j);
            }
        }
        return boardPane;
    }

    public void AddTreasure(int x, int y){
        System.out.println("-----------------");
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
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
        tile.setCursor(Cursor.DEFAULT);
        if(tile.isTreasure.getValue()){
            Image treasureImage = new Image("img/treasure.png");
            ImageView treasureImageView = new ImageView(treasureImage);
            GridPane.setHalignment(treasureImageView, HPos.CENTER); // To align horizontally in the cell
            GridPane.setValignment(treasureImageView, VPos.CENTER); // To align vertically in the cell
            boardPane.add(treasureImageView, tile.xPos.get(), tile.yPos.get());
        }else {
            StackPane pane = new StackPane();
            Label tileNumber = new Label();
            tileNumber.setText(String.valueOf(tile.adjacency.getValue()));
            tileNumber.setStyle("-fx-font-weight: bold; -fx-text-fill: #0099ff;");
            boardPane.add(tileNumber, tile.xPos.getValue(), tile.yPos.getValue());
        }
    }

}
