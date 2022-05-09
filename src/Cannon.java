import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Cannon extends Rectangle {

    /**
     * Constructs the tanks cannon with its top left corner at (xPosition,yPosition).
     * @param initialAngle the degree at which the rectangle is initially rotated by.
     */
    public Cannon(double xPosition, double yPosition, double initialAngle) {
        super(xPosition, yPosition, 40, Body.BODY_RADIUS);
        this.setAnchor(xPosition,yPosition);
        this.setRotation(initialAngle);
        this.setFillColor(Color.DARK_GRAY);
        this.setStrokeWidth(5);
    }

    public double getCenterX(){
        return getCenter().getX();
    }
    
    public double getCenterY(){
        return getCenter().getY();
    }
}
