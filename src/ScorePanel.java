import javax.swing.*;
import java.awt.*;

/**
 * Created by Leet on 11.05.2017.
 */
public class ScorePanel extends JPanel{
    private static JLabel label;
    private static Font f = new Font("Comic Sans", Font.ITALIC, 18);

    public ScorePanel() {
        label = new JLabel("Your score is: " + Game.getScore());
        label.setFont(f);
        add(label, BorderLayout.CENTER);
    }

    public void setScore() {
//        int f = Game.getScore();
        label.setText("Your score is: " + Game.getScore());
    }

    public void gameOver(){
        for (Component c : getComponents())
            remove(c);
        repaint();
    }

}
