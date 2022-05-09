import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;

import edu.macalester.graphics.events.Key;
import java.lang.Math;

/**
 * The hardest Java version of Diep.io .
 */
public class DiepGame {
    private CanvasWindow canvas;
    private Cannon cannon;
    private Body body;
    private HealthBar healthBar;
    private EnemyManager enemyManager;
    private Bullet bullet;
    private List<Bullet> bullets;
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 1000;
    private int enemyTracker = 0;
    private int loseTracker = 0;
    private Screen loseScreen;
    private Screen winScreen;
    private static Screen startScreen;
    private GraphicsText name;
    private int bossHealthGainTracker = 0;
    private Frame frame;
  
    public static void main(String[] args) {
        startScreen = new Screen("diep.io","Play",Color.white);
    }
    
    /**
     * Instantiates all the objects needed to create the game.
     */
    public DiepGame() {
        canvas = new CanvasWindow("diep.io", CANVAS_WIDTH, CANVAS_HEIGHT);
        frame = new Frame();
        canvas.setBackground(new Color(0xF5F5DC));
        cannon = new Cannon(0,0,0);
        body = new Body(CANVAS_WIDTH/2,CANVAS_HEIGHT/2);
        healthBar = new HealthBar(body.getX(), body.getY() + Body.BODY_RADIUS*2.5, Body.BODY_RADIUS*2);
        enemyManager = new EnemyManager(canvas);
        bullets = new ArrayList<>();
        name =  new GraphicsText();
        name.setText(startScreen.getText());
        name.setFont(FontStyle.BOLD, 14);
    }

    public void run(){
        createFrame();
        createTank();
        play();
    }

    public void play() {
        shootBullet();
        canvas.animate(() ->{
            moveTank();
            loseGame();
            winGame();
            bossRegenerateHealth();
            generateEnemies();
            moveEnemies();
            bulletList();
        });
    }

    /**
     * Constructs a frame.
     */
    public void createFrame() {
        canvas.add(frame);
    }

    /**
     * Creates a new bullet at the center of tank and adds that bullet to a list of bullets.
     */
    public void reloadBullet() {
        bullet = new Bullet(canvas, body.getCenterX(), body.getCenterY(), 10);
        canvas.add(bullet);
        bullets.add(bullet);
    }

    /**
     * Constructs the tank by combining a body, cannon, and name that he user inputted
     * on the screens.
     */
    public void createTank(){
        canvas.add(body);
        canvas.add(cannon, body.getBody().getCenter().getX(), body.getBody().getCenter().getY() - Body.BODY_RADIUS/2);
        cannon.setAnchor(0, Body.BODY_RADIUS/2);
        canvas.add(name);
        name.setCenter(body.getCenterX(),body.getCenterY()-50);
    }

    /**
     *  Checks to see if the player won the game. The player wins the game if the 
     *  boss's health is zero.
     */
    public void winGame() {
        if(enemyManager.getBoss().getHealthBar().die()){
            bullets.clear();
            enemyManager.getEnemiesList().clear();
            winScreen = new Screen("You Win!","Play Again", Color.green);
            enemyManager.getBoss().getHealthBar().resetHealth();
            canvas.removeAll();
            name.setText(winScreen.getText());
            name.setFont(FontStyle.BOLD, 14);
        }
    }

    /**
     *  Checks to see if the player lost the game. The player loses the game if the
     * tank's health is zero or if the boss is no longer visible on the canvas. 
     */
    public void loseGame() {
        if (body.getHealthBar().die() && loseTracker==0 ||enemyManager.getBoss().getCenter().getY()>CANVAS_HEIGHT && loseTracker==0){
            bullets.clear();
            enemyManager.getEnemiesList().clear();
            loseScreen = new Screen("You Lose","Play Again",Color.RED);
            canvas.removeAll();
            body.getHealthBar().resetHealth();
            loseTracker++;
            name.setText(loseScreen.getText());
            name.setFont(FontStyle.BOLD, 14);
        }
    }

    /**
     * The boss gains back 10 percent of its initial total health every 6 seconds.
     */
    public void bossRegenerateHealth() {
        if (bossHealthGainTracker % 360 == 0) {
            enemyManager.getBoss().getHealthBar().gainHealth(10);
        }
        bossHealthGainTracker++;
    }

    /**
     * The cannon rotates around the center of the tank to face towards the mouse 
     * pointer when the mouse pointer moves. The tank shoots a bullet when the user
     * clicks on the canvas. The cannon also sets its location to the mouse pointer 
     *  after shooting. 
     */
    public void shootBullet() {
        canvas.onClick(event -> {
            reloadBullet();
            double shootingAngle = Math.atan2(
                event.getPosition().getY() - body.getCenterY(), 
                event.getPosition().getX() - body.getCenterX());
            bullet.setShootingAngle(shootingAngle);

            double cannonAngle = Math.toDegrees(shootingAngle);
            cannon.setRotation(cannonAngle);
        });

        canvas.onMouseMove((event) -> {
            double cannonAngle = Math.toDegrees(Math.atan2(
                event.getPosition().getY() - body.getCenterY(), 
                event.getPosition().getX() - body.getCenterX()));
            cannon.setRotation(cannonAngle);
            });
    }

