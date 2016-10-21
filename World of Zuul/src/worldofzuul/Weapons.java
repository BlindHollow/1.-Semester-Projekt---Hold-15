package worldofzuul;

public class Weapons extends Items {

    public int damage;
    private boolean usable; //tells if the weapon can be used to destroy barricade between houses

    public Weapons(String name, int damage, boolean axe) {
        this.damage = damage;
        this.name = name;
        this.usable = axe;
    }

    public int attack() {
        int total_Damage = damage;

        System.out.println("You dealt " + total_Damage + " damage.");

        return total_Damage;
    }

}