
class Pipe {
    private int height;
    private int x;
    private static final int DISTANCE = 200;
    private static final int WIDTH = 100;

    Pipe(){
        height = (int)(Math.round(Math.random()*(BirdPanel.getHEIGHT()-400)) + 100);
        x = BirdPanel.getWIDTH();
    }
    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getDISTANCE() {
        return DISTANCE;
    }

    public void move(){
        x-=10;
    }
}
