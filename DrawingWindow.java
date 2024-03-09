import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingWindow extends JFrame {
    private ArrayList<ArrayList<Point>> lines = new ArrayList<>();
    private boolean isDrawing;
    private Point lastPoint;

    public DrawingWindow() {
        setTitle("Drawing Window");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (ArrayList<Point> line : lines) {
                    for (int i = 0; i < line.size() - 1; i++) {
                        Point p1 = line.get(i);
                        Point p2 = line.get(i + 1);
                        g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                }
            }
        };

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isDrawing = true;
                lastPoint = e.getPoint();
                ArrayList<Point> newLine = new ArrayList<>();
                newLine.add(lastPoint);
                lines.add(newLine);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDrawing = false;
                lastPoint = null;
            }
        });

        drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDrawing) {
                    Point currentPoint = e.getPoint();
                    if (!currentPoint.equals(lastPoint)) {
                        lines.get(lines.size() - 1).add(currentPoint);
                        lastPoint = currentPoint;
                        drawingPanel.repaint();
                    }
                }
            }
        });

        getContentPane().add(drawingPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DrawingWindow().setVisible(true);
        });
    }
}
