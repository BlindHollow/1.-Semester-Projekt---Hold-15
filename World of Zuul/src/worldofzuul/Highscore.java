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
    private Registry register;
    
    private final static String dir_Players = ".\\players";
    private final static String dir_Highscore = dir_Players + "\\highscore";
    
    /**
     * 
     */
    public Highscore()
    {
        SetCurrentPlayerName( "player" );
        
        if( hDirectories.Exist( dir_Highscore ) == false )
        {
            hDirectories.Create( dir_Highscore, 
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
        File FilepathToPlayers = new File( dir_Highscore );
        
        System.out.println( "Highscore Directory:" + FilepathToPlayers.getAbsolutePath() );
        
        ArrayList<File> PlayerInformation = hFiles.List.FilesInDirectory( FilepathToPlayers );
        
        // If null or empty, exit
        if( PlayerInformation == null )
            return;
        
        if( PlayerInformation.isEmpty() )
        {
            return;
        }

        
    }
    
    // Functions ------------------------------------------------------------------------------------ //
    
    /**
     * 
     */
    private static final class hFiles
    {
        // Wrapper function
        public static boolean Create( String Path )
        {
            File f = new File( Path );
            
            return Create( f );
        }
                
        /**
         * 
         * @return 
         */
        public static boolean Create( File filePath )
        {
            
            if( Exist( filePath ) == false )
            {
                try
                {
                    return filePath.createNewFile(); 
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
        
        // Wrapper Function
        public static boolean Remove( String path )
        {
            File f = new File( path );
            
            return Remove( f );
        }
        
        /**
         * 
         * @return 
         */
        public static boolean Remove( File filePath )
        {
            try
            {
                if( Exist( filePath ) )
                    filePath.delete();
            }
            catch( Exception Ex )
            {
                
            }
            
           return false; 
        }
        
        // Wrapper Function
        public static boolean Exist( String path )
        {
            File f = new File( path );
            
            return Exist( f );
        }
        
        /**
         * 
         * @return 
         */
        public static boolean Exist( File filePath )
        {
            try
            {
                if( filePath.isFile() )
                {
                    return filePath.exists();
                }
            }
            catch( Exception ex )
            {
                
            }
            
            return false;
        }
        
        public static final class List
        {
            public static ArrayList<File> FilesInDirectory( File filePath )
            {
                ArrayList<File> ListOfFoundFiles = new ArrayList();
                
                File[] ListOfStuffFound = filePath.listFiles();
                
                if( ListOfStuffFound.length == 0 )
                    return null;
                
                for( File f : ListOfStuffFound )
                {
                    
                    if( f.isFile() )
                    {
                        ListOfFoundFiles.add( f );
                    }
                    
                }
                
                return ListOfFoundFiles;
            }
        }
      
        
    } // End hFiles
    
    /**
     * 
     */
    private static final class hDirectories
    {
        // Wrapper Function
        public static boolean Create( String path, boolean createParents )
        {
            File f = new File( path );
            
            return Create( f, 
                           createParents );
        }
        
        public static boolean Create( File Path, boolean createParents )
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
        
        // Wrapper function
        public static boolean Remove( String path )
        {
            File f = new File( path );
            
            return Remove( f );
        }
        
        public static boolean Remove( File Path )
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
        
        // Wrapper function
        public static boolean Exist( String path )
        {
            File f = new File( path );
            
            return Exist( f );
        }
        
        public static boolean Exist( File Path )
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
