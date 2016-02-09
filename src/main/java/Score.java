import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by joonas on 9.02.16.
 */
public class Score {

    public SimpleIntegerProperty moves = new SimpleIntegerProperty();
    public SimpleIntegerProperty treasuresLeft = new SimpleIntegerProperty();

    public Score(int treasuresLeft){
        this.moves.setValue(0);
        this.treasuresLeft.setValue(treasuresLeft);
    }
}
