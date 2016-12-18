package worldofzuul;

import worldofzuul.utilities.Dice;
import java.util.Set;
import java.util.HashMap;

/**
 * This class contains information about the rooms. A room consists of a
 * description string and three seperate hashmaps containng information about
 * what exits a given room has, what items are placed in it and whether there
 * are zombies in the room and if yes, which zombies there are.
 *
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
public class Room {

    private boolean lock = false;
    private String name;
    private boolean spawnable = true;
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Items> placements;
    private HashMap<String, Zombie> zombies;

    private Dice spawnChance = new Dice(0, 100);


    /**
     * Creates room
     * @param name the name of the room
     * @param description the rooms description
     */
    public Room(String name, String description) {
        this.description = description;
        exits = new HashMap<>();
        placements = new HashMap<>();
        zombies = new HashMap<>();
        this.name = name;
    }

    /**
     * sets the exits of the room
     * @param direction the dirextion of neighbour
     * @param neighbor neighbouring room
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Places an item in the room
     */
    public void placeItem(Items item) {
        placements.put(item.getName(), item);
    }

    /**
     * Removes an item from the room
     *
     * @param key the name of the item
     */
    public void removeItem(String key) {
        placements.remove(key);
    }

    /**
     * Places a zombie in a room
     *
     * @param zombie the zombie to place
     */
    public void placeZombie(Zombie zombie) {
        zombies.put(zombie.getId().toString(), zombie);
    }

    /**
     * Removes the zombie from the room. Should be used when it's killed
     *
     * @param key the id of zombie to remove
     */
    public void removeZombie(String key) {
        zombies.remove(key);
    }

    public String getShortDescription() {
        return description;
    }

    public Room getExit(String direction) {
        Room room = exits.get(direction);

        return room;
    }

    /**
     * Returns a Room depending on an int
     * @param index a randomly generated int
     * @return Returns a room
     */
    public Room getExit(int index) {
        Set<String> keys = exits.keySet();
        int i = 0;
        for (String exit : keys) {
            if (i == index) {
                return exits.get(exit);
            }
            i++;
        }
        return null;
    }

    public Items getItem(String key) {
        return placements.get(key);
    }

    public HashMap<String, Items> getAllItems() {
        return this.placements;
    }

    public Zombie getZombie(String key) {
        return zombies.get(key);
    }

    /**
     * Creates a zombies and adds it to the room
     */
    private void spawnZombie() {
        int hp, dmg;
        hp = 20;
        dmg = 4;

        Zombie monster = new Zombie(hp, dmg);

        //String monsterName = monster.getRandomName();
        zombies.put(monster.getId().toString(), monster);
    }

    public void setSpawnable(boolean value) {
        spawnable = value;
    }

    public boolean getSpawnable() {
        return spawnable;
    }

    /**
     * Spawn up to 3 zombies in the room
     */
    public void spawnRandomZombie() {

        if (spawnable == true) {
            for (int i = 0; i < 3; i++) {
                int rValue = spawnChance.calculate();

                if (rValue >= 20 && rValue <= 53) {
                    spawnZombie();
                }
            }
        }
    }

    public boolean isLocked() {
        return lock;
    }

    public void setLock(boolean status) {
        lock = status;
    }

    public int getSize() {
        return exits.size();
    }

    public HashMap<String, Room> getNeighbours() {
        return exits;
    }

    public HashMap<String, Items> getPlacements() {
        return placements;
    }

    public HashMap<String, Zombie> getZombies() {
        return zombies;
    }

    public String getName() {
        return name;
    }

}
