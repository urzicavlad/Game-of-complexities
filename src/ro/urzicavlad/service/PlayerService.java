package ro.urzicavlad.service;

import ro.urzicavlad.model.Game;
import ro.urzicavlad.model.Player;

import ro.urzicavlad.map.Map;

public interface PlayerService {

    Player register();

    Map<String, String> collectAnswers(Game game);

    void updateScore(Integer points, Player player);
}
