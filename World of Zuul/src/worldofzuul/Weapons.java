package worldofzuul;

public class Weapons extends Items {

    public int damage;
    public int modifier;
    private boolean usable; //tells if the weapon can be used to destroy barricade between houses

    public Weapons(String name, int damage, int modifier, boolean axe) {
        this.damage = damage;
        this.modifier = modifier;
        this.name = name;
        this.usable = axe;
    }

    public int attack() {
        int total_Damage = damage * modifier;

        System.out.println("You dealt " + total_Damage + " damage.");

        return total_Damage;
    }

}
