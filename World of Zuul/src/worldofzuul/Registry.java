/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.io.*;


/**
 *
 * @author Fract
 */
public class Registry 
{
    private String RegistryPath;
    
    public enum Type
    {
       Int,
       Float,
       String,
       Unknown
    }
    
    public Registry( String pathToRegistry )
    {
        RegistryPath = pathToRegistry;
    }
    
    public boolean AddKey( String Key )
    {
        
        return false;
    }
    
    public boolean AddKeys( String[] Keys )
    {
     
        return false;
    }
    
    public boolean RemoveKey( String Key )
    {
        
        return false;
    }
    
    public boolean RemoveKeys( String[] Keys )
    {
        
        return false;
    }
    
    private class Entries
    {
        
    }
    
    //
    private static class rFiles
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
       
        
    } // End rFiles
    
    private static class rDirectories
    {
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
       
    } // End rDirectories
    
} // End Class Registry
