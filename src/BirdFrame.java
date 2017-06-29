import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

class BirdFrame extends JFrame implements ActionListener{

    private static BirdPanel birdPanel = new BirdPanel();
    private static ScorePanel scorePanel = new ScorePanel();
    private BirdTimer birdTimer = new BirdTimer();
    private static File wingFile = new File("res/wing.wav");
    private static AudioInputStream ais;
    private static Clip clip;
    private byte once = 1;

    public static ScorePanel getScorePanel() {
        return scorePanel;
    }

    BirdFrame(){
        setTitle("Flappy Bird");
        addKeyListener(new Keyboard());
        add(scorePanel,BorderLayout.NORTH);
        add(birdPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    public static BirdPanel getBirdPanel() {
        return birdPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        birdPanel.repaint();
    }

    private class Keyboard extends KeyAdapter{
        @Override
        @SuppressWarnings("Duplicates")
        public void keyTyped(KeyEvent e) {
            if(e.getKeyChar() == ' '){
                if(once == 1)
                {
                    Thread t = new Thread(birdTimer);
                    t.start();
                    once--;
                }
                Bird b = birdPanel.getBird();
                try{
                    ais = AudioSystem.getAudioInputStream(wingFile);
                    clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.setFramePosition(0);
                    clip.start();
                } catch (Exception ex){}
                b.setVelocity(-15);
            }
        }
    }
}
