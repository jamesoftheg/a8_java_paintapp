import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends GeometricObject {

    double width = getxEnd()-getxStart();
    double height = getyEnd()-getyStart();

    public Rectangle(double xStart, double yStart, double xEnd, double yEnd, Color lineColor) {
        super(xStart, yStart, xEnd, yEnd, lineColor);
    }

    @Override
    void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.fillRect(getxStart(),getyStart(),width,height);
    }
}
