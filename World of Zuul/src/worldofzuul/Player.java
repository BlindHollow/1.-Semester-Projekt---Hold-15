package worldofzuul; //NETBEANS

import java.util.HashMap;

/**
 *
 * Contains info about the Player.
 *
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
public class Player {

    //Variables
    private int health, hunger, thirst, illness;

    private String name;
    private int score;

    private boolean hasPrimaryWeapon;

    private boolean isDead = false; //If set to true, game will end.
    private Weapons primaryWeapon;

    private HashMap<String, Items> inventory = new HashMap<>();

    /**
     * Constructor Starts the player object with full health, hunger, thirst and
     * illness gauges.
     */
    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.hunger = 100;
        this.thirst = 100;
        this.illness = 0;
        this.score = 0;
    }

    /**
     * Constructor used if loading from a saved game state.
     */
    public Player(String name, int health, int hunger, int thirst, int illness) {
        this.health = health;
        this.hunger = hunger;
        this.thirst = thirst;
        this.illness = illness;
        this.name = name;

    }

    /**
     * Sets the player name
     *
     * @param s The string that you want to set as player name
     */
    public void setName(String s) {
        System.out.println("name is being set");
        this.name = s;
    }

    /**
     * Updates the health attribute checks if health is 0 or less on update, if
     * it is, sets isDead = true, causing the game to end.
     *
     * @param modifier how much the game should be changed
     */
    public void updateHealth(int modifier) {
        health = health + modifier;
        if (health <= 0) {
            this.savePlayerscore();

            health = 0;
            isDead = true; //If player is dead game should end.

        }
    }

    /**
     * Updates the hunger attribute. If hunger goes beolw 0, set back to zero,
     * as hunger less than zero is not allowed.
     *
     * @param modifier how much the hunger should be modified
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
     * @param modifier how much the thirst should be modified
     */
    public void updateThirst(int modifier) {
        thirst = thirst + modifier;
        if (thirst < 0) {
            thirst = 0;
        }
        dehydration();
    }

    /**
     * increases the current player points, with a specific number
     *
     * @param i how much the players score should be increased
     */
    public void increasePlayerScore(int i) {
        this.score = this.score + i;

    }

    /**
     * decreases the current player points, with a specific number
     *
     * @param i how much to decrease the players score
     */
    public void decreasePlayerScore(int i) {
        this.score = this.score - i;
    }

    //access function
    public int getScore() {
        return this.score;
    }

    /**
     * Updates illness attribute has no maxvalue if above 80 it will update
     * health with -10% of current value of illness. TODO Adjust numbers,
     * possibly remove attribute entirely.
     *
     * @param modifier how much the illness should be modified
     */
    public void updateIllness(int modifier) {
        illness = illness + modifier;
        if (illness < 0) {
            illness = 0;
        }
        if (illness > 80) {
            updateHealth((int) (-1 * illness * 0.10)); //lose health equivalent to 10% of illness stat.
        }
    }

    /**
     * saves the players score to a file
     */
    public void savePlayerscore() {
        worldofzuul.score.Highscore.saveCharacter(name, this.getScore());
    }

    /**
     * Loads the score of a player, if a matching file is found
     */
    public void loadHighscore() {
        worldofzuul.score.HighscorePlayer player = worldofzuul.score.Highscore.loadCharacter(name);

        if (player != null) {
            this.score = player.getPlayerScore();
        }

    }

    /**
     * Degrades hunger and thirst by 5 each. TODO: Adjust numbers to adjust
     * difficulty.
     */
    public void degenHungerAndThirst(int n) {
        updateHunger(-1 * n);
        updateThirst(-1 * n);
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

    /**
     * Sets the players primary weapon
     * @param key the name of the weapon
     */
    public void setPrimaryWeapon(String key) {
        primaryWeapon = (Weapons) inventory.get(key);
        hasPrimaryWeapon = true;
    }

    //Access functions.
    public boolean schroedinger() {

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

    public Weapons getPrimaryWeapon() {
        return primaryWeapon;
    }

    public boolean hasPrimaryWeapon() {
        return hasPrimaryWeapon;
    }

    /**
     * 
     * @return returns true if the player has an item that can be used for opening doors 
     */
    public boolean hasUsableItem() {
        for (Items item : inventory.values()) {
            if (item instanceof Weapons) {
                Weapons weap = (Weapons) item;
                if (weap.isUsable()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Items getItemInInventory(String key) {
        return inventory.get(key);
    }

    public HashMap<String, Items> getInventory() {
        return inventory;
    }

    public void removePrimaryWeapon() {
        primaryWeapon = null;
        hasPrimaryWeapon = false;
    }

    public boolean itemsIsInInventory(Items i) {
        return inventory.containsKey(i.getName());
    }
} //class Player
