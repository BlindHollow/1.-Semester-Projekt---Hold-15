package worldofzuul; //NETBEANS

import java.util.HashMap;
import java.util.Set;

/**
 * Contains info about the Player.
 *
 */
public class Player {

    private int health, hunger, thirst, illness;

    private boolean isDead = false; //If set to true, game will end.

    public HashMap<String, Items> inventory = new HashMap<>();

    /**
     * Constructor Starts the player object with full health, hunger, thirst and
     * illness gauges.
     */
    public Player() {
        this.health = 100;
        this.hunger = 100;
        this.thirst = 100;
        this.illness = 0;
    }

    /**
     * Updates the health attribute checks if health is 0 or less on update, if
     * it is, sets isDead = true, causing the game to end.
     *
     * @param modifier
     */
    public void updateHealth(int modifier) {
        health = health + modifier;
        if (health <= 0) {
            isDead = true; //If player is dead game should end.
        }
    }

    /**
     * Updates the hunger attribute. If hunger goes beolw 0, set back to zero,
     * as hunger less than zero is not allowed.
     *
     * @param modifier
     */
    public void updateHunger(int modifier) {
        hunger = hunger + modifier;
        if (hunger < 0) {
            hunger = 0;
        }
        starving();
    }

    /**
     * Updates the thirst attribute. If thirst goes below 0 it is set to zero,
     * as thirst less than zero is not allowed.
     *
     * @param modifier
     */
    public void updateThirst(int modifier) {
        thirst = thirst + modifier;
        if (thirst < 0) {
            thirst = 0;
        }
        dehydration();
    }

    /**
     * Updates illness attribute has no maxvalue if above 80 it will update
     * health with -10% of current value of illness. TODO Adjust numbers,
     * possibly remove attribute entirely.
     *
     * @param modifier
     */
    public void updateIllness(int modifier) {
        illness = illness + modifier;
        if (illness > 80) {
            updateHealth((int) (-1 * illness * 0.10)); //lose health equivalent to 10% of illness stat.
        }
    }

    /**
     * Degrades hunger and thirst by 5 each. TODO: Adjust numbers to adjust
     * difficulty.
     */
    public void degenHungerAndThirst() {
        updateHunger(-5);
        updateThirst(-5);
    }

    /**
     * Updates health if hunger is below a certain threshold. Prints that the
     * player is starving.
     */
    private void starving() { //TODO: Possibly add more cases ?
        if (hunger == 0) {
            updateHealth(-10);
            System.out.println("You are starving.");
        } else if (hunger < 10 && hunger > 0) {
            updateHealth(-5);
            System.out.println("You are starving.");
        }
    }

    /**
     * Updates health if thirst is below a certain theshold. Prints that the
     * player is dehydrated.
     */
    private void dehydration() { //TODO: Possibly add more cases ?
        if (thirst == 0) {
            updateHealth(-10);
            System.out.println("You are dehydrated.");
        } else if (thirst < 10 && thirst > 0) {
            updateHealth(-5);
            System.out.println("You are dehydrated.");
        }
    }

    //Access functions.
    public boolean schrodinger() {
        return isDead;
    }

    public int getHealth() {
        return health;
    }

    public int getHunger() {
        return hunger;
    }

    public int getThirst() {
        return thirst;
    }

    public int getIllness() {
        return illness;
    }

    /**
     * Prints the players status, ie. values of the attributes.
     */
    public void getStatus() {
        System.out.println("You have " + health + " health, " + hunger + " hunger, " + thirst + " thirst " + illness + " illness.");
    }

//Returns the item (an object) with the key "key" in the inventory HashMap
    public Items getInventory(String key) {
        return inventory.get(key);
    }

    //Lists the items in the inventory
    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            String itemString = "Items in inventory:";
            Set<String> keys = inventory.keySet();
            for (String item : keys) {
                itemString += " " + item;
            }
            System.out.println(itemString);
        }
    }
} //class Player
