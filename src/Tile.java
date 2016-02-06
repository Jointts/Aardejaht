import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * Created by joonas on 4.02.16.
 */
public class Tile extends Region{

    SimpleBooleanProperty discovered = new SimpleBooleanProperty();
    SimpleIntegerProperty adjacency = new SimpleIntegerProperty();
    Lighting lighting = new Lighting();
    Light.Distant light = new Light.Distant();
    SimpleIntegerProperty xPos = new SimpleIntegerProperty();
    SimpleIntegerProperty yPos = new SimpleIntegerProperty();
    SimpleBooleanProperty isTreasure = new SimpleBooleanProperty();

    public Tile(int xPos, int yPos){
        this.xPos.setValue(xPos);
        this.yPos.setValue(yPos);
        isTreasure.setValue(Boolean.FALSE);
        discovered.setValue(Boolean.FALSE);
        setStyle("-fx-background-color: #575757");
        light.setAzimuth(-135);
        light.setElevation(30);
        lighting.setLight(light);
        lighting.setSurfaceScale(0.9);
        setEffect(lighting);
        setPrefSize(200, 200);
    }

    public void exitTile(){
        if(!discovered.getValue()){
            setStyle("-fx-background-color: #575757");
        }
    }

    public void hoverTile(){
        if(!discovered.getValue()){
            setStyle("-fx-background-color: #a3a3a3");
        }
    }

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
