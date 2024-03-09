import java.awt.*; 
import javax.swing.* ;
import java.awt.event.*; 
import java.util.Random;

public class GameBall extends JFrame {
    private int ballX=10,ballY=10;
    private boolean gameOver;
    private int score;
    private static final int BALL_SIZE = 20;
    private static final int MOVE_SPEED = 3;
    public GameBall()
    {
        gameOver = false;
        setTitle("Bouncing Ball");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int mouseX = e.getX();
                int mouseY = e.getY();
                if (mouseX >= ballX && mouseX <= ballX + BALL_SIZE && mouseY >= ballY && mouseY <= ballY + BALL_SIZE) {
                    score += 10;
                    System.out.println("Score: " + score);
                }
            }
        });
        JPanel gameBall= new JPanel(){
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
            }
        };
        Thread gameThread = new Thread(() -> {
            while (!gameOver) {
                moveBall();
                repaint();
                try {
                    Thread.sleep(20); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    }
    private void moveBall()
    {
        while (true) { 
            ballX = ballX + 1; 
            ballY = ballY + 1; 

            // specifying some condition to make 
            // balls move in a particular path 
            if (ballX < 0 || ballX > 680) 
                gameOver=true;
            if (ballY < 20 || ballY > 780) 
                gameOver=true;
        } 
    } 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameBall().setVisible(true);
        });
    }

    
}
