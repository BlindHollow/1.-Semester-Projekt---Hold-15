package worldofzuul;

import java.net.URL;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.media.*;
import javafx.stage.*;

/**
 * This is the main class, it makes a new game, and starts the user interface
 * @author bytof
 */
public class NewFXMain extends Application {

    public static Game spil;

    /**
     * Creates an instance of Class:Game
     */
    static void startNewGame() {
        spil = new Game();
    }

    /**
     * Makes a new stage and load the WelcomeScene to it
     * @param stage The stage that is started
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
        Scene gameScene = new Scene(root, 1200, 680);

        stage.setScene(gameScene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/zombie_icon.jpg"))); //Set icon on titlebar
        stage.setTitle("Death Hospital - The Game"); //Set title on titlebar
        stage.show();
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setOnCloseRequest((WindowEvent we) -> {
            AlertBox.displayQuitBox("Quit?", "Are you sure?");
        });

        final URL resource = getClass().getResource("sounds/darkness.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(javafx.util.Duration.ZERO);
        });
        mediaPlayer.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        startNewGame();
        launch(args);
    }

}
