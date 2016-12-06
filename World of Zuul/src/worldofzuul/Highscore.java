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
    private Registry register = new Registry( ".\\Highschool.rg" );
       
    /**
     * 
     */
    public Highscore()
    {
        SetCurrentPlayerName( "player" );
        
        String[] keyValues = { "username", 
                               "highscore" };
        
        register.AddKeys( keyValues );
        
    }
    
    /**
     * 
     * @param name 
     */
    public Highscore( String name )
    {
        this();
        
        SetCurrentPlayerName( name );
        
        LoadPlayers();
        
    }
    
    /**
     * Saves a character's, current score
     * @param CharacterName
     * @return True: Saved, False: Error occured
     */
    public boolean SaveCurrentCharacter( String CharacterName )
    {
        if( hText.ParseCharacters( CharacterName ) != true )
        {
            return false;
        }
        
        
        
        return true;
    }
    
    /**
     * Loads other Character's that are saved
     */
    public final void LoadPlayers()
    {

        
    }
    
    // Functions ------------------------------------------------------------------------------------ //
   
    private static class hText
    {   
            /**
            * 
            * @param InputName
            * @return 
            */
        public static boolean ParseCharacters( String InputName )
        {
            // 
            for( char c : InputName.toCharArray() )
            {
                boolean Continue = false;

                if( hText.AllowedCharacter( c ) == true )
                    Continue = true;

                if( Continue == false )
                    return false;
            }

            return true;
        } // End ParseCharacters
     
       /**
        * 
        * @param inputValue
        * @return 
        */
        public static boolean AllowedCharacter( char inputValue )
        {
            if ( inputValue <= 'A' || 
                 inputValue >= 'z' )
                return true;

            if( inputValue <= '0' || 
                inputValue >= '9' )
                return true;

            if( inputValue == '-' || 
                inputValue == '_')
                return true;

            return false;
        } // End AllowedCharacter
        
    } // End Parsing
    
}  // End Class Main
