import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bird
{
    private static final int GRAVITY = 1;
    private static BufferedImage image;
    private static BufferedImage bird;
    private int velocity = 0;
    private int x = 0;
    private int y = 0;
    private static int start = 0;
    public static int fat = 50;
    private static int i = -1;

    Bird(){
        try {
            int f = (int) (Math.round(Math.random() * 2)+1);
            image = ImageIO.read(new File("res/spr_b" + f + "_strip4.png"));
        } catch (Exception e) {
        }
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }


    public BufferedImage getImage() {
        return bird;
    }

    public static void changeImage() {
        if (i == 3)
            i = 0;
        else
            i++;
        int width = 34;
        int height = 24;
        int y = 0;
        start = width * i;
        bird = image.getSubimage(start, y, width, height);
    }

    public int getVelocity() {
        return velocity;
    }


    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {
        velocity += GRAVITY;
        y += velocity;
    }
}
