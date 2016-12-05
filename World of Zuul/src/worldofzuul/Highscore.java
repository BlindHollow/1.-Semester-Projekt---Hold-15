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
    private final static String dir_Highscore = Content.Directory_Config + "\\highscore";
    
    private final static String dir_Players = dir_Highscore + "\\players";
    
    /**
     * 
     * @param inputValue
     * @return 
     */
    private boolean AllowedCharacter( char inputValue )
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
    }
    
    /**
     * 
     * @param InputName
     * @return 
     */
    private boolean ParseNameCharacters( String InputName )
    {
        // 
        for( char c : InputName.toCharArray() )
        {
            boolean Continue = false;
            
            if( AllowedCharacter( c ) == true )
                Continue = true;
            
            if( Continue == false )
                return false;
        }
        
        return true;
    }
    
    /**
     * 
     * @param InputText
     * @return 
     */
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
        
        if( retValues.size() == 0 )
            return null;
        
        return ( String[] )retValues.toArray();
    }
    
    /**
     * 
     */
    public Highscore()
    {
        SetCurrentPlayerName( "player" );
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
     * Loads a character's, current score
     * @param CharacterName
     * @return True: Loaded, False: Error occured
     */
    public boolean LoadCurrentCharacter( String CharacterName )
    {
        if( ParseNameCharacters( CharacterName ) )
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
        if( ParseNameCharacters( CharacterName ) )
        {
            return false;
        }
        
        
        
        return true;
    }
    
        /**
     * Loads other Character's that are saved
     */
    public void LoadPlayers()
    {
        
        
    }
    
    /**
     * 
     */
    private final class hFiles
    {
                
        /**
         * 
         * @return 
         */
        public boolean Create( File f )
        {
            
            if( Exist( f ) == false )
            {
                try
                {
                    return f.createNewFile(); 
                }
                catch( Exception ex )
                {
                    
                }
                
            }
            else
            {
                
            }
            
            return false; 
        }
        
        /**
         * 
         * @return 
         */
        public boolean Remove( File f )
        {
            try
            {
                if( Exist( f ) )
                    f.delete();
            }
            catch( Exception Ex )
            {
                
            }
            
           return false; 
        }
        
        /**
         * 
         * @return 
         */
        public boolean Exist( File f )
        {
            try
            {
                if( f.isFile() )
                {
                    return f.exists();
                }
            }
            catch( Exception ex )
            {
                
            }
            
            return false;
        }
        
    } // End hFiles
    
    /**
     * 
     */
    private final class hDirectories
    {
        public boolean Create( File Path, boolean createParents )
        {
            try
            {
                if( createParents == true )
                {
                    Path.mkdirs();
                }
                else
                {
                    Path.mkdir();
                }
            }
            catch( Exception Ex )
            {
                
            }
            
            return false;
        }
        
        
        public boolean Remove( File Path )
        {
            try
            {
                if( Exist( Path ) == true )
                {
                    Path.delete();
                        
                    return true;
                }   
                else
                {
                    return false;
                }
                
            }
            catch( Exception ex )
            {
                
            }
            
            return false;
        }
        
        public boolean Exist( File Path )
        {
            try
            {
                if( Path.isDirectory() )
                {
                    return Path.exists();
                }  
            }
            catch( Exception Ex )
            {
                
            }
                    
            return false;   
        }
    
    } // End Class hDirectories
    
}  // End Class Main
