import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class ParallelLinesFigure {

    private final int DISTANCE_BETWEEN_LINES = Utils.get_pixels_from_mm(5);
    private final int DIAMETER_ATTACHMENT_POINT = Utils.get_pixels_from_mm(2);
    private final double PHASE_LINE_THICKNESS = 1.0;
    private final double NEUTRAL_LINE_THICKNESS = 0.5;
    private final double GROUND_LINE_THICKNESS = 0.5;

    private final Line phase;
    private final Line neutral;
    private final Line ground;
    private int x;
    private int y;
    private double length;
    private List<Point> attachmentPoints;

    public ParallelLinesFigure(int x, int y, double length, int numAttachmentPoints) {
        this.phase = new Line(length, x, y,0);
        this.neutral = new Line(length, x,y + DISTANCE_BETWEEN_LINES,0);
        this.ground = new Line(length, x,y + DISTANCE_BETWEEN_LINES * 2,0);
        this.x = x;
        this.y = y;
        this.length = length;
        this.attachmentPoints = new ArrayList<>();

        // Calculate the interval between points on the top line
        double interval = length / (numAttachmentPoints + 1);

        // Создаем точки присоединений
        for (int i = 1; i <= numAttachmentPoints; i++) {
            int pointX = x +  i *  Utils.get_pixels_from_mm(interval);
            attachmentPoints.add(new Point(pointX , y - DIAMETER_ATTACHMENT_POINT / 2));
        }

        phase.setLineThickness(PHASE_LINE_THICKNESS);
        neutral.setLineThickness(NEUTRAL_LINE_THICKNESS);
        ground.setLineThickness(GROUND_LINE_THICKNESS);
        ground.setDottedLine(5,2);
    }

    public void draw(Graphics2D g) {
        phase.draw(g);
        neutral.draw(g);
        ground.draw(g);


        for (Point p : attachmentPoints) {
            g.fillOval(p.x, p.y , DIAMETER_ATTACHMENT_POINT, DIAMETER_ATTACHMENT_POINT);
        }

    }

}



