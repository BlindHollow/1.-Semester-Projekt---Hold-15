/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.ArrayList;

/**
 *
 * 
 */
public class Player {
    private int health, hunger, thirst, illness;
    
    private boolean isDead = false; //If set to true, game will end.
    
    public static ArrayList<Items> inventory;
    /**
     * Constructor
     * Starts the player object with full health, hunger, thirst and illness gauges.
     */
    public Player() {
        this.health = 100;
        this.hunger = 100;
        this.thirst = 100;
        this.illness = 0;
    }
    /**
     * Updates the health attribute
     * checks if health is 0 or less on update,
     * if it is, sets isDead = true, causing the game to end. 
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
        if (hunger < 0) {
            hunger = 0;
        }
        starving();
    }
    
    public void updateThirst(int modifier) {
        thirst = thirst + modifier;
        if (thirst < 0) {
            thirst = 0;
        }
        starving();
    }
    
    public void updateIllness(int modifier) {
        illness = illness + modifier;
    }
    
    /**
     * Degrades hunger and thirst by 5 each.
     */
    public void degenHungerAndThirst() {
        updateHunger(-5);
        updateThirst(-5);
                
    }
    
    public void starving() {
        if (hunger == 0 || thirst == 0) {
            updateHealth(-10);
        }
        if (hunger < 10 && hunger > 0 || thirst < 10 && thirst > 0) {
            updateHealth(-5);
        }
    }
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
    
    public void getStatus() {
        System.out.println("You have " + health + " health, " + hunger + " hunger, " + thirst + " thirst " + illness + " illness.");                
    }
    
    public void showInventory() {
        for (Items itm : inventory) {
            System.out.println(itm.getName());
        }
    }
} //class Player
