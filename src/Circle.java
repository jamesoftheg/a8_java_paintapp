import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends GeometricObject {

    double diameter = (getxEnd() - getxStart()) + (getyEnd() - getyStart());
    double radius = (diameter / 2);

    double circleX = getxStart()-radius;
    double circleY = getyStart()-radius;

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Circle(double xStart, double yStart, double xEnd, double yEnd, Color lineColor) {
        super(xStart, yStart, xEnd, yEnd, lineColor);
    }

    @Override
    void draw(GraphicsContext gc) {
        super.draw(gc);
        if (getxStart() > getxEnd()) {
            circleX = circleX + radius;
        }
        if (getxStart() > getxEnd()) {
            circleY = circleY + radius;
        }
        gc.fillOval(circleX, circleY, diameter, diameter);
    }
}