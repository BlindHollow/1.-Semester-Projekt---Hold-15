/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author bytof
 * Interface for the Parser class. With this implemented, 
 * the gui can easily access all necessary methods.
 */
public interface ParserInterface {
    boolean moveToRoom(String direction);
    int getPlayerHealth();
    int getPlayerHunger();
    int getPlayerThirst();
    int getPlayerIllness();
    boolean playerDead();
    int playerScore();
    void pickUpItem(String itemName);
    void dropItem(String itemName);
    void useItem(String itemName);
    void attackZombie(String s);
    void setPrimaryWeapon(String weapon);
    Set<String> getZombies();
    Room getCurrentRoom();
    Set<String> getPlayerInventory();
    Set<String> getItemsInRoom();
    HashMap<String, Room> getRoomExits();
    void saveGame();
    void loadGame();
}
