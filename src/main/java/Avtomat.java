import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Avtomat  {

    private final int DISTANCE_BETWEEN_LINES_PX = Utils.get_pixels_from_mm(6.0);
    private final double WIDTH_LINE1_MM = 20.0;
    private final double WIDTH_LINE2_MM = 20.0;
    private final double WIDTH_LINE3_MM = 6.0;
    // Нейтраль
    private final double WIDTH_LINE4_MM = 30.0;
    // Земля
    private final double WIDTH_LINE5_MM = 25.0;

    private final double LINE_THICKNESS_MM = 0.2;

    public List<Point> attachmentPoints;


    // Сам автомат
    Line line1;
    Line line2;
    Line line3;
    // N и PE
    Line line4;
    Line line5;
    Line line6;
    Line line7;

    private int neutralPointY;
    private int groundPointY;
    private Point pointStart;
    private String textLabel;


    public Avtomat(int x,int y, int neutralPointY, int groundPointY, String textLabel) {
        attachmentPoints = new ArrayList<>();
        pointStart = new Point(x,y);
        this.textLabel = textLabel;
        this.line1 = new Line(WIDTH_LINE1_MM, x, y,90);
        this.line2 = new Line(WIDTH_LINE2_MM, x, y + DISTANCE_BETWEEN_LINES_PX + Utils.get_pixels_from_mm(WIDTH_LINE1_MM),90);
        this.line3 = new Line(WIDTH_LINE3_MM, x, y + DISTANCE_BETWEEN_LINES_PX + Utils.get_pixels_from_mm(WIDTH_LINE1_MM),-120);

        this.neutralPointY = neutralPointY;
        this.groundPointY = groundPointY;

        line1.setLineThickness(LINE_THICKNESS_MM);
        line2.setLineThickness(LINE_THICKNESS_MM);
        line3.setLineThickness(LINE_THICKNESS_MM);


        this.line4 = new Line(WIDTH_LINE4_MM, x + Utils.get_pixels_from_mm(2.5), neutralPointY,90);
        this.line5 = new Line(WIDTH_LINE5_MM, x + Utils.get_pixels_from_mm(5.0), groundPointY,90);
        this.line6 = new Line(line4.endPointX, line4.endPointY, line2.endPointX, line4.endPointY + Utils.get_pixels_from_mm(5.0) );
        this.line7 = new Line(line5.endPointX, line5.endPointY, line2.endPointX, line4.endPointY + Utils.get_pixels_from_mm(5.0));

        attachmentPoints.add(new Point(line1.startPointX, line1.startPointY));
        attachmentPoints.add(new Point(line4.startPointX, line4.startPointY));
        attachmentPoints.add(new Point(line5.startPointX, line5.startPointY));

    }

    public void draw(Graphics2D g2d){
        line1.draw(g2d);
        line2.draw(g2d);
        line3.draw(g2d);
        line4.draw(g2d);
        line5.draw(g2d);
        line6.draw(g2d);
        line7.draw(g2d);

        Utils.drawPoints(attachmentPoints, g2d);

        Font font = new Font("Arial", Font.PLAIN, Utils.get_pixels_from_mm(5));
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int labelWidth = metrics.stringWidth(textLabel);
        int x = line3.endPointX - labelWidth;
        int y = line3.endPointY + Utils.get_pixels_from_mm(10);
        g2d.setColor(Color.BLACK); // Установка цвета текста
        g2d.drawString(textLabel, x, y);
    }

}
