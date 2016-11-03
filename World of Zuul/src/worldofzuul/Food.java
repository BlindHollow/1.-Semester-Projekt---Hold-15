package worldofzuul;

/**
 *Items of this type have affect the hunger and thirst attributes of a player.
 * 
 */
public class Food extends Items {

    private final int hungerRegen; //for regenerating health upon use
    private final int thirstRegen; //for regenerating thirst upon use

    public Food(String name, int hunger, int thirst) {
        this.hungerRegen = hunger;
        this.thirstRegen = thirst;
        this.name = name;
    }

}
