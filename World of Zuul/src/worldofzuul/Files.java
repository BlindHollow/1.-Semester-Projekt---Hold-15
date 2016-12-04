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
public final class Files 
{
    /**
     * 
     * @param Directory
     * @return 
     */
    public static String[] RetrieveFiles( String Directory )
    {
        File fObject = new File( Directory );
        
        
        ArrayList<String> FilePathsFound = new ArrayList();
        
        try
        {
            // if it isn't a directory, well no points of doing it
            if( fObject.isDirectory() != true )
                return null;
            
            // if it doesn't exist, we can't really list anything
            if( fObject.exists() != true )
                return null;
            
            // List files
            File[] foundFiles = fObject.listFiles();
            
            // Adds the directories path to the list
            for( File f : foundFiles )
            { 
                if( f.isFile() )
                    FilePathsFound.add( f.getPath() );
                else
                    continue;
            }
            
        }
        catch( Exception e )
        {
            // Do something
            
            return null;
        }
        
        // Returns String Array
        return ( String[] )FilePathsFound.toArray();
    } // End RetrieveFiles
    
    public static boolean Exist( String Path )
    {
        File find = new File( Path );
        
        return find.exists();
    }
    
    /**
     * 
     * @return 
     */
    public static String LoadFile( String path )
    {
        StringBuilder outputText = new StringBuilder();
        
        FileInputStream fis;
        InputStreamReader isr;
        
        try
        {
            fis = new FileInputStream( path );
            
            isr = new InputStreamReader( fis, 
                                         "UTF-8" );
            
            int data;
            
            while( ( data = isr.read() ) != -1 )
            {
                char currentVal = ( char ) data;
                outputText.append( currentVal );
                
                data = isr.read();
            }
            
            fis.close();
            isr.close();
        }
        catch( Exception E )
        {
            // IO Exception, gonna look at it, later
            
        }
        
        return outputText.toString();
    }
    
    /**
     * 
     */
    public static void SaveFile( String Path, 
                                 String Content )
    {
        try
        {
           PrintWriter out = new PrintWriter( Path );
           
           out.println( Content );
        }
        catch( Exception e )
        {
            System.out.println(e.getMessage());
        }
        
    }
    
}
