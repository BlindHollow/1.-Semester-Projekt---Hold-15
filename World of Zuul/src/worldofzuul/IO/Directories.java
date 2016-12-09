/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.IO;

import java.io.File;

/**

 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
public class Directories 
{
        /**
         * 
         * @param Path
         * @param CreateParents
         * @return 
         */
        public static boolean create(String Path, boolean CreateParents)
        {
            File f = new File(Path);
            
            return create(f, CreateParents);
        }
    
        /**
         * 
         * @param Path
         * @param createParents
         * @return 
         */
        public static boolean create( File Path, boolean createParents )
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
       public static boolean remove( File Path )
       {
           try
           {
               if( exist( Path ) == true )
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
       public static boolean exist( File Path )
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
