/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author bytof
 */
public class Sustain extends Items {

    private final int healthRegen; //for regenerating health upon use
    private final int illnessRegen; //for lowering the illness upon use

    public Sustain(String name, int health, int illness) {
        this.healthRegen = health;
        this.illnessRegen = illness;
        this.name = name;
    }

}
