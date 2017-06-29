import javax.swing.*;

public class Game {

    private static boolean gameOver = false;
    private static int score = 0;

    public static int getScore() {
        return score;
    }

    public static void main(String[] args) {
        BirdFrame frame = new BirdFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void setGameOver(boolean gameOver) {
        Game.gameOver = gameOver;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void addScore(){
        score++;
    }
}
