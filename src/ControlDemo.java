import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.*;

import java.util.ArrayList;

/**
 * Example of using Java FX Controls and a Canvas together
 * COMP-10062 Demo - mark.yendt@mohawkcollege.ca
 */

/**
 * Clear Button
 * Shapes
 * More Shapes
 * Delete First Object
 * Delete Last Object
 *
 */

public class ControlDemo extends Application {

    // Layout sizes
    final double SCREEN_WIDTH = 1000;
    final double SCREEN_HEIGHT = 800;
    final double CONTROL_HEIGHT = 100;

    private GraphicsContext gc, transgc;
    private double xStart, yStart;
    private double xEnd, yEnd;
    private double radius, diameter;

    private ColorPicker lineColorPicker;

    private Color lineColor = Color.BLUE;

    ToggleGroup paintTools = new ToggleGroup();

    ToggleButton penButton = new ToggleButton("Pencil");

    ToggleButton markerButton = new ToggleButton("Marker");

    ToggleButton lineButton = new ToggleButton("Line");

    ToggleButton rectButton = new ToggleButton("Rectangle");

    ToggleButton circButton = new ToggleButton("Circle");

    ArrayList<GeometricObject> objects; // Can I just use this instead?

    // TODO: Private Event Handlers and Helper Methods
    private void pressHandler(MouseEvent me) {
        xStart = me.getX();
        yStart = me.getY();

        if (markerButton.isSelected()) {
            gc.setStroke(lineColorPicker.getValue());
            gc.beginPath();
            gc.lineTo(xStart,yStart);
        }

        else if (penButton.isSelected()) {
            gc.setStroke(lineColorPicker.getValue());
            Pencil p = new Pencil(xStart, yStart, lineColorPicker.getValue());
            objects.add(p);
        }
    }

    private void releaseHandler(MouseEvent me) {
        transgc.clearRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);

        xEnd = me.getX();
        yEnd = me.getY();

        if (lineButton.isSelected()) {
            Line l = new Line(xStart, yStart, xEnd, yEnd, lineColorPicker.getValue());
            objects.add(l);
        }
        else if (circButton.isSelected()) {
            if (xStart > xEnd) {
                double tempX = xStart;
                xStart = xEnd;
                xEnd = tempX;
            }

            if (yStart > yEnd) {
                double tempY = yStart;
                yStart = yEnd;
                yEnd = tempY;
            }

            Circle c = new Circle(xStart, yStart, xEnd, yEnd, lineColorPicker.getValue());
            objects.add(c);
        }
        else if (rectButton.isSelected()) {

            if (xStart > xEnd) {
                double tempX = xStart;
                xStart = xEnd;
                xEnd = tempX;
            }

            if (yStart > yEnd) {
                double tempY = yStart;
                yStart = yEnd;
                yEnd = tempY;
            }

            Rectangle r = new Rectangle(xStart, yStart, xEnd, yEnd, lineColorPicker.getValue());
            objects.add(r);
        }
        else if (markerButton.isSelected()) {
            gc.setStroke(lineColorPicker.getValue());
            gc.beginPath();
            gc.lineTo(me.getX(),me.getY());
        }

        // Redraw the lines.
        gc.setFill(Color.LIGHTYELLOW);

        for(GeometricObject tempLine : objects)
            tempLine.draw(gc);

        for(GeometricObject tempRect : objects)
            tempRect.draw(gc);

        for(GeometricObject tempCirc : objects)
            tempCirc.draw(gc);

