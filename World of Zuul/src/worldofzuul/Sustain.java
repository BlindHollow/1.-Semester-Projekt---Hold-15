package worldofzuul;

/**
 *Items of this type have an effect on the health and illness attributes of a player.
 
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
public class Sustain extends Items {

    private final int healthRegen; //for regenerating health upon use
    private final int illnessRegen; //for lowering the illness upon use

    /**
     * Creates a new item
     * @param name name of the item
     * @param health how much it regenerates health
     * @param illness how much it regenerates illness 
     */
    public Sustain(String name, int health, int illness) {
        this.healthRegen = health;
        this.illnessRegen = illness;
        this.name = name;
    }
    
    public int getHealthRegen(){
        return this.healthRegen;
    }
    
    public int getIllnessRegen(){
        return -1*this.illnessRegen;
    }

}