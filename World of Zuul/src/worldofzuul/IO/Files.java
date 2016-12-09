/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.IO;

import java.io.File;

/**
 *
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
public class Files 
{   
    /**
     * 
     * @param filePath
     * @return 
     */
    public static boolean create( File filePath )
    {

        if( exist( filePath ) == false )
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
    
       /**
        * 
        * @param filePath
        * @return 
        */
       public static boolean remove( File filePath )
       {
           try
           {
                if( exist( filePath ) )
                    filePath.delete();
           }
           catch( Exception Ex )
           {

           }

          return false;
       }
       
       /**
        * 
        * @param filePath
        * @return 
        */
       public static boolean exist( File filePath )
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
       
}
