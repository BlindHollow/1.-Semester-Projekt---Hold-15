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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.control.TextArea;
import worldofzuul.score.*;

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
    private Label roomLabel;
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
    private ImageView zombie1, zombie2, zombie3, pilotImg;
    @FXML
    private RadioButton radUse, radDrop, radWep;
    @FXML
    private ToggleGroup radButts;
    @FXML
    private TextArea highscores;
    final FileChooser fileChooser = new FileChooser();
    Stage stage = null;
    Parent root = null;
    private Parser pars;
    private String zom1Str, zom2Str, zom3Str;
    private Highscore hs;

    /**
     * initialises the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pars = new Parser();
        onPlayerName();
        updateRoom();
        updateHighscore();
    }

    /**
     * Handles button actions. Gets the source of the event, and handles the
     * action accordingly.
     *
     * @param event The user action
     * @throws IOException
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnContinue) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
            File file = fileChooser.showOpenDialog(stage);
            pars.loadGame(file);
            stage = (Stage) btnContinue.getScene().getWindow(); //get reference to the button's stage        
            loadScene("GameScene");
        } else if (event.getSource() == btnNewGame) {
            welcomeStartAnchor.setVisible(true);
        } else if (event.getSource() == btnStartGame) {
            NewFXMain.spil.newGame();
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
     * @param sceneName The name of the scene
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
     * @param event User event
     * @throws IOException
     */
    @FXML //Åbner en hjælp box
    private void getHelpBox(ActionEvent event) throws IOException {
        AlertBox.displayHelpBox();
    }

    /**
     * Opens the QuitBox
     * @param event User event
     * @throws IOException 
     */
    @FXML //Giver mulighed for at stoppe spillet
    private void quitGame(ActionEvent event) throws IOException {
        AlertBox.displayQuitBox("Quit?", "Are you sure?");
    }

    /**
     *  Does something dependeing on what is clicked. Then updates everythig.
     *
     * @param event User clicked on something
     */
    @FXML
    private void onMouseClicked(MouseEvent event) {
        if (event.getSource().equals(doorW)) {
            goRoom("west");
        } else if (event.getSource().equals(doorE)) {
            goRoom("east");
        } else if (event.getSource().equals(doorN)) {
            goRoom("north");
        } else if (event.getSource().equals(doorS)) {
            goRoom("south");
        } else if (event.getSource().equals(doorNW)) {
            goRoom("northwest");
        } else if (event.getSource().equals(doorNE)) {
            goRoom("northeast");
        } else if (event.getSource().equals(doorSW)) {
            goRoom("southwest");
        } else if (event.getSource().equals(doorSE)) {
            goRoom("southeast");
        } else if (event.getSource().equals(fireaxeImg)) {
            pars.pickUpItem("fireaxe");
        } else if (event.getSource().equals(ramImg)) {
            pars.pickUpItem("ram");
        } else if (event.getSource().equals(vaccinationImg)) {
            pars.pickUpItem("vaccination");
        } else if (event.getSource().equals(energydrinkImg)) {
            pars.pickUpItem("energydrink");
        } else if (event.getSource().equals(energybarImg)) {
            pars.pickUpItem("energybar");
        } else if (event.getSource().equals(medkitImg)) {
            pars.pickUpItem("medkit");
        } else if (event.getSource().equals(rumImg)) {
            pars.pickUpItem("rum");
        } else if (event.getSource().equals(cannedtunaImg)) {
            pars.pickUpItem("cannedtuna");
        } else if (event.getSource().equals(policegunImg)) {
            pars.pickUpItem("policegun");
        } else if (event.getSource().equals(shotgunImg)) {
            pars.pickUpItem("shotgun");
        } else if (event.getSource().equals(crowbarImg)) {
            pars.pickUpItem("crowbar");
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

    /**
     * Moves player to a new room, and opens an alertbox if the game is won
     * @param s The direction to go
     */
    private void goRoom(String s) {
        if (pars.moveToRoom(s)) {
            AlertBox.displayEndBox("Congratulations, you won", "Your final score is: " + pars.playerScore());
        }
        updateStats();

    }

    /**
     * Updates the updates the image of the current room, and its available exits
     */
    private void updateRoom() { 
        if (labelPlayerName != null) { //It's cheating, but it works
            doorNW.setVisible(false);
            doorN.setVisible(false);
            doorNE.setVisible(false);
            doorW.setVisible(false);
            doorE.setVisible(false);
            doorSW.setVisible(false);
            doorS.setVisible(false);
            doorSE.setVisible(false);
            pilotImg.setVisible(false);

            roomLabel.setText(pars.getCurrentRoom().getName());

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
            if (pars.pilotNote() && pars.getCurrentRoom().equals(pars.pilotRoom())) {
                pilotImg.setVisible(true);
            }

            updateItemsInRoom();
        }
    }

    /**
     * Updates the imgaes of zombies to show the ones that are in the room
     */
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

    /**
     * Updates the images of the items, to display the ones that are in the room
     */
    private void updateItemsInRoom() {
        fireaxeImg.setVisible(false);
        ramImg.setVisible(false);
        vaccinationImg.setVisible(false);
        medkitImg.setVisible(false);
        rumImg.setVisible(false);
        energydrinkImg.setVisible(false);
        energybarImg.setVisible(false);
        cannedtunaImg.setVisible(false);
        policegunImg.setVisible(false);
        shotgunImg.setVisible(false);
        crowbarImg.setVisible(false);

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
                case "medkit":
                    medkitImg.setVisible(true);
                    break;
            }
        }
    }

    /**
     * Updates the labels that displays the inventory.
     */
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
        if (pars.playerDead()) {
            AlertBox.displayEndBox("You died", "Your final score is: " + pars.playerScore());
        }
    }

    /**
     * Does something when an item in the inventory is clicked, based on which radiobutton is selected
     * @param event The item that is clicked
     */
    @FXML
    private void onInventory(MouseEvent event) {
        if (radButts.getSelectedToggle().equals(radUse)) {
            if (event.getSource().equals(invSlot1)) {
                pars.useItem(invSlot1.getText());
            } else if (event.getSource().equals(invSlot2)) {
                pars.useItem(invSlot2.getText());
            } else if (event.getSource().equals(invSlot3)) {
                pars.useItem(invSlot3.getText());
            } else if (event.getSource().equals(invSlot4)) {
                pars.useItem(invSlot4.getText());
            }
            updateInventory();
            updateStats();
        } else if (radButts.getSelectedToggle().equals(radDrop)) {
            if (event.getSource().equals(invSlot1)) {
                pars.dropItem(invSlot1.getText());
            } else if (event.getSource().equals(invSlot2)) {
                pars.dropItem(invSlot2.getText());
            } else if (event.getSource().equals(invSlot3)) {
                pars.dropItem(invSlot3.getText());
            } else if (event.getSource().equals(invSlot4)) {
                pars.dropItem(invSlot4.getText());
            }
            updateInventory();
            updateRoom();
        } else if (radButts.getSelectedToggle().equals(radWep)) {
            if (event.getSource().equals(invSlot1)) {
                pars.setPrimaryWeapon(invSlot1.getText());
            } else if (event.getSource().equals(invSlot2)) {
                pars.setPrimaryWeapon(invSlot2.getText());
            } else if (event.getSource().equals(invSlot3)) {
                pars.setPrimaryWeapon(invSlot3.getText());
            } else if (event.getSource().equals(invSlot4)) {
                pars.setPrimaryWeapon(invSlot4.getText());
            }
        }
    }

    /**
     * Displays the highscore on the welcome scene
     */
    private void updateHighscore() {
        if (highscores != null) {
            int limit = 0;
            hs = new Highscore();
            hs.loadPlayers();
            for (HighscorePlayer p : hs.orderedListOfPlayers()) {
               highscores.setText(highscores.getText() + "\nPlayer: " + p.getPlayerName() + "\t\tHighscore: " + p.getPlayerScore());
               limit++;
               if(limit == 10){
                   break;
               }
            }
        }
    }

    /**
     * Updates the label for the player name when the game scene is loaded
     */
    private void onPlayerName() {
        if (labelPlayerName != null) {
            labelPlayerName.setText(NewFXMain.spil.player.getName());
        }
    }
}