    /**
     * Creates a random number of enemies every second if the boss is still alive.
     */
    public void generateEnemies() { 
        enemyManager.tankHit(body);
            if (enemyTracker % 60 == 0 && enemyManager.getEnemiesList().contains(enemyManager.getBoss())) {
                enemyManager.generateEnemies();
            } 
        enemyTracker++;
    }

    /**
     * Makes the enemies move towards the bottom right corner of the canvas. All
     * of the enemies moves at the same speed except for the boss. The boss moves
     * slower.
     */
    public void moveEnemies() {
        for (Enemy enemy: enemyManager.getEnemiesList()) {
            if (enemy.equals(enemyManager.getBoss())) {
                enemyManager.getBoss().moveAround(Math.random()/2, Math.random()/2);
            }
            else {
                enemy.moveAround(Math.random()*2, Math.random()*2);
            }
        }
    }

    /**
     * Makes the bullets move when they are added to the canvas. The bullets are
     * removed from the canvas if they hit an enemy or borders of the canvas.
     */
    public void bulletList() {
        List<Bullet> bulletsToDel = new ArrayList<>();
            for (Bullet bullet : bullets) {
                bullet.move(1);
                if (enemyManager.bulletHit(bullet)) {
                    bulletsToDel.add(bullet);
                }
            }
            removeBullets(bulletsToDel);
            bullets.removeIf(bullet -> bullet.canvasCollision());
    }

    /**
     * This method helps prevent the concurrent modification error. 
     * @param bulletsToDel a list that consists of bullets that need to be removed 
     *                     from the canvas and bullets list.
     */
    private void removeBullets(List<Bullet> bulletsToDel) {
        for (Bullet bullet : bulletsToDel) {
            canvas.remove(bullet);
            bullets.remove(bullet);
        }
    }

    /**
     * Allows the user to move the tank with the W-A-S-D keys. The tank moves 
     * diagonally if two keys are pressed at the same time. If the two keys pressed
     * at the same time are W and S or A and D, then the tank doesn't move.
     */
    public void moveTank() {
        Set<Key> key = canvas.getKeysPressed(); 
        if (key.contains(Key.S) && key.contains(Key.D)) {
            body.setCenter(body.getCenterX() + 1, body.getCenterY() + 1);
            cannon.setCenter(cannon.getCenterX() + 1, cannon.getCenterY() + 1);
            healthBar.setPosition(healthBar.getX() + 1, healthBar.getY() + 1);
            name.setCenter(body.getCenterX(), body.getCenterY() - 50);
        }
        else if (key.contains(Key.S) && key.contains(Key.A)) {
            body.setCenter(body.getCenterX() - 1, body.getCenterY() + 1);   
            cannon.setCenter(cannon.getCenterX() - 1, cannon.getCenterY() + 1); 
            healthBar.setPosition(healthBar.getX() - 1, healthBar.getY() + 1);
            name.setCenter(body.getCenterX(), body.getCenterY() - 50);
        }
        else if (key.contains(Key.W) && key.contains(Key.D)) {
            body.setCenter(body.getCenterX() + 1, body.getCenterY() - 1);
            cannon.setCenter(cannon.getCenterX() + 1, cannon.getCenterY() - 1);
            healthBar.setPosition(healthBar.getX() + 1, healthBar.getY() - 1);
            name.setCenter(body.getCenterX(), body.getCenterY() - 50);
        }
        else if (key.contains(Key.W) && key.contains(Key.A)) {
            body.setCenter(body.getCenterX() - 1, body.getCenterY() - 1);
            cannon.setCenter(cannon.getCenterX() - 1, cannon.getCenterY() - 1);
            healthBar.setPosition(healthBar.getX() - 1, healthBar.getY() - 1);
            name.setCenter(body.getCenterX(), body.getCenterY() - 50);
        }
        else if(key.contains(Key.W)) {
            body.setCenter(body.getCenterX(), body.getCenterY() - 1);
            cannon.setCenter(cannon.getCenterX(), cannon.getCenterY() - 1);
            healthBar.setPosition(healthBar.getX(), healthBar.getY() - 1);
            name.setCenter(body.getCenterX(), body.getCenterY() - 50);
        }
        else if (key.contains(Key.A)) {
            body.setCenter(body.getCenterX() - 1, body.getCenterY());
            cannon.setCenter(cannon.getCenterX() - 1, cannon.getCenterY());
            healthBar.setPosition(healthBar.getX() - 1, healthBar.getY());
            name.setCenter(body.getCenterX(), body.getCenterY() - 50);
        }
        else if (key.contains(Key.S)) {
            body.setCenter(body.getCenterX(), body.getCenterY() + 1);
            cannon.setCenter(cannon.getCenterX(), cannon.getCenterY() + 1);
            healthBar.setPosition(healthBar.getX(), healthBar.getY() + 1);
            name.setCenter(body.getCenterX(), body.getCenterY() - 50);
        }
        else if (key.contains(Key.D)) {
            body.setCenter(body.getCenterX() + 1, body.getCenterY());
            cannon.setCenter(cannon.getCenterX() + 1, cannon.getCenterY());
            healthBar.setPosition(healthBar.getX() + 1, healthBar.getY());
            name.setCenter(body.getCenterX(), body.getCenterY() - 50);
        }
    }
}