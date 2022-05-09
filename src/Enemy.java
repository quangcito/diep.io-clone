import java.util.List;
import java.awt.Color;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;

public class Enemy extends GraphicsGroup {
    private HealthBar healthBar;
    private Path body;
    private double centerX;
    private double centerY;

    /**
     * Constructs an enemy where its center is at (centerX,centerY). Also constructs
     *  under the enemy a health bar based on its health.
     * @param points the vertices of the enemy
     * @param color the fill color of the enemy
     */
    public Enemy(List<Point> points, double centerX, double centerY, Color color, double health) {
        this.centerX = centerX;
        this.centerY = centerY;
        
        body = new Path((points));
        
        add(body);
        body.setCenter(centerX,centerY);
        body.setFillColor(color);
        body.setRotation(Math.random()*360);
        body.setStrokeWidth(3);

        healthBar = new HealthBar(centerX - 20, centerY + 20, health);
        healthBar.setCenter(centerX, centerY + 30);
        add(healthBar);

    }

    /**
     * Makes the enemy move xDirection in the x-axis and move yDirection in the 
     * y-axis. The enemy is also rotating around its center. 
     */
    public void moveAround(double xDirection, double yDirection) {
        moveBy(xDirection,yDirection);
        rotateAround();
    }

    /**
     * Makes the enemy rotate around its center.
     */
    public void rotateAround() {
        getBody().rotateBy(0.5);
    }

    /**
     * Tests for intersections between the given bullet and this enemy body.
     *
     * @return true If the given bullet intersects this enemy (even tangentially).
     */
    public boolean intersectsBullet(Bullet bullet) {
        // return true if the bullet is within the radius of the enemy.
        if (getCenter().distance(bullet.getCenter()) <= (2*Bullet.BULLET_RADIUS)) {
            return true;
        }
        return false;
    }

    /**
     *  Tests to see if the enemy intersects the tank .
     * @param body the shape of the enemy
     * @return true if an enemy touches the tank (even tangentially).
     */
    public boolean intersectsTank(Body body) {
        if (getCenter().distance(body.getCenter()) <= (3*Body.BODY_RADIUS)){
            return true;
        }
        return false;
    }

    public GraphicsObject getBody() {
        return body;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }
}