import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;
import java.awt.Color;

public class Screen {
    private DiepGame game;
    private TextField name;

    /**
     * Creates a menu screen that allows the user to input their name, play the
     * game or quit the game. The text "diep.io" is displayed on the first menu
     * screen.
     * 
     * @param text The message that is displayed if the user wins or loses the game.
     * @param playText the text of the play button.
     * @param backgroundColor the color of the menu screen.
     */
    public Screen(String text, String playText, Color backgroundColor){
        CanvasWindow screen = new CanvasWindow("diep.io", 400, 300);
        screen.setBackground(backgroundColor);
        
        GraphicsText title = new GraphicsText();
        title.setFont("Arial",FontStyle.PLAIN,30);
        title.setText(text);
        title.setCenter(200,50);
        screen.add(title);

        GraphicsText instructions = new GraphicsText();
        instructions.setText("This is the journey of:");
        instructions.setCenter(200,75);
        screen.add(instructions);

        name = new TextField();
        name.setCenter(200,100);
        screen.add(name);

        Button playButton = new Button(playText);
        screen.add(playButton);
        playButton.setCenter(200,150);
        playButton.onClick(()->{
            game = new DiepGame();
            game.run();
        });

        Button quitButton = new Button("Quit");
        screen.add(quitButton);
        quitButton.setCenter(200,200);
        quitButton.onClick(()->screen.closeWindow());
    }
    
    public String getText(){
        return name.getText();
    }
}