package ro.urzicavlad.service;

import ro.urzicavlad.model.Level;
import ro.urzicavlad.model.Round;
import ro.urzicavlad.util.IOUtil;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import ro.urzicavlad.map.Map;

public class RoundServiceImpl implements RoundService {

    public Round getRound() {
        Round round = new Round();
        Map<String, String> algorithms;
        try {

            algorithms = IOUtil.readDataFromFile(Paths.get("resources/algorithm_db"));
            round.setLevel(getLevel());
            round.setAlgorithmsDb(algorithms);
            round.setAlgorithmsName(getAlgorithms(round));
            round.setAlgorithmsComplexity(getComplexities(round));
        } catch (IOException e) {
            System.out.println("File not found.");
        }
        return round;
    }

    private List<String> getAlgorithms(Round round) {
        List<String> algorithms = new ArrayList<>(round.getAlgorithmsDb().keySet());
        Collections.shuffle(algorithms);
        return algorithms.subList(0, round.getLevel().getAlgorithms());
    }

    private List<String> getComplexities(Round round) {
        final List<String> algorithmName = round.getAlgorithmsName();
        final List<String> algorithmComplexity = new ArrayList<>();
        round.getAlgorithmsDb().forEach((key,value) -> {
            if (algorithmName.contains(key) && !algorithmComplexity.contains(value)){
                algorithmComplexity.add(value);
            }
        });
        Collections.shuffle(algorithmComplexity);
        return algorithmComplexity;
    }

    private Level getLevel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose difficulty:" +
                "\n\t1-EASY" +
                "\n\t2-MEDIUM" +
                "\n\t3-HARD");
        String option;
        Level level = null;
        while (level == null) {
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    level = Level.EASY;
                    break;
                case "2":
                    level = Level.MEDIUM;
                    break;
                case "3":
                    level = Level.HARD;
                    break;
                default:
                    System.out.println("Invalid level!" +
                            "\nAvailable levels:" +
                            "\n\t1-EASY" +
                            "\n\t2-MEDIUM" +
                            "\n\t3-HARD");
            }
        }
        return level;
    }
}
