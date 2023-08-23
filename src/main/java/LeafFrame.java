import java.awt.*;

public class LeafFrame {
    Line lineLeft;
    Line lineRight;
    Line lineUp;
    Line lineDown;

    int centerPointX;
    int centerPointY;

    public LeafFrame() {
        this.lineLeft = new Line(Utils.mm_height - 10.0, 20.0, 5.0, 90);
        this.lineRight = new Line(Utils.mm_height - 10.0, Utils.mm_width - 5.0, 5.0, 90);
        this.lineUp = new Line(Utils.mm_width - 25.0, lineLeft.startPointX, lineLeft.startPointY, 0);
        this.lineDown = new Line(Utils.mm_width - 25.0, lineLeft.endPointX, lineLeft.endPointY, 0);
        lineLeft.setLineThickness(1);
        lineUp.setLineThickness(1);
        lineRight.setLineThickness(1);
        lineDown.setLineThickness(1);

        this.centerPointX = this.lineLeft.startPointX + Math.abs(this.lineLeft.startPointX - this.lineRight.startPointX) / 2;
        this.centerPointY = this.lineLeft.startPointY +Math.abs(this.lineLeft.startPointY - this.lineLeft.endPointY) / 2;
    }

    public void draw(Graphics2D g2d){
        lineLeft.draw(g2d);
        lineRight.draw(g2d);
        lineUp.draw(g2d);
        lineDown.draw(g2d);

    }
}
