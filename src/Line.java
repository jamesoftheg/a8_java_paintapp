import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends GeometricObject {

    public Line(double xStart, double yStart, double xEnd, double yEnd, Color lineColor) {
        super(xStart, yStart, xEnd, yEnd, lineColor);
    }

    @Override
    void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.strokeLine(getxStart(), getyStart(), getxEnd(), getyEnd());
    }

    /*
    private double xStart, yStart;
    private double xEnd, yEnd;

    private Color lineColor;

    public Line(double xStart, double yStart, double xEnd, double yEnd, Color lineColor) {
            this.xStart = xStart;
            this.yStart = yStart;
            this.xEnd = xEnd;
            this.yEnd = yEnd;
            this.lineColor = lineColor;
        }

    void setLineColor(Color newColor) {
        lineColor = newColor;
    }

    void draw(GraphicsContext gc) {
        gc.setStroke(lineColor);
        gc.setLineWidth(5.0);
        gc.strokeLine(xStart, yStart, xEnd, yEnd);
    }

*/

}
