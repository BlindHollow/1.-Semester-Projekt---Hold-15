/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.media.*;
import javafx.stage.*;

/**
 *
 * @author bytof
 */
public class NewFXMain extends Application {

    public static Game spil;

    static void startNewGame() {
        spil = new Game();
    }

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
        
        //EXPERIMENTAL MUSIC PLAYER
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
