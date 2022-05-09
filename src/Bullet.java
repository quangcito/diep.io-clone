import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

public class Bullet extends Ellipse {
    public static final double BULLET_RADIUS = 10.0;
    private CanvasWindow canvas;
    private Point velocity;
    private double bulletSpeed;

    /**
     * Constructs a bullet centered at (centerX,centerY) on the specified window
     * object.
     * @param bulletSpeed the speed at which the bullet will fire from the cannon.
     */
    public Bullet(CanvasWindow canvas, double centerX, double centerY, double bulletSpeed) {
        super(0, 0, BULLET_RADIUS*2, BULLET_RADIUS*2);
        this.canvas = canvas;        
        setFillColor(Color.RED);
        setStrokeWidth(2);
        setCenter(centerX, centerY);
        this.bulletSpeed = bulletSpeed;
        velocity = new Point(0,0);
    }

    /**
     * Removes a bullet from the canvas if it hits the border of the canvas.
     * @return true if the bullet hits the border of the canvas.
     */
    public boolean canvasCollision() {
        if (getCenter().getX()<=0 || getCenter().getX()>=999 || getCenter().getY()<=0 || getCenter().getY() >= 950) {
            canvas.remove(this);
            return true;
        }
        return false;
    }

    /**
     * Makes a bullet move at a speed of dt.
     */
    public void move(double dt) {
        setCenter(getCenter().add(velocity.scale(dt)));
    }

    /**
     * Changes the velocity of the bullet to the specified angle.
     */
    public void setShootingAngle(double angle) {
        velocity = Point.atAngle(angle).scale(bulletSpeed);
    }
}