        for(GeometricObject tempPen : objects)
            tempPen.draw(gc);
    }

    /**
     * This will draw a "Rubberband" line on the transparent surface above the drawing.
     *
     * @param me The mouse drag event - not used in method
     */
    private void dragHandler(MouseEvent me) {
        transgc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        xEnd = me.getX();
        yEnd = me.getY();

        double width = xEnd-xStart;
        double height = yEnd-yStart;

        if (lineButton.isSelected()) {
            transgc.setStroke(lineColorPicker.getValue());  // getValue gets the CurrentColor on the control
            transgc.setLineWidth(5.0);
            transgc.strokeLine(xStart, yStart, xEnd, yEnd);
            Line tempLine = new Line(xStart, yStart, xEnd, yEnd, lineColorPicker.getValue());
        }
        else if (rectButton.isSelected()) {
            transgc.setFill(lineColorPicker.getValue());
            transgc.fillRect(xStart,yStart,width,height);
            Rectangle tempRect = new Rectangle(xStart, yStart, xEnd, yEnd, lineColorPicker.getValue());
        }
        else if (circButton.isSelected()) {
            transgc.setFill(lineColorPicker.getValue());
            transgc.fillOval(xStart-((xEnd - xStart)/2),yStart-((yEnd - yStart)/2),(xEnd - xStart) + (yEnd - yStart),(xEnd - xStart) + (yEnd - yStart));
            Circle tempCircle = new Circle(xStart, yStart, xEnd, yEnd, lineColorPicker.getValue());
        }
        else if (markerButton.isSelected()) {
            gc.lineTo(me.getX(),me.getY());
            gc.stroke();
        }

        //transgc.setStroke(lineColorPicker.getValue());  // getValue gets the CurrentColor on the control
        //transgc.setLineWidth(5.0);
        //transgc.strokeLine(xStart, yStart, xEnd, yEnd);

        //transgc.fillRect(xStart,yStart,width,height);
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
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.DARKGRAY); // set the size here
        stage.setTitle("Drawing and Java FX Controls"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model - No model yet

        objects = new ArrayList<>(); // Creates an empty list for lines.

        // 2. Create the GUI components - Just a CANVAS for now

        Canvas c = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT - CONTROL_HEIGHT);
        c.relocate(0, CONTROL_HEIGHT);
        Canvas transc = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        transc.relocate(0, CONTROL_HEIGHT);

        // Add JavaFX controls to top pane ...

        /* Any controls must be added to root */

        Label colorLabel = new Label("Line Color");
        colorLabel.relocate(10,65);

        lineColorPicker = new ColorPicker(lineColor);
        lineColorPicker.relocate(70,60);

        Button resetButton = new Button("Reset Colors");
        resetButton.relocate(200,60);
        resetButton.setOnAction(this::resetButtonHandler);

        lineButton.setToggleGroup(paintTools);
        lineButton.setMinWidth(75);
        lineButton.relocate(300,60);

        rectButton.setToggleGroup(paintTools);
        rectButton.setMinWidth(75);
        rectButton.relocate(400,60);

        circButton.setToggleGroup(paintTools);
        circButton.setMinWidth(75);
        circButton.relocate(500,60);

        penButton.setToggleGroup(paintTools);
        penButton.setMinWidth(75);
        penButton.relocate(600,60);

        markerButton.setToggleGroup(paintTools);
        markerButton.setMinWidth(75);
        markerButton.relocate(700,60);

        Button saveButton = new Button("Save");
        saveButton.setMinWidth(75);
        saveButton.relocate(800,60);

        Button undoButton = new Button("Undo");
        undoButton.setMinWidth(75);
        undoButton.relocate(800,20);
        undoButton.setOnAction(this::undoButtonHandler);

        // 3. Add components to the root
        root.getChildren().addAll(c, transc, colorLabel, lineColorPicker, resetButton, circButton, rectButton,
                lineButton, penButton, markerButton, saveButton, undoButton); // Remember to add any labels to here.

        // Create the two graphics contexts

        gc = c.getGraphicsContext2D();
        transgc = transc.getGraphicsContext2D();

        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        // 5. Add Event Handlers and do final setup

        transc.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
        transc.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
        transc.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);

        // 6. Show the stage
        stage.show();
    }

    private void undoButtonHandler(ActionEvent actionEvent) {
        if (!objects.isEmpty()) {
            objects.remove(objects.size() - 1);

            /* Updates the MODEL */
            for (GeometricObject l : objects)
                l.setLineColor(lineColorPicker.getValue());

            for (GeometricObject r : objects)
                r.setLineColor(lineColorPicker.getValue());

            for (GeometricObject c : objects)
                c.setLineColor(lineColorPicker.getValue());

            for (GeometricObject p : objects)
                p.setLineColor(lineColorPicker.getValue());

            /* Updates the VIEW. Resets the canvas, paints it back to initial state */
            gc.setFill(Color.LIGHTYELLOW);
            gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT); // Clears screen.

            for (GeometricObject l : objects)
                l.draw(gc);

            for (GeometricObject r : objects)
                r.draw(gc);

            for (GeometricObject c : objects)
                c.draw(gc);

            for (GeometricObject p : objects)
                p.draw(gc);
        }
    }

    /**
     * Changes all the colors of our lines to the currently selected color.
     * @param actionEvent
     */
    private void resetButtonHandler(ActionEvent actionEvent) {

        /* Updates the MODEL */
        for(GeometricObject l : objects)
            l.setLineColor(lineColorPicker.getValue());

        for(GeometricObject r : objects)
            r.setLineColor(lineColorPicker.getValue());

        for(GeometricObject c : objects)
            c.setLineColor(lineColorPicker.getValue());

        for(GeometricObject p : objects)
            p.setLineColor(lineColorPicker.getValue());

        /* Updates the VIEW. Resets the canvas, paints it back to initial state */
        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT); // Clears screen.

        for(GeometricObject l : objects)
            l.draw(gc);

        for(GeometricObject r : objects)
            r.draw(gc);

        for(GeometricObject c : objects)
            c.draw(gc);

        for(GeometricObject p : objects)
            p.draw(gc);

    }

    /**
     * Make no changes here.
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}