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
public class Directories 
{
        /**
         * 
         * @param Path
         * @param CreateParents
         * @return 
         */
        public static boolean Create(String Path, boolean CreateParents)
        {
            File f = new File(Path);
            
            return Create(f, CreateParents);
        }
    
        /**
         * 
         * @param Path
         * @param createParents
         * @return 
         */
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
        
       /**
        * 
        * @param Path
        * @return 
        */
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
       
       /**
        * 
        * @param Path
        * @return 
        */
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
