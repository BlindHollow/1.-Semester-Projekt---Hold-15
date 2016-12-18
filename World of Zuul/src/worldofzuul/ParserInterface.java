package worldofzuul;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author bytof Interface for the Parser class. With this implemented, the gui
 * can easily access all necessary methods.
 */
public interface ParserInterface {

    boolean moveToRoom(String direction);

    int getPlayerHealth();

    int getPlayerHunger();

    int getPlayerThirst();

    int getPlayerIllness();

    boolean playerDead();

    int playerScore();

    boolean pilotNote();

    Room pilotRoom();

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

    void loadGame(File file) throws IOException;
}
