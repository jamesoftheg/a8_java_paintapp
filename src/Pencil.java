import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pencil extends GeometricObject {
    public Pencil(double xStart, double yStart, Color lineColor) {
        super(xStart, yStart, lineColor);
    }

    @Override
    void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.beginPath();
        gc.lineTo(getxStart(), getyStart());
    }
}
