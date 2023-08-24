import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main  {
    private static int height  = 2480;
    private static int width = 3508 ;


    public static void main(String[] args) {

        String[][] data = {
                {"Группа №", "1","2"},
                {"Потребитель", "Освещение","sdsddfdfdfdfddfdf222"},
                {"Iном., А", "16",""},
                {"Фаза", "А",""}
        };

        // Создаем буферизированное изображение и получаем его графику
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Устанавливаем белый фон с прозрачностью
        g2d.setColor(new Color(255, 255, 255)); // Прозрачность 0
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());


        // Создаем экземпляры LabeledShape
        Rectangle2D rectangle = new Rectangle2D.Double(100, 100, 200, 200);
        LabeledShape rectangleWithLabel = new LabeledShape(rectangle, "Rectangle", new Point(302, 90));

        Ellipse2D ellipse = new Ellipse2D.Double(400, 100, 200, 200);
        LabeledShape ellipseWithLabel = new LabeledShape(ellipse, "Ellipse", new Point(600, 90));

        int[] xPoints = {500, 300, 200};
        int[] yPoints = {400, 400, 600};
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        LabeledShape triangleWithLabel = new LabeledShape(triangle, "Triangle", new Point(250, 390));




        // Рисуем рамку
        LeafFrame leafFrame = new LeafFrame();

        // Длина линии питания в мм
        int length = 200;
        // Чтобы линия питания была по центру
        // Длина линии питания в пикселях делится на 2 и вычитается из центра рамки, чтобы расположить линию по центру
        ParallelLinesFigure figure = new ParallelLinesFigure(leafFrame.centerPointX - Utils.get_pixels_from_mm(length) / 2, 300, length, 10);

        Point point = figure.attachmentPoints.get(0);
        Avtomat avtomat = new Avtomat(point.x, point.y, figure.getNeutral().startPointY, figure.getGround().startPointY, "QF1");
        Table table = new Table(avtomat.line2.endPointX, avtomat.line2.endPointY + 200, data);



        // Заливаем фигуры красным
//        g2d.setColor(Color.RED);
//        g2d.fill(rectangle);
//        g2d.fill(triangle);

        // Рисуем фигуры с подписями на буферизированном изображении
//        rectangleWithLabel.draw(g2d);
//        ellipseWithLabel.draw(g2d);
//        triangleWithLabel.draw(g2d);
        figure.draw(g2d);
        leafFrame.draw(g2d);
        avtomat.draw(g2d);
        table.draw(g2d);
        //line.draw(g2d);



        // Очищаем и закрываем графику
        g2d.dispose();


        // Сохраняем изображение на диск
        try {
            ImageIO.write(image, "png", new File("labeled_shapes.png"));
            System.out.println("Изображение сохранено успешно.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
