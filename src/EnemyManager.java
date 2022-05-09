import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.lang.Math;
import java.util.List;

import java.util.ArrayList;


public class EnemyManager {  
    private CanvasWindow canvas;
    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 800;
    private List<Enemy> enemies;
    private Enemy boss;
    
    /**
     * Constructs an enemy manager for the specified window object. Also creates
     * the boss.
     */
    public EnemyManager(CanvasWindow canvas){
        this.canvas = canvas;
        enemies = new ArrayList<>();
        generateBoss();
    }
    
    /**
     * Constructs a random combination of triangle, square, or pentagon enemies. 
     */
    public void generateEnemies(){
        long randomNumberOfEnemies = Math.round(Math.random()*3);
        
        for(int i = 0; i < randomNumberOfEnemies; i++){
            Enemy pentagonEnemy = new Enemy(List.of(new Point(0,   0),
                new Point(20,     15),
                new Point(12.5,  37.5),
                new Point(-12.5,     37.5),
                new Point(-20,     15),
                new Point(0,  0)),
                Math.random()*CANVAS_WIDTH/4, Math.random()*CANVAS_HEIGHT*1.5, Color.green, 50);

            Enemy squareEnemy = new Enemy(List.of(
                new Point(0,   0),
                new Point(30,     0),
                new Point(30,  30),
                new Point(0,30)), 
                Math.random()*CANVAS_WIDTH*1.5, Math.random()*CANVAS_HEIGHT/4, Color.orange,50);

            Enemy triangleEnemy = new Enemy(List.of(
                new Point(20,   20),
                new Point(42.5,     57.5),
                new Point(-2.5,  57.5) ),
                Math.random()*CANVAS_WIDTH, Math.random()*CANVAS_HEIGHT/2, Color.MAGENTA,50);
            
            enemies.add(pentagonEnemy);
            enemies.add(triangleEnemy);
            enemies.add(squareEnemy);
            
            canvas.add(pentagonEnemy);
            canvas.add(triangleEnemy);
            canvas.add(squareEnemy);
        }
    }

    /**
     * Constructs a boss.
     */
    public void generateBoss(){
        boss = new Enemy(List.of(
                new Point(0,   0),
                new Point(160,     160),
                new Point(160,  0),
                new Point(0,  160)
                ), 100,100 , Color.black,100);
        enemies.add(boss);
        canvas.add(boss);
    }


    /**
     * Checks whether the bullet hits any of the enemies, reduce the health 
     * intersecting enemy if so.
     */
    public boolean bulletHit(Bullet bullet) {
        for (Enemy enemy : enemies) {
            if (enemy.intersectsBullet(bullet)) {
                if (enemy.equals(boss)) {
                    boss.getHealthBar().loseHealth(3);
                }
                else {
                enemy.getHealthBar().loseHealth(25);
                }    
                if (enemy.getHealthBar().die()) {
                    canvas.remove(enemy);
                    enemies.remove(enemy);
            }
            return true;
        }
        }
        return false;
    }

    /**
     * Checks whether an enemy or boss hits the tank. The tank loses 25 percent of 
     * its health if an enemy hits the tank, but loses all of its health if the boss
     * hits the tank. 
     */
    public boolean tankHit(Body body) {
        
        if (boss.intersectsTank(body)) {
                body.getHealthBar().loseHealth(100);
                return true;
            }

        for (Enemy enemy : enemies){
            if (enemy.intersectsTank(body)) {
                body.getHealthBar().loseHealth(25);
                canvas.remove(enemy);
                enemies.remove(enemy);
                return true;
            }
        
        }
        return false;
    }

    public List<Enemy> getEnemiesList(){
        return enemies;
    }

    public Enemy getBoss(){
        return boss;
    }
}