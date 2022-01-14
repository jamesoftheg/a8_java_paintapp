import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GeometricObject {

    private double xStart, yStart;
    private double xEnd, yEnd;

    private Color lineColor;

    public GeometricObject(double xStart, double yStart, double xEnd, double yEnd, Color lineColor) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.lineColor = lineColor;
    }

    public GeometricObject(double xStart, double yStart, Color lineColor) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.lineColor = lineColor;
    }

    public double getxStart() {
        return xStart;
    }

    public double getyStart() {
        return yStart;
    }

    public double getxEnd() {
        return xEnd;
    }

    public double getyEnd() {
        return yEnd;
    }

    void setLineColor(Color newColor) {
        lineColor = newColor;
    }

    public Color getLineColor() {
        return lineColor;
    }

    void draw(GraphicsContext gc) {
        gc.setStroke(lineColor);
        gc.setFill(lineColor);
        gc.setLineWidth(5.0);
    }

}
