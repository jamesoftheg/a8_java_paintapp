import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Basic demonstration of mouse events in action.
 *
 * @author sam scott
 */
public class Testing extends Application {

    // TODO: Instance Variables for View Components and Model
    GraphicsContext gc;
    double  sx, sy;
    double  ex, ey;

    final double SCREENHEIGHT = 600;
    final double SCREENWIDTH = 800;

    // TODO: Private Event Handlers and Helper Methods
    private void pressHandler(MouseEvent me) {
        sx = me.getX();
        sy = me.getY();
        System.out.println("Pressed " + me.getButton() + " at (" + me.getX() + "," + me.getY() + ").");
    }

    private void moveHandler(MouseEvent me) {

        System.out.println("Move " + me.getButton() + " at (" + me.getX() + "," + me.getY() + ").");
    }

    private void dragHandler(MouseEvent me) {
        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(sx,sy,(ex-sx), (ey-sy));
        ex = me.getX();
        ey = me.getY();
        gc.setFill(Color.RED);
        gc.fillRect(sx,sy,(ex-sx), (ey-sy));
        System.out.println("Drag " + me.getButton() + " at (" + me.getX() + "," + me.getY() + ").");
    }

    private void releaseHandler(MouseEvent me) {
        ex = me.getX();
        ey = me.getY();
        gc.setFill(Color.BLUE);
        gc.fillRect(sx,sy,(ex-sx), (ey-sy));
        ex = ey = 0;
        System.out.println("Released " + me.getButton() + " at (" + me.getX() + "," + me.getY() + ").");
    }

    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, SCREENWIDTH, SCREENHEIGHT); // set the size here
        stage.setTitle("Mouse Events"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model
        // 2. Create the GUI components
        Canvas c = new Canvas(SCREENWIDTH, SCREENHEIGHT);
        // 3. Add components to the root
        root.getChildren().add(c);
        // 4. Configure the components (colors, fonts, size, location)
        gc = c.getGraphicsContext2D();
        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0, 0, SCREENWIDTH, SCREENWIDTH);
        // 5. Add Event Handlers and do final setup
        c.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
        c.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
        c.addEventHandler(MouseEvent.MOUSE_MOVED, this::moveHandler);
        c.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);
        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}