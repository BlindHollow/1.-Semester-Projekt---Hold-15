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