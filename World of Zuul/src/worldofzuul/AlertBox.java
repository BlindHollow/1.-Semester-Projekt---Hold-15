/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 *
 * @author mathy
 */
public class AlertBox {

    public static void displayQuitBox(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(500);

        Label label = new Label();
        label.setText(message);
        Button saveButton = new Button("Save game");
        Button closeButton = new Button("Yes");
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());
        closeButton.setOnAction(e -> System.exit(0));
        saveButton.setOnAction(e -> {
            try {
                NewFXMain.spil.saveGame();
                System.exit(0);
            } catch (IOException ex) {
                Logger.getLogger(AlertBox.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton, cancelButton, saveButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
    }
    
    public static void displayHelpBox() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Help window");
        window.setMinWidth(450);
        window.setMinHeight(500);

        Label label = new Label();
        label.setText("This is a help box");
        Button closeButton = new Button("Cancel");
        closeButton.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
    }
    
    public static void displayEndBox(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(450);
        window.setMinHeight(500);

        Label label = new Label();
        Button closeButton = new Button("Quit");
        Button newGameButton = new Button("Start new game");
        label.setText(message);
        closeButton.setOnAction(e -> System.exit(0));
        newGameButton.setOnAction(e -> {
            NewFXMain.startNewGame();
            window.close();
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton, newGameButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
    }

}
