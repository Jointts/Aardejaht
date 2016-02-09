import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.*;

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
        setJson();
        getJson();
    }

    @Override
    public String toString(){
        return name.getValue() + " Size: " + size.getValue() + " Moves: " + moves.getValue();
    }

    public void setJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);

        try {
            FileWriter writer = new FileWriter("src/main/resources/score.json", false);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/score.json"));
            Score obj = gson.fromJson(br, Score.class);
            System.out.println(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
