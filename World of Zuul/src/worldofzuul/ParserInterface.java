/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.HashMap;

/**
 *
 * @author bytof
 * Interface for the Parser class. With this implemented, 
 * the gui can easily access all necessary methods.
 */
public interface ParserInterface {
    void moveToRoom(String direction);
    int getPlayerHealth();
    int getPlayerHunger();
    int getPlayerThirst();
    int getPlayerIllness();
    void pickUpItem(String itemName);
    void dropItem(String itemName);
    void useItem(String itemName);
    HashMap<String, Items> getPlayerInventory();
    HashMap<String, Items> getItemsInRoom();
    HashMap<String, Room> getRoomExits();
    void saveGame();
    void loadGame();
}
