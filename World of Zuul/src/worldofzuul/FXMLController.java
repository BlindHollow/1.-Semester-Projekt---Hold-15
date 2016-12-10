/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnContinue) {
            stage = (Stage) btnContinue.getScene().getWindow(); //get reference to the button's stage        
            loadScene("GameScene");
        } else if (event.getSource() == btnNewGame) {
            welcomeStartAnchor.setVisible(true);
        } else if (event.getSource() == btnStartGame) {
            stage = (Stage) btnStartGame.getScene().getWindow();
            loadScene("GameScene");
        } else {
            stage = (Stage) mainBtn.getScene().getWindow();
            loadScene("WelcomeScene");
        }

    }

    @FXML
    private void loadScene(String sceneName) throws IOException {
        root = FXMLLoader.load(getClass().getResource(sceneName + ".fxml")); //load up OTHER FXML document
        //create a new scene with root and set the stage
        Scene scene = new Scene(root,1200, 680);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void quitGame(ActionEvent event) throws IOException {
        //hider scenen, og slukker spillet
        btnQuit.getScene().getWindow().hide();
    }

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

}
