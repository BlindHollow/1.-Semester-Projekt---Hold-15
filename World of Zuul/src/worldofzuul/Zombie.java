package worldofzuul;

/**
 * Contains information about the zombies in the game
 *
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
import java.util.Random;
import java.util.UUID;

public class Zombie {

    private String name;
    private int zomHP;
    private int zomDamage;
    private UUID id;
    private boolean isDead;
    private String[] zombieNames = {"jon", "ned", "ramsay", "arya", "peter", "thormund", "jorah", "sansa", "brann", "rickon"};

    /**
     * Constructor for Zombie
     *
     * @param zomHP the zombies hp
     * @param zomDamage the zombies damage
     */
    public Zombie(int zomHP, int zomDamage) {
        this.name = (zombieNames[new Random().nextInt(zombieNames.length)]);
        this.zomHP = zomHP;
        this.zomDamage = zomDamage;
        this.isDead = false;
        this.id = UUID.randomUUID();
        System.out.println(id.toString());
    }

    public UUID getId() {
        return id;
    }

    /**
     * Hitting the zombie, with a specific amount of damage
     *
     * @param damage amount of damage the zombie takes
     */
    public void hit(int damage) {
        updateHealth(-1 * damage);
        System.out.println("You attacked " + name + " for " + damage + " damage.");
    }

    /**
     * Zombie hits the player
     *
     * @param player the player to hit
     */
    public void attackPlayer(Player player) {
        player.updateHealth(-1 * zomDamage);
        player.updateIllness(7);
        System.out.println(name + " attacked you for " + zomDamage + " damage.");
    }

    /**
     * Updates the health of the zombie
     *
     * @param modifier how much the zombie is damaged
     */
    public void updateHealth(int modifier) {
        zomHP = zomHP + modifier;
        if (zomHP <= 0) {
            isDead = true;
        }
    }

    public boolean schroedinger() {
        return isDead;
    }

    public String getName() {
        return name;
    }

}
