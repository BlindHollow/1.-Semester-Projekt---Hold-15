package worldofzuul;

/**
 *Items of this type have affect the hunger and thirst attributes of a player.
 
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
public class Food extends Items {
    //for regenerating health upon use
    private final int hungerRegen; 
    
    //for regenerating thirst upon use
    private final int thirstRegen; 

    /**
     * Constructor, Set Name, hunger and thirst for the food 
     * @param name
     * @param hunger
     * @param thirst 
     */
    public Food(String name, int hunger, int thirst) {
        this.hungerRegen = hunger;
        this.thirstRegen = thirst;
        this.name = name;
    }
    
    /**
     * get how much of the players hunger it will regenerate
     * @return 
     */
    public int getHungerRegen(){
        return this.hungerRegen;
    }
    
    /**
     *  get how much of the players thirs it will regenerate
     * @return 
     */
    public int getThirstRegen(){
        return this.thirstRegen;
    }

}
