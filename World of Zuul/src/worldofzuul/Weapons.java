package worldofzuul;

public class Weapons extends Items {

    public int damage;
    public int modifier;

    public Weapons(String name, int damage, int modifier) {
        this.damage = damage;
        this.modifier = modifier;
        this.name = name;
    }

    public int attack() {
        int total_Damage = damage * modifier;

        System.out.println("You dealt " + total_Damage + " damage.");

        return total_Damage;
    }

}
