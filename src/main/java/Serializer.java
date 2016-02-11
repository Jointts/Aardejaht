import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by joonas on 11.02.16.
 */
public class Serializer {
    private Main main;
    XStream xstream = new XStream(new DomDriver());

    public Serializer(Main main){
        this.main = main;
    }

    public void setScore(Score score){

        try {
            FileInputStream fin = new FileInputStream ("src/main/resources/score.xml");
            xstream.processAnnotations(ScoreList.class);
            ScoreList scoreList = (ScoreList)xstream.fromXML(fin);
            FileWriter writer = new FileWriter("src/main/resources/score.xml", false);
            xstream.processAnnotations(ScoreList.class);
            scoreList.addScore(score);
            String result = xstream.toXML(scoreList);
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ScoreList getScore(){

        try {
            FileInputStream fin = new FileInputStream ("src/main/resources/score.xml");
            xstream.processAnnotations(ScoreList.class);
            return (ScoreList)xstream.fromXML(fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
