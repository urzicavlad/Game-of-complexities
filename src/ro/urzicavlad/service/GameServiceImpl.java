package ro.urzicavlad.service;

import ro.urzicavlad.map.MapImpl;
import ro.urzicavlad.model.Game;


import ro.urzicavlad.map.Map;
import java.util.Scanner;

public class GameServiceImpl implements GameService {

    private PlayerService playerService = new PlayerServiceImpl();
    private RoundService roundService = new RoundServiceImpl();

    @Override
    public Game start() {
        return prepareGame();
    }

    private Game prepareGame() {
        Game game = new Game();
        game.setPlayer(this.playerService.register());
        boolean quit = false;
        while (!quit) {
            game.setRound(this.roundService.getRound());
            final Map<String, String> answers = playerService.collectAnswers(game);
            Integer score = calculateScore(answers, game.getRound().getAlgorithmsDb());
            playerService.updateScore(score, game.getPlayer());
            System.out.println("You have been accumulate " + game.getPlayer().getScore() + " points!");
            quit = isQuit();
        }
        return game;
    }

    private Integer calculateScore(Map<String, String> answers, Map<String, String> algorithmDb) {
        Map<String, String> playerCorrectAnswer = new MapImpl<>();
        for (String answer : answers.keySet()) {
            if (answers.get(answer).equalsIgnoreCase(algorithmDb.get(answer))) {
                playerCorrectAnswer.put(answer, algorithmDb.get(answer));
            }
        }
        int points = playerCorrectAnswer.size();
        System.out.println("Your correct answers! " + playerCorrectAnswer);
        System.out.println("You got " + points + " points!");
        return points;
    }

    private boolean isQuit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to play again?(Y/N)");
        return scanner.nextLine().equalsIgnoreCase("N");
    }
}

