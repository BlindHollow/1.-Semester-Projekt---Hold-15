/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.score;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import worldofzuul.misc.directories;

/**
 *
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
public class Highscore extends HighscoreSystem 
{
    private boolean debug = false;
    
    /**
     * 
     */
    public Highscore()
    {
        setCurrentPlayerName( "player" );
        
        if( worldofzuul.IO.Directories.exist( new File( directories.HighscoreTable ) ) == false )
        {
            worldofzuul.IO.Directories.create( directories.HighscoreTable, 
                                               true );
        }
        
    }
    
    /**
     * 
     * @param name 
     */
    public Highscore( String name )
    {
        this();
        
        if( hsText.parseCharacters( name ) == false )
        {
            return;
        }
        
        setCurrentPlayerName( name );
        
        loadPlayers();
        
        
    }
    
    /**
     * Saves a character's, current score
     * @param CharacterName
     * @return True: Saved, False: Error occured
     */
    public boolean saveCurrentCharacter( )
    {
       StringBuilder builder = new StringBuilder(); 
        
       builder.append( getCurrentPlayerName() );
       builder.append( ',' );
       builder.append( Integer.toString( getCurrentPlayerPoints() ) );
              
       File file = new File( directories.HighscoreTable + "\\" + getCurrentPlayerName() );
       
       try
       {
            PrintWriter pw = new PrintWriter( file, "UTF-8" );
            pw.println( builder.toString() );
            pw.close();
       }
       catch( Exception ex )
       {
            return false;   
       }
       
       return true;
    }
    
    /**
     * Loads other Character's that are saved
     */
    public final void loadPlayers()
    {
        File playerFilesDirectory = new File( directories.HighscoreTable );
        File[] listedPlayerFiles = worldofzuul.IO.List.listFiles( playerFilesDirectory );
        
        for( File user : listedPlayerFiles )
        {
            try
            {
                List<String> linesRead = Files.readAllLines( user.toPath() );
                
                for( String currentline : linesRead )
                {
                    String[] result = currentline.split( "," );
                    
                    addPlayers( result[0], 
                                Integer.parseInt( result[1] ) );  
                }
                
            }
            catch ( Exception ex )
            {
                        
            }
            
        }
        
        
    }
    
    // Get
    public boolean getDebug()
    {
        return this.debug;
    }
    
    // Set
    public void setDebug( boolean State )
    {
        this.debug = State;
    }
    
    // Functions ------------------------------------------------------------------------------------ //
   
    private static class hsText
    {   
            /**
            * 
            * @param InputName
            * @return 
            */
        public static boolean parseCharacters( String InputName )
        {
            //
            boolean Continue;
            
            for( char c : InputName.toCharArray() )
            {
                Continue = allowedCharacter( c );

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
        public static boolean allowedCharacter( char inputValue )
        {
            
            if ( inputValue >= 'A' && 
                 inputValue <= 'z' )
            {
                return true;
            }
            
            if( inputValue >= '0' && 
                inputValue <= '9' )
            {
                return true;
            }
            
            if( ( inputValue == '-' ) || 
                ( inputValue == '_' ) )
            {
                return true;
            }
            
            return false;
        } // End AllowedCharacter
        
    } // End Parsing
    
    private static class hsDebug
    {
        public static void output( String Text, boolean DebugState )
        {
            
            if( DebugState == true )
            {
                System.out.println(Text);
            }
            
        }
    } // End hsDebug    

}  // End Class Main
