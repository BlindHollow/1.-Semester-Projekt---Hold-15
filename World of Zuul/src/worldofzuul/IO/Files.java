/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.IO;

import java.io.File;

/**
 *
 * @author Fract
 */
public class Files 
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
}
