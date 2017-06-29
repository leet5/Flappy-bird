public class BirdTimer implements Runnable{

    @Override
    public void run() {
        try{
            while (!Thread.currentThread().isInterrupted())
            {
                Thread.sleep(30);
                if(Game.isGameOver()){
                    System.out.println("Ваш счет: " + Game.getScore());
                    throw new InterruptedException();
                }
                Bird.changeImage();
                BirdFrame.getBirdPanel().repaint();
                BirdFrame.getScorePanel().setScore();
                BirdFrame.getScorePanel().revalidate();
            }
        }catch (InterruptedException e){
            BirdFrame.getScorePanel().gameOver();
            BirdFrame.getBirdPanel().gameOver();

            BirdFrame.getScorePanel().revalidate();
            BirdFrame.getBirdPanel().revalidate();
        }
    }

}
