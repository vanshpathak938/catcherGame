import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;


public class catcher implements Runnable {
    public static int left = 0;
    public static int right = 0;
    public static int pos = 150;
    public static JFrame myJFrame = new JFrame();
    public static JLabel movingPlayer; 
    public static JLabel score;
    public static int scoreValue = 0;
    public static int speed = 100;
    public static void main(String[] args) {
    MyProgram obj = new MyProgram();
    Thread thread = new Thread(obj);
    thread.start();
    
    movingPlayer = new JLabel("|_____________|"); 
    
    score = new JLabel(String.valueOf("Current Score: " + scoreValue));
    score.setBounds(10,10,150,30);
    myJFrame.add(score);
    
    movingPlayer.setBounds(pos,330, 100,30);   
    myJFrame.add(movingPlayer);
    myJFrame.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println(keyCode);
        if (keyCode == KeyEvent.VK_LEFT) {
          left = left + 10;
          right = 0;
          pos = pos - left;
          left = 0;
          movingPlayer.setBounds(pos,330, 100,30);
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
        right = right + 10;
        left = 0;
        pos = pos + right;
        right = 0;
        movingPlayer.setBounds(pos,330, 100,30);
        }
      }
    });
    
        myJFrame.setSize(400, 400);
        myJFrame.setLayout(null);
        myJFrame.setVisible(true);
        
    }
    
    public void run() {
     try
      {
        Thread.sleep(speed);   
        JLabel movingObject; 
        movingObject = new JLabel("_____________");  
        int randomXValue = (int)(Math.random()*(300)+10);
        movingObject.setBounds(randomXValue,50, 100,30);   
        myJFrame.add(movingObject);
        for(int x = 10; x < 330; x+=10)
        {
            movingObject.setBounds(randomXValue,30+x, 100,30); 
            Thread.sleep(200);
            if(movingPlayer.getX()<=movingObject.getX()&&movingObject.getX()<=movingPlayer.getX()+17&&movingObject.getY()==320){
                System.out.println("point earned");
                movingObject.setVisible(false);
                scoreValue++;
                score.setText(String.valueOf("Current Score: " + scoreValue));
                speed -= 10;
                if(speed < 10)
                {
                    speed = 10;
                }
                run();
            }
            else if(movingObject.getY()==330)
            {
                score.setBounds(10,10,250,30);
                score.setText("GAME OVER your score was: " + String.valueOf(scoreValue));
                break;
            }
            
        }
      }
      catch(InterruptedException ex)
      {
      } 
  }
}
