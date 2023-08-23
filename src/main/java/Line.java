import java.awt.*;
import java.awt.image.BufferedImage;

class Line {
    // Длина линии
    double mmLength;
    // Координаты
    int startPointX;
    int startPointY;
    int endPointX;
    int endPointY;
    int centerPointX;
    int centerPointY;
    // Угол 0 - вправо, 90 - вниз
    double directionDegrees;
    // Пунктирная линия
    boolean isDottedLine = false;
    // Паттерн для пунктирной линии
    float[] dashPattern;

    Color color = Color.BLACK;
    // Толщина линии в пикселях
    int lineThickness = 1;

    public Line(double mmLength, int startPointX, int startPointY, double directionDegrees) {
        this.mmLength = mmLength;
        this.startPointX = startPointX;
        this.startPointY = startPointY;
        this.directionDegrees = directionDegrees;

        // Конвертируем миллиметры в пиксели с учетом DPI
        int pixelLength = Utils.get_pixels_from_mm(this.mmLength);

        // Рассчитываем конечные координаты на основе направления
        double angleRadians = Math.toRadians(directionDegrees);
        this.endPointX = (int) (startPointX + pixelLength * Math.cos(angleRadians));
        this.endPointY = (int) (startPointY + pixelLength * Math.sin(angleRadians));

        this.centerPointX = Math.abs(this.startPointX - this.endPointX) / 2;
        this.centerPointY = Math.abs(this.startPointY - this.endPointY) / 2;
    }

    public Line(double mmLength, double startPointX_mm, double startPointY_mm, double directionDegrees) {
        this.mmLength = (Double) mmLength;
        this.directionDegrees = directionDegrees;



        // Конвертируем миллиметры в пиксели с учетом DPI
        this.startPointX = Utils.get_pixels_from_mm(startPointX_mm);
        // Конвертируем миллиметры в пиксели с учетом DPI
        this.startPointY = Utils.get_pixels_from_mm(startPointY_mm);

        // Конвертируем миллиметры в пиксели с учетом DPI
        int pixelLength = Utils.get_pixels_from_mm(this.mmLength);

        // Рассчитываем конечные координаты на основе направления
        double angleRadians = Math.toRadians(directionDegrees);
        this.endPointX = (int) (startPointX + pixelLength * Math.cos(angleRadians));
        this.endPointY = (int) (startPointY + pixelLength * Math.sin(angleRadians));
    }

    public void draw(Graphics2D g2d) {
        // Устанавливаем цвет для рисования
        g2d.setColor(color);

        if (isDottedLine) {
            // Установить пунктирную линию
            g2d.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dashPattern, 0.0f));
        }else {
            g2d.setStroke(new BasicStroke(lineThickness));
        }

        // Рисуем линию
        g2d.drawLine(startPointX, startPointY, endPointX, endPointY);

    }

    public void setDottedLine(int lenght, int gapLength) {
        // Шаблон для пунктирной линии
        this.dashPattern = new float[]{Utils.get_pixels_from_mm(lenght), Utils.get_pixels_from_mm(gapLength)};
        this.isDottedLine = true;

    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setLineThickness(double mmLineThickness) {
        this.lineThickness = Utils.get_pixels_from_mm(mmLineThickness);
        //this.lineThickness = mmLineThickness;
    }
}