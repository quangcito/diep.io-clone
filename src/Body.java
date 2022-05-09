import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;

import java.awt.Color;

public class Body extends GraphicsGroup {
    public static final double BODY_RADIUS = 20.0;
    Ellipse body;
    HealthBar healthBar;

    /**
     * Constructs the tanks body centered at (centerX,centerY). Also constructs the
     * tanks health bar under the tank.
     */
    public Body(double centerX, double centerY) {
        body = new Ellipse(0,0, BODY_RADIUS*2, BODY_RADIUS*2);
        body.setFillColor(Color.BLUE);
        body.setStrokeWidth(5);
        body.setCenter(centerX, centerY);
        add(body);

        healthBar = new HealthBar(centerX - 20, centerY + 20, 50);
        healthBar.setCenter(centerX, centerY + 30);
        add(healthBar);
    }

    public Ellipse getBody() {
        return body;
    }

    public HealthBar getHealthBar(){
        return healthBar;
    }

    public double getCenterX(){
        return getCenter().getX();
    }
    
    public double getCenterY(){
        return getCenter().getY();
    }
}