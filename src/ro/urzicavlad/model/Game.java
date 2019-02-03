package ro.urzicavlad.model;


public class Game {

    private Player player;
    private Round round;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    @Override
    public String toString() {
        return "Game{" +
                "player=" + player +
                ", round=" + round +
                '}';
    }
}
