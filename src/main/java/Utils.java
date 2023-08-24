import java.awt.*;
import java.util.List;

public class Utils {

    static double height  = 2480;
    static double width = 3508 ;
    static int dpi = 300;
    static double INCH = 25.4 ;


    static double mm_height  = Utils.height / Utils.dpi * Utils.INCH;
    static double mm_width = Utils.width / Utils.dpi * Utils.INCH;
    private static final int DIAMETER_ATTACHMENT_POINT_PX = Utils.get_pixels_from_mm(1.0);




    public static int get_pixels_from_mm(double mmLength){
       return  (int) ((mmLength * dpi) / INCH);
    }

    public static void drawPoint(int x, int y, Graphics2D g){
        g.setColor(Color.CYAN);
        g.fillOval(x, y , 30, 30);
    }

    public static void drawPoints(List<Point> attachmentPoints, Graphics2D g){
        if (attachmentPoints != null ) {
            for (Point p : attachmentPoints) {
                g.fillOval(p.x - DIAMETER_ATTACHMENT_POINT_PX / 2, p.y - DIAMETER_ATTACHMENT_POINT_PX / 2, DIAMETER_ATTACHMENT_POINT_PX, DIAMETER_ATTACHMENT_POINT_PX);
            }
        }
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    Метод setStroke() класса BasicStroke позволяет установить параметры для рисования линий. Вот описание параметров этого метода:
//
//        lineWidth (Толщина линии): Определяет толщину рисуемой линии. Это значение измеряется в координатных единицах, как было объяснено ранее.
//
//        endCap (Тип концевой точки): Определяет, каким образом линия завершается. Допустимые значения:
//
//        BasicStroke.CAP_BUTT: Концевые точки обрезаны в соответствии с конечными координатами.
//        BasicStroke.CAP_ROUND: Концевые точки закруглены.
//        BasicStroke.CAP_SQUARE: Концевые точки прямоугольны и продолжаются за пределы конечных координат.
//        lineJoin (Тип соединения линий): Определяет, каким образом соединяются сегменты линий при образовании углов. Допустимые значения:
//
//        BasicStroke.JOIN_BEVEL: Угловые точки обрезаны.
//        BasicStroke.JOIN_MITER: Угловые точки вытянуты, чтобы соединиться в угле.
//        BasicStroke.JOIN_ROUND: Угловые точки закруглены.
//        miterLimit (Предел сращивания): Применяется только с параметром lineJoin равным BasicStroke.JOIN_MITER. Определяет максимальное расстояние между конечной точкой и точкой пересечения угла, при котором будет применено сращивание.
//
//        dashArray (Массив шаблона): Определяет паттерн для пунктирных линий. Массив содержит последовательность значений, где четные индексы задают длину видимого сегмента линии, а нечетные индексы - длину невидимого сегмента линии.
//
//        dashPhase (Начальная фаза паттерна): Определяет, с какой точки начинается паттерн пунктирной линии. Обычно это значение равно 0.
//
//        Значения этих параметров позволяют настраивать стиль рисования линий с использованием объекта BasicStroke. Вы можете настроить параметры в соответствии с вашими требованиями и визуальными предпочтениями.
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////