package worldofzuul;

/**
 * Contains information about the zombies in the game
 * (Can possibly inherit from player?)
 */
public class Zombie {

    private String name;
    private int zomHP;
    private int zomDamage;
    private boolean isDead;
    

    public Zombie(String name, int zomHP, int zomDamage) {
        this.name = name;
        this.zomHP = zomHP;
        this.zomDamage = zomDamage;
        this.isDead = false;
    }

    public void hit(int damage) {
        updateHealth(-1*damage);
        System.out.println("You attacked " + name + " for " + damage + " damage.");
    }
    
    public void attackPlayer(Player player) {
        player.updateHealth(-1*zomDamage);
        player.updateIllness(7);
    }

    public void kill() {
        String actionString = "You killed the " + this.name;
        System.out.println(actionString);
    }
    
    public void updateHealth(int modifier) {
        zomHP = zomHP + modifier;
        if (zomHP <= 0) {
            isDead = true;
        }
    }
    
    public boolean schroedinger(){
        return isDead;
    }

    public String getName() {
        return name;
    }

}