package ro.urzicavlad;

import ro.urzicavlad.service.GameServiceImpl;

public class Application {

    public static void main(String[] args) {
        new GameServiceImpl().start();
    }
}
