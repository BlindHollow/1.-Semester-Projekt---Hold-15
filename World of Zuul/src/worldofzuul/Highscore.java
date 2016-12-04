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
        
        // Hvis directorien, eksistere lad være med at gøre noget, ellers lav den
        if( Directory.ExistDirectory( Content.Directory_Highscore ) != true )
            Directory.CreateDirectory( Content.Directory_Highscore, 
                                       true );
        
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
        String[] filePath = Files.RetrieveFiles( Content.Directory_Highscore );
        
        for( String s : filePath )
        {
            File f = new File( s );
            
            if( f.isFile() )
            {
                String name = f.getName();
                
                String content = Files.LoadFile( s );
                
                AddPlayers( name, 
                            Integer.parseInt( content ) );   
            }
            
        }
        
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
        
        String[] tokens = Tokenize( config );
        
        // Retrieves the current score
        String CurrentScore = tokens[ tokens.length - 1 ];
        
        int ScoreValue = Integer.parseInt( CurrentScore );
        
        SetCurrentPlayerPoints( ScoreValue );
        
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
        
        if( Directory.ExistDirectory( Content.Directory_Highscore ) == false )
            Directory.CreateDirectory( Content.Directory_Highscore, 
                                       true );
        
        String Path = Content.Directory_Highscore + "\\" + CharacterName;
        
        Files.SaveFile( Path, 
                        Integer.toString( GetCurrentPlayerPoints() ) );
        
        
        
        return true;
    }
    
}
