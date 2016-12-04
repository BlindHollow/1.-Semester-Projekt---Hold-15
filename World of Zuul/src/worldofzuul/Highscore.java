/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.*;
import java.io.*;

/**
 *
 * @author Fract
 */
public class Highscore extends HighscoreSystem 
{
    private boolean AllowedCharacter( char inputValue )
    {
     if ( inputValue <= 'A' || 
          inputValue >= 'z' )
         return true;
     
     if( inputValue <= '0' || inputValue >= '9')
         return true;
     
     if( inputValue == '-' || 
         inputValue == '_')
         return true;
     
        return false;
    }
    
    private boolean ParseName( String InputName )
    {
        // 
        for( char c : InputName.toCharArray() )
        {
            boolean Continue = false;
            
            if( AllowedCharacter( c ) == true )
                Continue = true;
            
            // In case - Add customs here
            
            if( Continue == false )
                return false;
        }
        
        return true;
    }
    
    private String[] Tokenize( String InputText )
    {
        ArrayList<String> retValues = new ArrayList();
        
        StringBuilder builder = new StringBuilder();
        
        for( int x = 0; 
                 x <= InputText.length() - 1; 
                 x ++ )
        {
            char current = InputText.charAt( x );
            
            // If space, newline or end of line, append to list
            if( current == ' '   ||
                current == '\n'  ||
                x == InputText.length() -1 )
                    retValues.add( builder.toString() );
            
            // Only append allowed characters
            if( AllowedCharacter( current ) )
                builder.append( current );       
        }
        
        return ( String[] )retValues.toArray();
    }
    
    /**
     * 
     */
    public Highscore()
    {
        SetCurrentPlayerName( "player" );
        
        
        
        Load();
    }
    
    /**
     * 
     * @param name 
     */
    public Highscore( String name )
    {
        this();
        SetCurrentPlayerName( name );
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
        
        
        return true;
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
        
        
        
        return true;
    }
    
}
