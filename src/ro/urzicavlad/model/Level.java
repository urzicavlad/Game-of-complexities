package ro.urzicavlad.model;

public enum Level {
    EASY(5), MEDIUM(10), HARD(15);

    private int algorithms;

    Level(int algorithms) {
        this.algorithms = algorithms;
    }

    public int getAlgorithms() {
        return algorithms;
    }
}
