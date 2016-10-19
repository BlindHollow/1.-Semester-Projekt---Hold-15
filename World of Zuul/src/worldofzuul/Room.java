package worldofzuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

// TODO: Write Documentation
public class Room {

    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Items> placements;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        placements = new HashMap<>();
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

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    //Returns the item (an object) with the key "key" in the placements HashMap
    public Items getItem(String key) {
        return placements.get(key);
    }

    //Prints a list of items in the current room
    public void searchRoom() {
        String itemString = "Items in room:";
        Set<String> keys = placements.keySet();
        for (String item : keys) {
            itemString += " " + item;
        }
        System.out.println(itemString);
    }

}
