import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class BallGame extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int BALL_SIZE = 20;
    private static final int MOVE_SPEED = 3;
    private int ballX=40, ballY=40;
    private boolean ballVisible=true;
    private boolean gameOver;
    private int score;

    public BallGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        score = 0;
        gameOver = false;
        generateRandomBallPosition();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int mouseX = e.getX();
                int mouseY = e.getY();
                if (ballVisible && mouseX >= ballX && mouseX <= ballX + BALL_SIZE && mouseY >= ballY && mouseY <= ballY + BALL_SIZE) {
                    score += 10;
                    ballVisible = false;
                    generateRandomBallPosition();
                    System.out.println("Score: " + score);
                }
            }
        });

        Thread gameThread = new Thread(() -> {
            while (!gameOver) {
                moveBall();
                checkCollision();
                repaint();
                try {
                    Thread.sleep(20); // Adjust speed of the ball
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    }

    private void moveBall() {
        if(ballVisible)
        {
            ballX += MOVE_SPEED;
            ballY += MOVE_SPEED;
        }


    }
    private void generateRandomBallPosition() {
        Random random = new Random();
        ballX = random.nextInt(WIDTH - BALL_SIZE);
        ballY=ballX;
        ballVisible = true;
    }

    private void checkCollision() {
        if (ballX < 0 || ballX + BALL_SIZE > WIDTH || ballY < 0 || ballY + BALL_SIZE > HEIGHT) {
            gameOver = true;
            System.out.println("Game Over! Final Score: " + score);
            JOptionPane.showMessageDialog(this, "Game Over! Final Score: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (ballVisible) {
            // Draw the ball
            g2d.setColor(Color.RED);
            g2d.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ball Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new BallGame());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
