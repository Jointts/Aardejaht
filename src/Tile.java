import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.effect.Light;
import javafx.scene.effect.LightingBuilder;
import javafx.scene.layout.Region;

/**
 * Created by joonas on 4.02.16.
 */
public class Tile extends Region{

    SimpleBooleanProperty discovered = new SimpleBooleanProperty();
    SimpleIntegerProperty adjacency = new SimpleIntegerProperty();

    public Tile(){
        discovered.setValue(Boolean.FALSE);
        setStyle("-fx-background-color: burlywood");
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135);
        light.setElevation(30);
        setEffect(LightingBuilder.create().light(light).build());
        setPrefSize(200, 200);
    }

}
