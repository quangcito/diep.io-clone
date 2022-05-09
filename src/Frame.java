import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.*;

/**
 * Represents a frame (wall) on the canvas.
 */
public class Frame extends GraphicsGroup {   
    public Frame() {
        super(0, 0);
        drawFrame();
    }

    /**
     * Method draws the frame containing 4 lines on a rectangle canvas
     */
    public void drawFrame() {
        List<Line> lines = new ArrayList<>();
        Line line = new Line(0, 0, 0, 0);

        line.setStartPosition(0, 0);
        line.setEndPosition(0, DiepGame.CANVAS_HEIGHT);
        line.setStrokeColor(Color.BLACK);
        line.setStrokeWidth(10);
        lines.add(line);

        line = new Line(0, 0, 0, 0);
        line.setStartPosition(0, 0);
        line.setEndPosition(DiepGame.CANVAS_WIDTH, 0);
        line.setStrokeColor(Color.BLACK);
        line.setStrokeWidth(10);
        lines.add(line);

        line = new Line(0, 0, 0, 0);
        line.setStartPosition(DiepGame.CANVAS_WIDTH, 0);
        line.setEndPosition(DiepGame.CANVAS_WIDTH, DiepGame.CANVAS_HEIGHT);
        line.setStrokeColor(Color.BLACK);
        line.setStrokeWidth(10);
        lines.add(line);

        line = new Line(0, 0, 0, 0);
        line.setStartPosition(0, DiepGame.CANVAS_HEIGHT);
        line.setEndPosition(DiepGame.CANVAS_WIDTH, DiepGame.CANVAS_HEIGHT);
        line.setStrokeColor(Color.BLACK);
        line.setStrokeWidth(10);
        lines.add(line);

        lines.forEach(this::add);
    }
}
