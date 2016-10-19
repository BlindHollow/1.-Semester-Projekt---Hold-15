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
 *
 */
public class Player {

    private int health, hunger, thirst, illness;

    public boolean isDead = false; //If set to true, game will end.

    public static HashMap<String, Items> inventory = new HashMap<>();

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

    public void updateHunger(int modifier) {
        hunger = hunger + modifier;
    }

    public void updateThirst(int modifier) {
        thirst = thirst + modifier;
    }

    public void updateIllness(int modifier) {
        illness = illness + modifier;
    }

    /**
     * Degrades hunger and thirst by 5 each.
     */
    public void degenHungerAndThirst() {
        hunger = hunger - 5;
        thirst = thirst - 5;
    }

    public int getHealth() {
        return health;
    }

    public int getHunger() {
        return hunger;
    }

    public int getThrist() {
        return thirst;
    }

    public int getIllness() {
        return illness;
    }

    public void getStatus() {
        System.out.println("You have " + health + " health, " + hunger + " hunger, " + thirst + " thirst " + illness + " illness.");
    }

    //Returns the item (an object) with the key "key" in the inventory HashMap
    public Items getInventory(String key) {
        return inventory.get(key);
    }

    //Lists the items in the inventory
    public void showInventory() {
        String itemString = "Items in inventory:";
        Set<String> keys = inventory.keySet();
        for (String item : keys) {
            itemString += " " + item;
        }
        System.out.println(itemString);
    }
} //class Player
