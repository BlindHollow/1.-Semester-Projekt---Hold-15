package worldofzuul;

import worldofzuul.utilities.Dice;

/*
 
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
     * @param name
     * @param damage
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
    private int CritDmg( int dmg )
    {
        int r = randChange.calculate();
        
        if( r <= 45 && r >= 55 )
        {
            return ( dmg * this.critmodifier );
        }
        else
        {
            return dmg;
        }
    }
    
    /**
     * returns if it can be used
     * @return 
     */
    public boolean isUsable() {
        return usable;
    }

    /**
     * Gets the weapons damage
     * @return 
     */
    public int getDamage() {
        return CritDmg( this.damage );
    }

}
