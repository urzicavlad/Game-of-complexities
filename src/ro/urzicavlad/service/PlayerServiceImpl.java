package ro.urzicavlad.service;

import ro.urzicavlad.map.MapImpl;
import ro.urzicavlad.model.Game;
import ro.urzicavlad.model.Player;

import java.util.List;
import ro.urzicavlad.map.Map;
import java.util.Scanner;

public class PlayerServiceImpl implements PlayerService {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public Player register() {
        return collectPlayerInfo();
    }

    @Override
    public void updateScore(Integer points, Player player) {
        player.setScore(player.getScore() + points);
    }

    private Player collectPlayerInfo(){
        System.out.println("Welcome at Game of Complexities!");
        Player player = new Player();
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        player.setName(name);
        player.setAnswers(new MapImpl<>());
        return player;
    }

    public Map<String, String> collectAnswers(Game game) {
        Map<String, String> answers = new MapImpl<>();
        System.out.println("The answer must be in this format: " +
                "[digit-digit](Algorithm name position - Algorithm complexity position)");
        final List<String> algorithmNames = game.getRound().getAlgorithmsName();
        final List<String> algorithmsComplexity = game.getRound().getAlgorithmsComplexity();
        int i = 0;
        while (i < game.getRound().getLevel().getAlgorithms()) {
            System.out.println("Algorithms: " + algorithmNames + "" +
                    "\nComplexities:" + algorithmsComplexity);
            String response = scanner.nextLine();
            if (!isValid(response)) {
                System.out.println("Invalid Answer! (Keep in mind to respect [digit-digit] format)");
                continue;
            }
            String[] parts = response.split("-");
            final int algorithmNameIndex = Integer.parseInt(parts[0]) - 1;
            final int complexityIndex = Integer.parseInt(parts[1]) - 1;
            if (algorithmNameIndex >= algorithmNames.size() ||
                    complexityIndex >= algorithmsComplexity.size()){
                System.out.println("Invalid Answer! (Maybe you are out of scope:D!)");
                continue;
            }
            final String algorithm = algorithmNames.get(algorithmNameIndex);
            final String complexity = algorithmsComplexity.get(complexityIndex);
            if (!answers.containsKey(algorithm)) {
                answers.put(algorithm, complexity);
                System.out.println(algorithm + " --> " + complexity);
                i++;
            } else {
                System.out.println("You have been answer to that algorithm!");
            }
        }
        return answers;
    }

    private boolean isValid(String response) {
        return response.length() == 3 &&
                Character.isDigit(response.charAt(0)) &&
                response.charAt(1) == '-' &&
                Character.isDigit(response.charAt(2));
    }

}
