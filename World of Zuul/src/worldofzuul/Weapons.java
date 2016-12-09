package worldofzuul;

import Utilities.Dice;

public class Weapons extends Items {

    private int damage;
    private int critmodifier;
    private boolean usable;
    private Dice RandChange = new Dice(0, 100);

    public Weapons(String name, int damage, int critmod, boolean usable) {
        this.damage = damage;
        this.name = name;
        this.usable = usable;
        this.critmodifier = critmod;
    }
    
    private int CritDmg( int dmg )
    {
        int r = RandChange.Calculate();
        
        if( r <= 45 && r >= 55 )
        {
            return ( dmg * this.critmodifier );
        }
        else
        {
            return dmg;
        }
    }
    
    public boolean isUsable() {
        return usable;
    }

    public int getDamage() {
        return CritDmg( this.damage );
    }

}
