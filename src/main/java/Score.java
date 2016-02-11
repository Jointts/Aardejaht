import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.*;
import java.util.List;

/**
 * Created by joonas on 9.02.16.
 */
public class Score {

    public SimpleIntegerProperty moves = new SimpleIntegerProperty();
    public SimpleIntegerProperty treasuresLeft = new SimpleIntegerProperty();
    public SimpleStringProperty name = new SimpleStringProperty();
    public SimpleStringProperty size = new SimpleStringProperty();

    public Score(int treasuresLeft, String xSize, String ySize){
        this.name.setValue("Joonas");
        this.moves.setValue(0);
        this.size.setValue(xSize + "x" + ySize);
        this.treasuresLeft.setValue(treasuresLeft);
    }

    @Override
    public String toString(){
        return name.getValue() + " Size: " + size.getValue() + " Moves: " + moves.getValue();
    }

}
