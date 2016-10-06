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
    private double health, hunger, thirst, illness;
    
    public Player() {
        this.health = 100.0;
        this.hunger = 100.0;
        this.thirst = 100.0;
        this.illness = 0;
    }
    
    private void updateHealth(double modifier) {
        health = health + modifier;
    }
    
    private void updateHunger(double modifier) {
        hunger = hunger + modifier;
    }
    
    private void updateThirst(double modifier) {
        thirst = thirst + modifier;
    }
    
    private void updateIllness(double modifier) {
        illness = illness + modifier;
    }
    
    public double getHealth() {
        return health;
    }
    
    public double getHunger() {
        return hunger;
    }
    
    public double getThrist() {
        return thirst;
    }
    
    public double getIllness() {
        return illness;
    }
    
    public void getStatus() {
        System.out.println("You have " + health + "health, " + hunger + " hunger, " + thirst + " thirst " + illness + "illness.");                
    }
} //class Player
