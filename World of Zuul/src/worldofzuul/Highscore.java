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
    private boolean ParseName( String InputName )
    {
        // 
        for( char c : InputName.toCharArray() )
        {
            boolean Continue = false;
            
            if( c <= 'A' || c >= 'z' )
                Continue = true;
            
            
            if( c <= '0' || c >= '9' )
                Continue = true;
            
            
            if( c == '-' ||c == '_' )
                Continue = true;
            
            if( Continue == false )
                return false;
        }
        
        return true;
    }
    
    /**
     * 
     */
    public Highscore()
    {
        SetCurrentPlayername("player");
        
        // Hvis directorien, eksistere lad være med at gøre noget, ellers lav den
        if( Directory.ExistDirectory( Content.Directory_Highscore ) != true )
            Directory.CreateDirectory( Content.Directory_Highscore, 
                                       true );
        
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
        if( ParseName( CharacterName ) )
        {
            return false;
        }
        
        String Path = Content.Directory_Highscore + "\\" + CharacterName;
        
        // If it exist Load, else error
        if( Files.Exist( Path ) == false )
            return false;
        
        String config = Files.LoadFile( Path );
        
        
        
        return false;
    }
    
    /**
     * Saves a character's, current score
     * @param CharacterName
     * @return True: Saved, False: Error occured
     */
    public boolean SaveCurrentCharacter( String CharacterName )
    {
        if( ParseName( CharacterName ) )
        {
            return false;
        }
      
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
