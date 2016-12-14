package worldofzuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class contains information about the rooms. A room consists of a
 * description string and three seperate hashmaps containng information about
 * what exits a given room has, what items are placed in it and whether there
 * are zombies in the room and if yes, which zombies there are.
 */
public class Room {

    private boolean lock = false;
    private String name;
    private boolean Spawnable = true;
    private final String description;
    private final HashMap<String, Room> exits;
    private final HashMap<String, Items> placements;
    private HashMap<String, Zombie> zombies;

    public Room(String name, String description) {
        this.description = description;
        exits = new HashMap<>();
        placements = new HashMap<>();
        zombies = new HashMap<>();
        this.name = name;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    //Places an item in the room
    public void placeItem(Items item) {
        placements.put(item.getName(), item);
    }

    //Removes an item from the room
    public void removeItem(String key) {
        placements.remove(key);
    }

    //Places a zombie in a room
    public void placeZombie(Zombie zombie) {
        zombies.put(zombie.getName(), zombie);
    }

    //Removes the zombie from the room. Should be used when it's killed
    public void removeZombie(String key) {
        zombies.remove(key);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description + ".\n" + getZombieString() + getExitString();
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public String getZombieString() {
        if (!zombies.isEmpty()) {
            String zombieString = "A zombified version of ";
            Set<String> keys = zombies.keySet();
            for (String zombie : keys) {
                zombieString += zombie;
            }
            return zombieString + " is in the room!" + ".\n";
        } else {
            return "";
        }

    }

    public Room getExit(String direction) {
        Room room = exits.get(direction);

        return room;
    }

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

    //Returns the item (an object) with the key "key" in the placements HashMap
    public Items getItem(String key) {
        return placements.get(key);
    }

    /**
     * A method that returns all the items in a room.
     *
     * @return
     */
    public HashMap<String, Items> getAllItems() {
        return this.placements;
    }

    public Zombie getZombie(String key) {
        return zombies.get(key);
    }

    private void spawnZombie() {
        Zombie monster = new Zombie("RandomZombie", 100, 2);

        zombies.put("RandomZombie", monster);
    }

    public void SetSpawnable(boolean value) {
        Spawnable = value;
    }

    public boolean GetSpawnable() {
        return Spawnable;
    }

    public void spawnRandomZombie() {
        if (Spawnable == true) {
            Dice random = new Dice(0, 100);

            int rValue = random.Calculate();

            if (rValue >= 20    && rValue <= 80) {
                spawnZombie();
            }

        }
    }

    //Prints a list of items in the current room
    public void searchRoom() {
        if (placements.isEmpty()) {
            System.out.println("The room is empty.");
        } else {
            String itemString = "Items in room:";
            Set<String> keys = placements.keySet();
            for (String item : keys) {
                itemString += " " + item;
            }
            System.out.println(itemString);
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
