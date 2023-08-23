import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

class LabeledShape {
    private Shape shape;
    private String label;
    private Point position;

    public LabeledShape(Shape shape, String label, Point position) {
        this.shape = shape;
        this.label = label;
        this.position = position;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);

        g2d.draw(shape);
        Font font = new Font("Arial", Font.PLAIN, 14);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int labelWidth = metrics.stringWidth(label);
        int x = position.x - labelWidth;
        int y = position.y;
        g2d.setColor(Color.BLACK); // Установка цвета текста
        g2d.drawString(label, x, y);
    }
}

