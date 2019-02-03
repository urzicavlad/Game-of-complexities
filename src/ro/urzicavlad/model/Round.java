package ro.urzicavlad.model;

import java.util.List;
import java.util.Map;

public class Round {

    private Level level;
    private Map<String, String> algorithmsDb;
    private List<String> algorithmsName;
    private List<String> algorithmsComplexity;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Map<String, String> getAlgorithmsDb() {
        return algorithmsDb;
    }

    public void setAlgorithmsDb(Map<String, String> algorithmsDb) {
        this.algorithmsDb = algorithmsDb;
    }

    public List<String> getAlgorithmsName() {
        return algorithmsName;
    }

    public void setAlgorithmsName(List<String> algorithmsName) {
        this.algorithmsName = algorithmsName;
    }

    public List<String> getAlgorithmsComplexity() {
        return algorithmsComplexity;
    }

    public void setAlgorithmsComplexity(List<String> algorithmsComplexity) {
        this.algorithmsComplexity = algorithmsComplexity;
    }

    @Override
    public String toString() {
        return "Round{" +
                "algorithmsDb=" + algorithmsDb +
                ", algorithmsName=" + algorithmsName +
                ", algorithmsComplexity=" + algorithmsComplexity +
                '}';
    }
}
