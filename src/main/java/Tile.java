import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Cursor;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * Created by joonas on 4.02.16.
 */
public class Tile extends Region{

    Lighting lighting = new Lighting();
    Light.Distant light = new Light.Distant();
    SimpleBooleanProperty discovered = new SimpleBooleanProperty();
    SimpleIntegerProperty adjacency = new SimpleIntegerProperty();
    SimpleIntegerProperty xPos = new SimpleIntegerProperty();
    SimpleIntegerProperty yPos = new SimpleIntegerProperty();
    SimpleBooleanProperty isTreasure = new SimpleBooleanProperty();

    public Tile(int xPos, int yPos){
        this.xPos.setValue(xPos);
        this.yPos.setValue(yPos);
        isTreasure.setValue(Boolean.FALSE);
        discovered.setValue(Boolean.FALSE);
        baseTile();
    }

    //  Display settings for a Tile that is undiscovered (Default setting)
    public void baseTile(){
        setStyle("-fx-background-color: #575757");
        light.setAzimuth(-135);
        light.setElevation(30);
        lighting.setLight(light);
        lighting.setSurfaceScale(0.9);
        setEffect(lighting);
        setPrefSize(50, 50);
    }

    //  Changes the color of this tile when exited upon
    public void exitTile(){
        setCursor(Cursor.DEFAULT);
        if(!discovered.getValue()){
            setStyle("-fx-background-color: #575757");
        }
    }

    //  Changes the color of this tile when hovered upon
    public void hoverTile(){
        if(!discovered.getValue()){
            setStyle("-fx-background-color: #a3a3a3");
            setCursor(Cursor.HAND);
        }
    }

    //  Changes the color of this tile when clicked upon
    public void revealTile(){
        setStyle("-fx-background-color: #404040");
        light.setAzimuth(135);
        light.setElevation(90);
        light.setColor(Color.DIMGRAY);
        lighting.setLight(light);
        lighting.setSurfaceScale(0.9);
        setEffect(lighting);
        discovered.setValue(Boolean.TRUE);
    }

}
