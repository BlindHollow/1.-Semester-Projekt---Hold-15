package worldofzuul;

import java.util.HashMap;

// TODO: Write Documentation
public class Parser implements ParserInterface {

    @Override
    public void moveToRoom(String direction) {
        NewFXMain.spil.goRoom(direction);
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
        NewFXMain.spil.takeItem(itemName);
    }

    @Override
    public void dropItem(String itemName) {
        NewFXMain.spil.dropItem(itemName);
    }

    @Override
    public HashMap<String, Items> getPlayerInventory() {
        return NewFXMain.spil.player.getInventory();
    }

    @Override
    public HashMap<String, Items> getItemsInRoom() {
        return NewFXMain.spil.currentRoom().getAllItems();
    }

    @Override
    public HashMap<String, Room> getRoomExits() {
        return NewFXMain.spil.currentRoom().getNeighbours();
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
