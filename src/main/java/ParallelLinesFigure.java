import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class ParallelLinesFigure {

    public static final int DISTANCE_BETWEEN_LINES_PX = Utils.get_pixels_from_mm(3.0);

    private final double PHASE_LINE_THICKNESS_MM = 0.5;
    private final double NEUTRAL_LINE_THICKNESS_MM = 0.2;
    private final double GROUND_LINE_THICKNESS_MM = 0.2;

    private final Line phase;
    private final Line neutral;
    private final Line ground;
    private int x;
    private int y;
    private double length;
    public List<Point> attachmentPoints;

    public ParallelLinesFigure(int x, int y, double length, int numAttachmentPoints) {
        this.phase = new Line(length, x, y,0);
        this.neutral = new Line(length, x,y + DISTANCE_BETWEEN_LINES_PX,0);
        this.ground = new Line(length, x,y + DISTANCE_BETWEEN_LINES_PX * 2,0);
        this.x = x;
        this.y = y;
        this.length = length;
        this.attachmentPoints = new ArrayList<>();


        // Calculate the interval between points on the top line
        double interval = length / (numAttachmentPoints + 1);

        // Создаем точки присоединений
        for (int i = 1; i <= numAttachmentPoints; i++) {
            int pointX = x +  i *  Utils.get_pixels_from_mm(interval);
            attachmentPoints.add(new Point(pointX , y));
        }

        phase.setLineThickness(PHASE_LINE_THICKNESS_MM);
        neutral.setLineThickness(NEUTRAL_LINE_THICKNESS_MM);
        ground.setLineThickness(GROUND_LINE_THICKNESS_MM);
        ground.setDottedLine(5,2);
    }

    public void draw(Graphics2D g) {
        phase.draw(g);
        neutral.draw(g);
        ground.draw(g);



    }

    public Line getPhase() {
        return phase;
    }

    public Line getNeutral() {
        return neutral;
    }

    public Line getGround() {
        return ground;
    }
}



