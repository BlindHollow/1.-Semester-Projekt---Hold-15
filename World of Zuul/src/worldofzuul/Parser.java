package worldofzuul;

import java.util.HashMap;
import java.util.Set;

/**
 * A class with methods that the gui controller can call. 
 * By making an instance of this in any gui, 
 * the gui can play the game(as long as it uses the methods in a correct way).
 * @author bytof
 */
public class Parser implements ParserInterface {

    @Override
    public boolean moveToRoom(String direction) {
        return NewFXMain.spil.goRoom(direction);
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
    public boolean playerDead(){
        return NewFXMain.spil.player.schroedinger();
    }
    
    @Override
    public int playerScore(){
        return NewFXMain.spil.player.retrieveScore();
    }
    
    @Override
    public void pickUpItem(String itemName) {
        NewFXMain.spil.takeItem(itemName);
    }

    @Override
    public void dropItem(String itemName) {
        NewFXMain.spil.dropItem(itemName);
    }
    
    @Override
    public void useItem(String itemName){
        NewFXMain.spil.useItem(itemName);
    }
    
    @Override
    public void attackZombie(String s){
        NewFXMain.spil.attackZombie(s);
    }
    
    @Override
    public Set<String> getZombies(){
        return NewFXMain.spil.currentRoom().getZombies().keySet();
    }
    
    @Override
    public Room getCurrentRoom(){
        return NewFXMain.spil.currentRoom();
    }

    @Override
    public Set<String> getPlayerInventory() {
        return NewFXMain.spil.player.getInventory().keySet();
    }

    @Override
    public Set<String> getItemsInRoom() {
        return NewFXMain.spil.currentRoom().getAllItems().keySet();
    }

    @Override
    public HashMap<String, Room> getRoomExits() {
        return NewFXMain.spil.currentRoom().getNeighbours();
    }
    
    @Override
    public void setPrimaryWeapon(String weapon){
        NewFXMain.spil.player.setPrimaryWeapon(weapon);
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
