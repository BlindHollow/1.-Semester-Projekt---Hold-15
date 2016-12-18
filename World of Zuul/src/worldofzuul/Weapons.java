package worldofzuul;

import worldofzuul.utilities.Dice;

/**
 * Contains information about weapons
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
*/

public class Weapons extends Items {

    private int damage;
    private int critmodifier;
    private boolean usable;
    private Dice randChange = new Dice(0, 100);

    /**
     * Constructor for the weapons class. 
     * @param name the name of the item
     * @param damage how much damage it deals
     * @param critmod 
     * @param usable 
     */
    public Weapons(String name, int damage, int critmod, boolean usable) {
        this.damage = damage;
        this.name = name;
        this.usable = usable;
        this.critmodifier = critmod;
    }
    
    /**
     * A potentiel to give bonus damage, else it will return normal damage
     * @param dmg
     * @return 
     */
    private int critDmg(int dmg){
        int r = randChange.calculate();
        
        if( r >= 45 && r <= 55 ){           
            return ( dmg * critmodifier );
        }
        else {
            return dmg;
        }
    }
    
    public boolean isUsable() {
        return usable;
    }

    public int getDamage() {
        return critDmg( this.damage );
    }

}
