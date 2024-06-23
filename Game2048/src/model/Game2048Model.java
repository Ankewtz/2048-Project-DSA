package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Game2048Model {
    private int playerScore, highScore;

    public Game2048Model() {
        this.playerScore = 0;
        this.highScore = 0;
    }

    public int getPlayerScore() {
        return this.playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setHighScore() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/model/DiemCaoNhat.txt"));
            bw.write(String.valueOf(this.getPlayerScore()));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHighScore() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/model/DiemCaoNhat.txt"));
            highScore = Integer.parseInt(br.readLine());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScore;
    }
}
