import java.awt.Color;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

public class HealthBar extends GraphicsGroup {
    private Rectangle greenBar;
    private double totalHP;
    private double currentHP;

    /**
     * Constructs a green and red bar that represents a health bar. The health bar
     * is centered at (xPosition, yPosition).
     * @param totalHP the initial length of the green and red bars.
     */
    public HealthBar(double xPosition, double yPosition, double totalHP) {
        super();
        this.totalHP = totalHP;
        currentHP = totalHP;
        Rectangle greenBar = new Rectangle(xPosition, yPosition, totalHP, 5);
        this.greenBar = greenBar;
        greenBar.setFillColor(Color.GREEN);
        greenBar.setStroked(false);
        greenBar.setCenter(xPosition,yPosition);
        Rectangle redBar = new Rectangle(xPosition, yPosition, totalHP, 5);
        redBar.setFillColor(Color.RED);
        redBar.setStroked(false);
        redBar.setCenter(xPosition,yPosition);
        add(redBar);
        add(greenBar);
    }

    /**
     * Decreases the length of green bar when an object loses health. The red bar 
     * preserves its size.
     * @param percent the percentage amount of health an object loses.
     */
    public void loseHealth(double percent) {
        currentHP -= totalHP * percent/100;
        greenBar.setSize(currentHP, 5);
    }

    /**
     * Increases the length of green bar when an object gains health. The red bar 
     * preserves its size.
     * @param percent the percentage amount of health an object gains. 
     */
    public void gainHealth(double percent) {
        currentHP += totalHP * percent/100;
        greenBar.setSize(currentHP, 5);
    }

    /**
     * Checks to see if the object is alive or dead.
     * @return true if the object has no health.
     */
    public boolean die() {
        if (currentHP <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Makes the object health equal to its initial health.
     */
    public void resetHealth() {
        currentHP = 100;
    }
}