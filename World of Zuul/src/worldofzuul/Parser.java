package worldofzuul;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;


// TODO: Write Documentation
public class Parser implements ParserInterface{

    @Override
    public void moveToRoom(String direction) {
        NewFXMain.spil.goRoom("direction");
    }

    @Override
    public int getPlayerHealth() {
       return NewFXMain.spil.player.getHealth();
    }

    @Override
    public int getPlayerHunger() {
        return NewFXMain.spil.player.getHunger();
    }

    @Override
    public int getPlayerThirst() {
        return NewFXMain.spil.player.getThirst();
    }

    @Override
    public int getPlayerIllness() {
        return NewFXMain.spil.player.getIllness();
    }

    @Override
    public void pickUpItem(String itemName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dropItem(String itemName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Items> getPlayerInventory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Items> getItemsInRoom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Room> getRoomExits() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
