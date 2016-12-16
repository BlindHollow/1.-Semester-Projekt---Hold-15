/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bytof
 */
public class FXMLController implements Initializable {

    @FXML
    private ProgressBar healthbar;
    @FXML
    private ProgressBar hungerbar;
    @FXML
    private ProgressBar thirstbar;
    @FXML
    private ProgressBar illnessbar;
    @FXML
    private Label invSlot1;
    @FXML
    private Label invSlot2;
    @FXML
    private Label invSlot3;
    @FXML
    private Label invSlot4;
    @FXML
    private Label tempLabel;
    @FXML
    private Button btnNewGame;
    @FXML
    private AnchorPane welcomeStartAnchor;
    @FXML
    private Button mainBtn;
    @FXML
    private Button btnStartGame;
    @FXML
    private Button btnContinue;
    @FXML
    private Button btnQuit;
    @FXML
    private TextField enterPlayerName;
    @FXML
    private Label labelPlayerName;
    Stage stage = null;
    Parent root = null;
    private Parser pars;

    /**
     * initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pars = new Parser();
        onPlayerName();
    }

    /**
     * Handles button actions. Gets the source of the event, and handles the
     * action accordingly.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnContinue) {
            stage = (Stage) btnContinue.getScene().getWindow(); //get reference to the button's stage        
            loadScene("GameScene");
        } else if (event.getSource() == btnNewGame) {
            welcomeStartAnchor.setVisible(true);
        } else if (event.getSource() == btnStartGame) {
            NewFXMain.spil.player.setName(enterPlayerName.getText());
            stage = (Stage) btnStartGame.getScene().getWindow();
            loadScene("GameScene");
        } else {
            pars.pickUpItem("fireaxe");
            this.updateInventory();
        }
    }

    /**
     * Used to load a new scene, based on a string, which is the name of the
     * scenes fxml
     *
     * @param sceneName
     * @throws IOException
     */
    @FXML
    private void loadScene(String sceneName) throws IOException {
        root = FXMLLoader.load(getClass().getResource(sceneName + ".fxml")); //load up OTHER FXML document
        //create a new scene with root and set the stage
        Scene scene = new Scene(root, 1200, 680);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Quits the game, by hiding the scene from which the event was called
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void quitGame(ActionEvent event) throws IOException {
        //Giver mulighed for at stoppe spillet
        btnQuit.setOnAction(e -> AlertBox.displayQuitBox("Quit?","Are you sure?"));
    }

    /**
     * Moves the player to another room, dependeing on what is clicked.
     *
     * @param event
     */
    @FXML
    private void onMouseClicked(MouseEvent event) {
        if (event.getX() < 50 && event.getX() > 0 && event.getY() < 365 && event.getY() > 315) {
            pars.moveToRoom("west");
            tempLabel.setText("You walked west");
            updateStats();
        } else if (event.getX() < 1000 && event.getX() > 950 && event.getY() < 365 && event.getY() > 315) {
            pars.moveToRoom("east");
            tempLabel.setText("You walked east");
            updateStats();
        } else if (event.getX() < 525 && event.getX() > 475 && event.getY() < 50 && event.getY() > 0) {
            pars.moveToRoom("north");
            tempLabel.setText("You walked north");
            updateStats();
        } else if (event.getX() < 525 && event.getX() > 475 && event.getY() < 680 && event.getY() > 630) {
            pars.moveToRoom("south");
            tempLabel.setText("You walked south");
            updateStats();
        } else if (event.getX() < 50 && event.getX() > 0 && event.getY() < 50 && event.getY() > 0) {
            pars.moveToRoom("northwest");
            tempLabel.setText("You walked northwest");
            updateStats();
        } else if (event.getX() < 1000 && event.getX() > 950 && event.getY() < 50 && event.getY() > 0) {
            pars.moveToRoom("northeast");
            tempLabel.setText("You walked northeast");
            updateStats();
        } else if (event.getX() < 50 && event.getX() > 0 && event.getY() < 680 && event.getY() > 630) {
            pars.moveToRoom("southwest");
            tempLabel.setText("You walked southwest");
            updateStats();
        } else if (event.getX() < 1000 && event.getX() > 950 && event.getY() < 680 && event.getY() > 630) {
            pars.moveToRoom("southeast");
            tempLabel.setText("You walked southeast");
            updateStats();
        }

    }

    private void updateRoom() { //TODO load picture of room, load pictures of exits
        HashMap<String, Room> exits = pars.getRoomExits();
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            switch (exit) {
                case "north":
                    break;
                case "south":
                    break;
                case "west":
                    break;
                case "east":
                    break;
                case "northwest":
                    break;
                case "northeast":
                    break;
                case "southwest":
                    break;
                case "southeast":
                    break;
                default:
                    break;
            }
        }
        updateItemsInRoom();
    }

    private void updateItemsInRoom() { //TODO load pictures of items
        int i = 0;
        for (String s : pars.getItemsInRoom()) {
            switch (i) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
    }

    private void updateInventory() {
        int i = 0;
        for (String s : pars.getPlayerInventory()) {
            switch (i) {
                case 0:
                    invSlot1.setText(s);
                    break;
                case 1:
                    invSlot2.setText(s);
                    break;
                case 2:
                    invSlot3.setText(s);
                    break;
                case 3:
                    invSlot4.setText(s);
                    break;
                default:
                    break;
            }
            i++;
        }
    }

    /**
     * Updates the players status on the gui.
     */
    private void updateStats() {
        double passnumber = pars.getPlayerThirst();
        this.thirstbar.setProgress(passnumber / 100);
        passnumber = pars.getPlayerHunger();
        this.hungerbar.setProgress(passnumber / 100);
        passnumber = pars.getPlayerHealth();
        this.healthbar.setProgress(passnumber / 100);
        passnumber = pars.getPlayerIllness();
        this.illnessbar.setProgress(passnumber / 100);
    }

    @FXML
    private void onPlayerName(MouseEvent event) {
        labelPlayerName.setText(NewFXMain.spil.player.getName());
    }

    private void onPlayerName() {
        if (labelPlayerName != null) {
            labelPlayerName.setText(NewFXMain.spil.player.getName());
        }
    }
}
