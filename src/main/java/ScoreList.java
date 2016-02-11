import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joonas on 11.02.16.
 */
@XStreamAlias("ScoreList")
public class ScoreList {
    @XStreamImplicit(itemFieldName="Score")
    private List<Score> scores = new ArrayList<>();

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public void addScore(Score score){
        scores.add(score);
    }
}
