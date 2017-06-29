import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class BirdPanel extends JPanel {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    private static File soundFile = new File("res/point.wav");
    private static File dieFile = new File("res/die.wav");
    private static AudioInputStream ais;
    private static Clip clip;
    private static Image background = new ImageIcon("res/bg1.png").getImage();
    private static BufferedImage ground;
    private static BufferedImage pipeUpImg;
    private static BufferedImage pipeDownImg;

    private Bird bird = new Bird();
    private Pipe pipe1 = new Pipe();
    private ArrayList<Pipe> pipes = new ArrayList<>();

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }


    public Bird getBird() {
        return bird;
    }

    public BirdPanel(){
        try{
        ground = ImageIO.read(new File("res/spr_earth.png"));
        pipeUpImg = ImageIO.read(new File("res/pipeUp.png"));
        pipeDownImg = ImageIO.read(new File("res/pipeDown.png"));
        }catch (Exception e){}
        bird.setX(WIDTH / 16);
        bird.setY(HEIGHT / 2);
        pipes.add(pipe1);
        repaint();
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void paint(Graphics g)
    {
        g.drawImage(background, 0 , 0 , WIDTH, HEIGHT, this);
        g.drawImage(bird.getImage(), bird.getX(), bird.getY(), Bird.fat+20, Bird.fat, this);
        bird.move();
        g.setColor(Color.YELLOW);
        for (Pipe pipe : pipes)
        {
            g.drawImage(pipeUpImg, pipe.getX(), 0, Pipe.getWIDTH(), pipe.getHeight(), this);
            g.drawImage(pipeDownImg, pipe.getX(), pipe.getHeight() + Pipe.getDISTANCE(), Pipe.getWIDTH(), HEIGHT-(pipe.getHeight() + Pipe.getDISTANCE()), this);
            pipe.move();
            if (bird.getX() + Bird.fat >= pipe.getX() && bird.getX() <= pipe.getX() + Pipe.getWIDTH()
                    && (bird.getY() <= pipe.getHeight() || bird.getY() + Bird.fat >= pipe.getHeight() + Pipe.getDISTANCE()))
            {
                try{
                    ais = AudioSystem.getAudioInputStream(dieFile);
                    clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.setFramePosition(0);
                    clip.start();
                } catch (Exception e){}
                Game.setGameOver(true);
            }
            if (bird.getX() == pipe.getX() + Pipe.getWIDTH())
            {
                try
                {
                    ais = AudioSystem.getAudioInputStream(soundFile);
                    clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.setFramePosition(0);
                    clip.start();
                } catch (Exception e){}
                Game.addScore();
            }
        }

        if(bird.getY()>HEIGHT)
            Game.setGameOver(true);

        if (pipes.get(pipes.size() - 1).getX() < WIDTH / 2)
            pipes.add(new Pipe());
        for (int i = 0; i < ground.getWidth()*43; i+=ground.getWidth()) {
            g.drawImage(ground,i,HEIGHT/16*15,ground.getWidth(),ground.getHeight(),this);
        }
    }

    public void gameOver(){
        for (Component c : getComponents())
            remove(c);
        repaint(0,0,WIDTH,HEIGHT);
        Font f = new Font("Times New Roman", Font.ITALIC, 32);
        JLabel label = new JLabel("Ну ёптваймать, всего то " + Game.getScore());
        label.setFont(f);
        add(label, BorderLayout.CENTER);
        repaint(0,0,WIDTH,HEIGHT);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}
