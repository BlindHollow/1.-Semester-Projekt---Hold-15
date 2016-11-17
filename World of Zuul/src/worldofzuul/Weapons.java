package worldofzuul;

public class Weapons extends Items {

    public int damage;

    public Weapons(String name, int damage, boolean axe) {
        this.damage = damage;
        this.name = name;
    }

    public int attack() {
        int total_Damage = damage;

        return total_Damage;
    }

    public int getDamage() {
        return damage;
    }

}
