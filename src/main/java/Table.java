import java.awt.*;
import java.awt.geom.AffineTransform;

public class Table {

    private final int WIDTH_1_COLUMN = 300;
    private final int WIDTH_COLUMNS = 200;
    private final int HEIGHT_2_ROW = 200;
    private final int HEIGHT_ROWS = 50;
    private final int MAX_LENGTH_ROW_POTREBITEL = 10;
    private  int x;
    private int y;
    String[][] data;

    public int tableWidth = 0;
    public int tableHeight = 0;

    public Table(int x, int y, String[][] data) {
        this.x = x;
        this.y = y;
        this.data = data;
    }

    public void draw(Graphics2D g2d){

        // Определяем сколько строк и столбцов будет
        int rows = data.length;
        int cols = data[0].length;

        int[] columnWidths = new int[cols];
        int[] rowHeights = new int[rows];

        // Настройка высоты каждой строки
        for (int i = 0; i < rows; i++) {
            if (i == 1){
                rowHeights[i] = HEIGHT_2_ROW;
            }else rowHeights[i] = HEIGHT_ROWS;
        }

        // Настройка ширины каждого столбца
        for (int i = 0; i < cols; i++) {
            if (i == 0){
                columnWidths[i] = WIDTH_1_COLUMN;
            }else columnWidths[i] = WIDTH_COLUMNS;
        }

        // Измерение таблицы
        for (int width : columnWidths) {
            tableWidth += width;
        }
        for (int height : rowHeights) {
            tableHeight += height;
        }

        g2d.setColor(Color.BLACK);

        Font font = new Font("Arial", Font.PLAIN, Utils.get_pixels_from_mm(3));
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();


        int xTable = x - columnWidths[0] - columnWidths[1] / 2;
        int yTable = y;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String s = data[row][col];
                g2d.drawRect(xTable, yTable, columnWidths[col], rowHeights[row]);
                // Длина строки в пикселях
                int labelWidth = metrics.stringWidth(s);

                if (col == 0 && row != 1){
                    // Выравнивание текста по правому краю
                    int textY = yTable + (rowHeights[row] - g2d.getFontMetrics().getHeight()) / 2 + g2d.getFontMetrics().getAscent();
                    g2d.drawString(s, xTable + 5, textY);
                }else if (row == 1){
                    // Поворот текста на 90 градусов для первой строки
                    AffineTransform original = g2d.getTransform();
                    AffineTransform rotation = new AffineTransform();
                    rotation.setToTranslation(xTable , yTable );
                    rotation.rotate(-Math.PI / 2);
                    g2d.setTransform(rotation);

                    int textX = - HEIGHT_2_ROW;
                    int textY = (columnWidths[col] - g2d.getFontMetrics().getHeight()) / 2 + g2d.getFontMetrics().getAscent();
                    String text = data[row][col];
                    // Если текст длинее MAX_LENGTH_ROW_POTREBITEL символов, текст переносится
                    if (text.length() > MAX_LENGTH_ROW_POTREBITEL) {
                        String part1 = text.substring(0, MAX_LENGTH_ROW_POTREBITEL);
                        String part2 = text.substring(MAX_LENGTH_ROW_POTREBITEL);
                        g2d.drawString(part1, textX, textY);
                        textY += g2d.getFontMetrics().getHeight();
                        g2d.drawString(part2, textX, textY);

                    }else {
                        g2d.drawString(data[row][col], textX, textY);
                    }
                    g2d.setTransform(original);
                }
                else {
                    // Выравнивание текста по центру
                    int textX = xTable + (columnWidths[col] - g2d.getFontMetrics().stringWidth(data[row][col])) / 2;
                    int textY = yTable + (rowHeights[row] - g2d.getFontMetrics().getHeight()) / 2 + g2d.getFontMetrics().getAscent();
                    g2d.drawString(s, textX, textY);
                }

                xTable += columnWidths[col];
            }
            xTable = x - columnWidths[0] - columnWidths[1] / 2;
            yTable += rowHeights[row];
        }
    }
}
