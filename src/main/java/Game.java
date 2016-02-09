import javafx.beans.binding.Bindings;
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
    private Score score;
    private Tile [][] board;
    GridPane layout = new GridPane();
    Scene scene;
    GridPane boardPane = new GridPane();

    public Game(Stage window, Main main) {
        this.window = window;
        this.main = main;
    }

    //  Initializing method for the game (Also used to change the scene to GAME)
    public void setActive(int xSize, int ySize, int treasureCount){
        int sceneWidth = xSize * 50;
        int sceneHeight = ySize * 50;
        this.scene = new Scene(layout, sceneWidth, sceneHeight);
        this.xSize = xSize;
        this.ySize = ySize;
        this.treasureCount = treasureCount;
        scene.getStylesheets().add("css/main.css");
        board = new Tile[xSize][ySize];
        score = new Score(treasureCount, String.valueOf(xSize), String.valueOf(ySize));
        GridPane gameMenu = new GridPane();
        gameMenu.add(getMenuButton(), 1, 1);
        gameMenu.add(getScoresButton(), 2, 1);
        gameMenu.add(getMoveCounter(), 5, 1);
        gameMenu.add(getTreasureCounter(), 4, 1);
        layout.add(TileBoard(), 0, 0, xSize, ySize);
        layout.add(gameMenu, 0, ySize, 5, 1);
        generateTreasures();
        window.setScene(scene);
        window.show();
    }

    //  Generates the initial blank-tiles display, and also passes Tile objects to a public array
    private Node TileBoard() {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                board[i][j] = new Tile(i, j);
                Tile newTile = board[i][j];
                newTile.setOnMouseEntered(event -> newTile.hoverTile());
                newTile.setOnMouseExited(event -> newTile.exitTile());
                newTile.setOnMouseClicked(event -> exposeTile(newTile));
                StackPane tilePane = new StackPane(newTile);
                tilePane.setAlignment(Pos.CENTER);
                boardPane.add(tilePane, i, j);
            }
        }
        return boardPane;
    }

    //  Adds a treasure to the provided coordinates and also adds adjacency +1 to its surrounding tiles
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

    // Generates random coordinates and calls the AddTreasure function
    public void generateTreasures(){
        Random rand = new Random();
        for(int count = 0; count < treasureCount; count++){
            int x = rand.nextInt(xSize);
            int y = rand.nextInt(ySize);
            AddTreasure(x, y);
        }
    }

    //  Exposes the tile
    private void exposeTile(Tile tile){
        if(tile.discovered.getValue()){return;}
        tile.revealTile();
        if(tile.adjacency.getValue() < 1){
            nullReveal(tile);
        }
        tile.setCursor(Cursor.DEFAULT);
        score.moves.setValue(score.moves.getValue() + 1);
        if(tile.isTreasure.getValue()){
            score.treasuresLeft.setValue(score.treasuresLeft.getValue() - 1);
            boardPane.add(getTreasureTile(), tile.xPos.get(), tile.yPos.get());
        }else {
            boardPane.add(getNumberTile(tile), tile.xPos.getValue(), tile.yPos.getValue());
        }
    }

    private void nullReveal(Tile tile){
        for(int xScan = tile.xPos.getValue()-1; xScan <= tile.xPos.getValue()+1 && xScan < xSize; xScan++){
            for(int yScan = tile.yPos.getValue()-1; yScan <= tile.yPos.getValue()+1 && yScan < ySize; yScan++){
                if(yScan < 0 | xScan < 0){continue;}
                exposeTile(board[xScan][yScan]);
            }
        }
    }

    //  Provides the display for the Menu button
    private Button getMenuButton(){
        Button menuButton = new Button("Menu");
        menuButton.setPrefSize(75, 25);
        menuButton.setOnMouseClicked(event -> {
            window.close();
            window.setScene(null);
            main.getMenu().setActive();
        });
        menuButton.setOnMouseEntered(event -> menuButton.setCursor(Cursor.HAND));
        menuButton.setId("menuButton");
        return menuButton;
    }

    //  Provides the display for the Scores button
    private Button getScoresButton(){
        Button scoreButton = new Button("Scores");
        scoreButton.setPrefSize(75, 25);
        scoreButton.setOnMouseEntered(event -> scoreButton.setCursor(Cursor.HAND));
        scoreButton.setId("menuButton");
        return scoreButton;
    }

    //  Provides the display for counting player moves
    private Label getMoveCounter(){
        Label scoreText = new Label();
        scoreText.setPrefSize(50, 25);
        scoreText.textProperty().bind(Bindings.convert(score.moves));
        return scoreText;
    }

    //  Provides the display for counting how many treasures are left
    private Label getTreasureCounter(){
        Label treasureText = new Label();
        treasureText.textProperty().bind(Bindings.convert(score.treasuresLeft));
        return treasureText;
    }

    //  Provides the display for a tile that has no treasure
    private Label getNumberTile(Tile tile){
        Label tileNumber = new Label();
        tileNumber.setText(String.valueOf(tile.adjacency.getValue()));
        tileNumber.setStyle("-fx-font-weight: bold; -fx-text-fill: #ffa717;");
        GridPane.setHalignment(tileNumber, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(tileNumber, VPos.CENTER); // To align vertically in the cell
        return tileNumber;
    }

    //  Provides the display for a tile that has a treasure
    private ImageView getTreasureTile(){
        Image treasureImage = new Image("img/treasure.png");
        ImageView treasureImageView = new ImageView(treasureImage);
        GridPane.setHalignment(treasureImageView, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(treasureImageView, VPos.CENTER); // To align vertically in the cell
        return treasureImageView;
    }

}
