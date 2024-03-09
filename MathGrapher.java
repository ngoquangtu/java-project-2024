import javax.swing.*;
import java.awt.*;

public class MathGrapher extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double SCALE_X = 100.0;
    private static final double SCALE_Y = 100.0;

    public MathGrapher() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2); // x-axis
        g2d.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT); // y-axis

        g2d.setColor(Color.BLUE);

        double startX = -Math.PI;
        double endX = Math.PI;
        double step = 0.01;

        int prevX = (int) (WIDTH / 2 + startX * SCALE_X);
        int prevY = (int) (HEIGHT / 2 - Math.sin(startX) * SCALE_Y);

        for (double x = startX + step; x <= endX; x += step) {
            int currX = (int) (WIDTH / 2 + x * SCALE_X);
            int currY = (int) (HEIGHT / 2 - Math.sin(x) * SCALE_Y);
            g2d.drawLine(prevX, prevY, currX, currY);
            prevX = currX;
            prevY = currY;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sine Graph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new MathGrapher());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
