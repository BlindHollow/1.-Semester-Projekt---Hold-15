/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.*;
import java.io.*;
import java.nio.file.Files;

/**
 *
 * @author Fract
 */
public class Highscore extends HighscoreSystem 
{
    private boolean Debug = false;
    
    private final String DatabasePath = ".\\db";
    private final String HS_Database = DatabasePath + "\\Highscore";
    
    /**
     * 
     */
    public Highscore()
    {
        SetCurrentPlayerName( "player" );
        
        if( hsDirectories.Exist( new File( HS_Database ) ) == false )
        {
            hsDirectories.Create( HS_Database, 
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
        
        if( hsText.ParseCharacters( name ) == false )
        {
            return;
        }
        
        SetCurrentPlayerName( name );
        
        LoadPlayers();
        
        
    }
    
    /**
     * Saves a character's, current score
     * @param CharacterName
     * @return True: Saved, False: Error occured
     */
    public boolean SaveCurrentCharacter( )
    {
       StringBuilder builder = new StringBuilder(); 
        
       builder.append( GetCurrentPlayerName() );
       builder.append( ',' );
       builder.append( Integer.toString( GetCurrentPlayerPoints() ) );
              
       File file = new File( HS_Database + "\\" + GetCurrentPlayerName() );
       
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
    public final void LoadPlayers()
    {
        File f = new File( HS_Database );
        
        File[] listed = hsFiles.ListFiles( f );
        
        for( File a : listed )
        {
            try
            {
                List<String> s = Files.readAllLines( a.toPath() );
                
                for( String b : s )
                {
                    String[] result = b.split( "," );
                    
                    AddPlayers( result[0], Integer.parseInt( result[1] ) );  
                }
                
            }
            catch (Exception ex)
            {
                        
            }
            
        }
        
        
    }
    
    // Get
    public boolean GetDebug()
    {
        return this.Debug;
    }
    
    // Set
    public void SetDebug( boolean State )
    {
        this.Debug = State;
    }
    
    // Functions ------------------------------------------------------------------------------------ //
   
    private static class hsText
    {   
            /**
            * 
            * @param InputName
            * @return 
            */
        public static boolean ParseCharacters( String InputName )
        {
            //
            boolean Continue = false;
            
            for( char c : InputName.toCharArray() )
            {
                Continue = AllowedCharacter( c );

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
            
            if( (inputValue == '-') || 
                (inputValue == '_') )
            {
                return true;
            }
            
            return false;
        } // End AllowedCharacter
        
    } // End Parsing
    
    private static class hsDebug
    {
        public static void Output( String Text, boolean DebugState )
        {
            
            if( DebugState == true )
            {
                System.out.println(Text);
            }
            
        }
    }
    
    private static class hsDirectories
    {
        public static boolean Create(String Path, boolean CreateParents)
        {
            File f = new File(Path);
            
            return Create(f, CreateParents);
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
        
    }
    
    private static class hsFiles implements FileFilter 
    {
        
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
       
       @Override
       public boolean accept( File f )
       {
           return f.isFile();
       }
       
       public static File[] ListFiles( File directory )
       {
           ArrayList<File> fileList = new ArrayList();
        
           try
           {
               File[] files = null;
               
               files = directory.listFiles( new hsFiles() );
               
               return files;
           }
           catch( Exception ex )
           {
               
           }
               
           return null;
       }
       
    }
    
}  // End Class Main
