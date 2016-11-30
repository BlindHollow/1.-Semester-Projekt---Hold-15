package worldofzuul;

public class Weapons extends Items {

    private int damage;
    private boolean usable;

    public Weapons(String name, int damage, boolean usable) {
        this.damage = damage;
        this.name = name;
        this.usable = usable;
    }
    
    public boolean isUsable() {
        return usable;
    }

    public int getDamage() {
        return damage;
    }

}
