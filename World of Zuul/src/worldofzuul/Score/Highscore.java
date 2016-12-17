/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.score;

import java.util.List;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import worldofzuul.misc.Directories;

/**

 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
/**
 * An extended version of the highscoreSystem, that retrieves a bunch of users.
 */
public class Highscore extends HighscoreSystem 
{
    /**
     * Constructor
     */
    public Highscore()
    {
        // Default playername
        setCurrentPlayerName( "player" );
        
        // Check if directory Exist
        if( worldofzuul.IO.Directories.exist(new File( Directories.HighscoreTable ) ) == false )
        {
            // Create it
            worldofzuul.IO.Directories.create( Directories.HighscoreTable, 
                                               true );
        }
        
    }
    
    /**
     * Constructor
     * @param name 
     */
    public Highscore( String name )
    {
        this();
        
        if( hsText.parseCharacters( name ) == false )
            return;
        
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
              
       File file = new File( Directories.HighscoreTable + "\\" + getCurrentPlayerName() );
       
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
        // Directory for the highscore files
        File playerFilesDirectory = new File( Directories.HighscoreTable );
        
        // List of files
        File[] listedPlayerFiles = worldofzuul.IO.List.listFiles( playerFilesDirectory );
        
        // Reads each files, and adds them to the highscore list
        for( File user : listedPlayerFiles )
        {
            
            try
            {
                /* 
                   Note: Returns the file, line by line
                // If the file is large enough, it can cause OutOfMemoryError
                // And cause the program to crash, however. due to the fact that
                // we're only reading names and stuff, it wont be a huge problem. 
                */
                List<String> linesRead = Files.readAllLines( user.toPath() );
                
                for( String currentline : linesRead )
                {
                    String[] result = currentline.split( "," );
                    
                    if( this.getCurrentPlayerName().equals( result[0].toLowerCase() ) == true )
                    {
                        System.out.println( "Result : " + user.getName() + ", " + result[0] );
                        this.setCurrentPlayerPoints( Integer.parseInt( result[1]) );
                    }
                    
                    addPlayers( result[0], 
                                Integer.parseInt( result[1] ) );  
                }
                
            }
            catch( java.lang.SecurityException SEx )
            {
                // Indicates a security violation.
                    // Triggered...
                continue;
            }
            catch( java.io.IOException IOEx )
            {
                continue;
            }
            catch( java.lang.IllegalArgumentException IAEx )
            {
                // 
                return;
            }
            catch( java.lang.OutOfMemoryError OOMEx )
            {
                // Couldn't allocate the required memory . . .
                return;
            }
            catch ( Exception Ex )
            {
                // out of our controll, exit gracefully. . .
                return;      
            }
            
        }
        
        
    }
    
    // Functions -------------------------------------------------------------->
   
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
            if( isAlphabetic( inputValue ) )
                return true;
            
            if( isNumber( inputValue ) )
                return true;
            
            if( isSpace( inputValue ) )
                return true;
            
            return false;
        } // End AllowedCharacter
        
        public static boolean isAlphabetic( char Input )
        {
            if ( Input >= 'A' && 
                 Input <= 'z' )
            {
                return true;
            }
            
            return false;
        }
        
        public static boolean isNumber( char Input )
        {
            if( Input >= '0' && 
                Input <= '9' )
            {
                return true;
            }
            
            return false;
        }
        
        public static boolean isSpace( char Input )
        {
            // Usually - & _ can indicate it's space
            if( Input >= '-' || 
                Input == '_' )
                return true;
            
            return false;
        }
        
    } // End Parsing  

}  // End Class Main
