/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.*;

/**
 *
 * @author Fract
 */
public class Highscore extends HighscoreSystem 
{
    /**
     * 
     */
    public Highscore()
    {
        if( Directory.ExistDirectory("") != true )
        {
            
        }
        
        if(Directory.ExistDirectory("") != true)
        {
            
        }
        
        if(Directory.ExistDirectory("") != true)
        {
        }
    }
    
    /**
     * 
     * @param name 
     */
    public Highscore( String name )
    {
        this();
        SetCurrentPlayername( name );
    }
    
    /**
     * Loads other Character's that are saved
     */
    public void Load()
    {
        
    }
    
    /**
     * Loads a character's, current score
     * @param CharacterName
     * @return True: Loaded, False: Error occured
     */
    public boolean LoadCurrentCharacter( String CharacterName )
    {
        
        
        return false;
    }
    
    /**
     * Saves a character's, current score
     * @param CharacterName
     * @return True: Saved, False: Error occured
     */
    public boolean SaveCurrentCharacter( String CharacterName )
    {
      
        return false;
    }
    
    /**
     * 
     * @return Scoretable Sorted
     */
    public ArrayList<HighscorePlayer> GetScoreTable()
    {
        return this.OrderedListOfPlayers();
    }
}
