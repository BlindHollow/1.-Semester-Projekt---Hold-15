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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private AnchorPane roomBackground;
    @FXML
    private Button btnHelp;
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
    @FXML
    private ImageView doorNW, doorN, doorNE, doorW, doorE, doorSW, doorS, doorSE;
    @FXML
    private ImageView fireaxeImg, policegunImg, shotgunImg, ramImg, crowbarImg, energybarImg, energydrinkImg, cannedtunaImg, rumImg, medkitImg, vaccinationImg;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView zombie1, zombie2, zombie3;
    @FXML
    private RadioButton radUse, radDrop, radWep;
    @FXML
    private ToggleGroup radButts;
    Stage stage = null;
    Parent root = null;
    private Parser pars;
    private String zom1Str, zom2Str, zom3Str;

    /**
     * initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pars = new Parser();
        onPlayerName();
        updateRoom();
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
        root.setId("gameScene");
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
    @FXML //Åbner en hjælp box
    private void getHelpBox(ActionEvent event) throws IOException {
        AlertBox.displayHelpBox();
    }

    @FXML //Giver mulighed for at stoppe spillet
    private void quitGame(ActionEvent event) throws IOException {
        AlertBox.displayQuitBox("Quit?", "Are you sure?");
    }

    /**
     * Moves the player to another room, dependeing on what is clicked.
     *
     * @param event
     */
    @FXML
    private void onMouseClicked(MouseEvent event) {
        if (event.getSource().equals(doorW)) {
            pars.moveToRoom("west");
            tempLabel.setText("You walked west");
            updateStats();
        } else if (event.getSource().equals(doorE)) {
            pars.moveToRoom("east");
            tempLabel.setText("You walked east");
            updateStats();
        } else if (event.getSource().equals(doorN)) {
            pars.moveToRoom("north");
            tempLabel.setText("You walked north");
            updateStats();
        } else if (event.getSource().equals(doorS)) {
            pars.moveToRoom("south");
            tempLabel.setText("You walked south");
            updateStats();
        } else if (event.getSource().equals(doorNW)) {
            pars.moveToRoom("northwest");
            tempLabel.setText("You walked northwest");
            updateStats();
        } else if (event.getSource().equals(doorNE)) {
            pars.moveToRoom("northeast");
            tempLabel.setText("You walked northeast");
            updateStats();
        } else if (event.getSource().equals(doorSW)) {
            pars.moveToRoom("southwest");
            tempLabel.setText("You walked southwest");
            updateStats();
        } else if (event.getSource().equals(doorSE)) {
            pars.moveToRoom("southeast");
            tempLabel.setText("You walked southeast");
            updateStats();
        } else if (event.getSource().equals(fireaxeImg)) {
            pars.pickUpItem("fireaxe");
        } else if (event.getSource().equals(ramImg)) {
            pars.pickUpItem("ram");
        } else if (event.getSource().equals(vaccinationImg)) {
            pars.pickUpItem("vaccination");
        } else if (event.getSource().equals(zombie1)) {
            pars.attackZombie(zom1Str);
        } else if (event.getSource().equals(zombie2)) {
            pars.attackZombie(zom2Str);
        } else if (event.getSource().equals(zombie3)) {
            pars.attackZombie(zom3Str);
        }
        updateRoom();
        updateInventory();
        updateZombies();
        updateStats();
    }

    private void updateRoom() { //TODO load picture of room, load pictures of exits
        if (labelPlayerName != null) { //It's cheating, but it works
            doorNW.setVisible(false);
            doorN.setVisible(false);
            doorNE.setVisible(false);
            doorW.setVisible(false);
            doorE.setVisible(false);
            doorSW.setVisible(false);
            doorS.setVisible(false);
            doorSE.setVisible(false);

            String image = "-fx-background-image: url('./worldofzuul/images/" + pars.getCurrentRoom().getName() + ".png')";
            roomBackground.setStyle(image);
            HashMap<String, Room> exits = pars.getRoomExits();
            Set<String> keys = exits.keySet();
            for (String exit : keys) {
                switch (exit) {
                    case "north":
                        doorN.setVisible(true);
                        break;
                    case "south":
                        doorS.setVisible(true);
                        break;
                    case "west":
                        doorW.setVisible(true);
                        break;
                    case "east":
                        doorE.setVisible(true);
                        break;
                    case "northwest":
                        doorNW.setVisible(true);
                        break;
                    case "northeast":
                        doorNE.setVisible(true);
                        break;
                    case "southwest":
                        doorSW.setVisible(true);
                        break;
                    case "southeast":
                        doorSE.setVisible(true);
                        break;
                    default:
                        break;
                }
            }
            updateItemsInRoom();
        }
    }

    private void updateZombies() {
        zombie1.setVisible(false);
        zombie2.setVisible(false);
        zombie3.setVisible(false);
        int i = 0;
        for (String s : pars.getZombies()) {
            switch (i) {
                case 0:
                    zombie1.setVisible(true);
                    zom1Str = s;
                    break;
                case 1:
                    zombie2.setVisible(true);
                    zom2Str = s;
                    break;
                case 2:
                    zombie3.setVisible(true);
                    zom3Str = s;
                    break;
            }
            i++;
        }
    }

    private void updateItemsInRoom() { //TODO load pictures of items
        fireaxeImg.setVisible(false);
        ramImg.setVisible(false);
        vaccinationImg.setVisible(false);

        for (String s : pars.getItemsInRoom()) {
            switch (s) {
                case "fireaxe":
                    fireaxeImg.setVisible(true);
                    break;
                case "shotgun":
                    shotgunImg.setVisible(true);
                    break;
                case "ram":
                    ramImg.setVisible(true);
                    break;
                case "policegun":
                    policegunImg.setVisible(true);
                    break;
                case "crowbar":
                    crowbarImg.setVisible(true);
                    break;
                case "energybar":
                    energybarImg.setVisible(true);
                    break;
                case "energydrink":
                    energydrinkImg.setVisible(true);
                    break;
                case "cannedtuna":
                    cannedtunaImg.setVisible(true);
                    break;
                case "rum":
                    rumImg.setVisible(true);
                    break;
                case "vaccination":
                    vaccinationImg.setVisible(true);
                    break;
                case "medKit":
                    medkitImg.setVisible(true);
                    break;
            }
        }
    }

    private void updateInventory() {
        invSlot1.setText("Empty");
        invSlot2.setText("Empty");
        invSlot3.setText("Empty");
        invSlot4.setText("Empty");
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

    @FXML
    private void onInventory(MouseEvent event) {
        if(radButts.getSelectedToggle().equals(radUse)){
            if(event.getSource().equals(invSlot1)){
                pars.useItem(invSlot1.getText());
            } else if(event.getSource().equals(invSlot2)){
                pars.useItem(invSlot2.getText());
            } else if(event.getSource().equals(invSlot3)){
                pars.useItem(invSlot3.getText());
            }
            updateInventory();
            updateStats();
        } else if(radButts.getSelectedToggle().equals(radDrop)){
            if(event.getSource().equals(invSlot1)){
                pars.dropItem(invSlot1.getText());
            } else if(event.getSource().equals(invSlot2)){
                pars.dropItem(invSlot2.getText());
            } else if(event.getSource().equals(invSlot3)){
                pars.dropItem(invSlot3.getText());
            }
            updateInventory();
            updateRoom();
        } else if(radButts.getSelectedToggle().equals(radWep)){
            if(event.getSource().equals(invSlot1)){
                pars.setPrimaryWeapon(invSlot1.getText());
            } else if(event.getSource().equals(invSlot2)){
                pars.setPrimaryWeapon(invSlot2.getText());
            } else if(event.getSource().equals(invSlot3)){
                pars.setPrimaryWeapon(invSlot3.getText());
            }
        }
    }

    private void onPlayerName() {
        if (labelPlayerName != null) {
            labelPlayerName.setText(NewFXMain.spil.player.getName());
        }
    }
}
