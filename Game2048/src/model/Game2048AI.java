package model;

import java.util.Random;
import controller.Game2048Controller;

public class Game2048AI implements Runnable {
    private Game2048Controller game2048Controller;
    private boolean isRunning;

    public Game2048AI(Game2048Controller game2048Controller) {
        this.game2048Controller = game2048Controller;
        this.isRunning = true;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] moves = { "UP", "DOWN", "LEFT", "RIGHT" };
        while (isRunning) {
            // Select a random move
            String move = moves[random.nextInt(moves.length)];
            switch (move) {
                case "UP":
                    game2048Controller.moveUp();
                    break;
                case "DOWN":
                    game2048Controller.moveDown();
                    break;
                case "LEFT":
                    game2048Controller.moveLeft();
                    break;
                case "RIGHT":
                    game2048Controller.moveRight();
                    break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
