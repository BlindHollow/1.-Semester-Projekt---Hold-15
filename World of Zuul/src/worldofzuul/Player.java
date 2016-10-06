/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * 
 */
public class Player {
    private int health, hunger, thirst, illness;
    
    public Player() {
        this.health = 100;
        this.hunger = 100;
        this.thirst = 100;
        this.illness = 0;
    }
    
    private void updateHealth(int modifier) {
        health = health + modifier;
    }
    
    private void updateHunger(int modifier) {
        hunger = hunger + modifier;
    }
    
    private void updateThirst(int modifier) {
        thirst = thirst + modifier;
    }
    
    private void updateIllness(int modifier) {
        illness = illness + modifier;
    }
    
    //Hunger and thirst should degrade, when changing rooms or when performing actions.
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
} //class Player
