package worldofzuul;

/**
 * Contains information about the zombies in the game
 * (Can possibly inherit from player?)
 
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */

import java.io.File;
import worldofzuul.utilities.Dice;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Zombie {
    //private String defaultName = "zombie";
    
    private String name;
    private int zomHP;
    private int zomDamage;
    private UUID id;
    private boolean isDead;
    //private String DefualtZombieNamePath = ".\\db\\Zombies";
    //private ArrayList<String> zombieNames = new ArrayList();
    private String[] zombieNames =  {"jon", "ned", "ramsay", "arya", "peter", "thormund", "jorah", "sansa", "brann", "rickon"};
    //private Dice randomName = new Dice(0, 0);

    /**
     * Constructor for Zombie
     * @param zomHP
     * @param zomDamage 
     */
    public Zombie(int zomHP, int zomDamage) {
        this.name = (zombieNames[new Random().nextInt(zombieNames.length)]);
        this.zomHP = zomHP;
        this.zomDamage = zomDamage;
        this.isDead = false;
        this.id = UUID.randomUUID();
        System.out.println(id.toString());
        
       /* File path = new File( DefualtZombieNamePath );
        
        if( worldofzuul.IO.Directories.exist( path ) == false )
            worldofzuul.IO.Directories.create( path, true );*/
    }
    
    public UUID getId(){
        return id;
    }

    /**
     * Hitting the zombie, with a specific amount of damage
     * @param damage 
     */
    public void hit(int damage) {
        updateHealth(-1*damage);
        System.out.println("You attacked " + name + " for " + damage + " damage.");
    }
    
    /**
     * 
     * @param player 
     */
    public void attackPlayer(Player player) {
        player.updateHealth(-1*zomDamage);
        player.updateIllness(7);
        System.out.println(name + " attacked you for " + zomDamage + " damage.");
    }

    /**
     * 
     */
    public void kill() {
        String actionString = "You killed the " + this.name;
        System.out.println(actionString);
    }
    
    /**
     * 
     * @param modifier 
     */
    public void updateHealth(int modifier) {
        zomHP = zomHP + modifier;
        if (zomHP <= 0) {
            isDead = true;
        }
    }
    
    /**
     * 
     * @return 
     */
    public boolean schroedinger(){
        return isDead;
    }

    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /*public String getRandomName()
    {
        File f = new File( ".\\db\\Zombies" );
        
        File[] a = worldofzuul.IO.List.listFiles(f);
        
        if( a == null || a.length == 0 )
            return "zombie";
        
        if( randomName.getEnd() != ( a.length - 1 ) )
        {
             randomName.edit( 0, ( a.length - 1 ) );
        }
        
        int RanVal = randomName.calculate();
        
        String s = nameFromFile( a[ RanVal ] );
        
        this.name = s;
        
        return s;
    }
    
    private String nameFromFile(File f)
    {
        String value = null;
        
        try 
        {
            List<String> LinesRead = Files.readAllLines( f.toPath() );
            
            for( String s : LinesRead )
                if( s.isEmpty() == false )
                    value = s;
            
            return value;
        }
        catch( Exception ex )
        {
            
        }
        
        return value;
    }*/
    
}