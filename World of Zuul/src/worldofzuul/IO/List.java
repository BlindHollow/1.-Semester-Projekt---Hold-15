/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.IO;

import java.io.File;
import java.util.ArrayList;
import worldofzuul.Highscore;

/**
 *
 * @author Fract
 */
public class List 
{
    public static File[] ListFiles( File directory )
       {
           ArrayList<File> fileList = new ArrayList();
        
           try
           {
               File[] files = null;
               
               files = directory.listFiles( new Highscore.hsFiles() );
               
               return files;
           }
           catch( Exception ex )
           {
               
           }
               
           return null;
       }
}
