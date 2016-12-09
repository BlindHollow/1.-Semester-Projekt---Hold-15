/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.IO;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Bytoft, Mikkel
 * @author Christensen, Martin Steen
 * @author Hansen, Søren Vest
 * @author Johansen, Emil Højgaard
 * @author Madsen, Kent vejrup
 * @author Thy, Mads Heimdal
 */
public class List 
{
    /**
     * 
     * @param directory
     * @return 
     */
    public static File[] listFiles( File directory )
    {
        ArrayList<File> fileList = new ArrayList();
        
        try
        {
            File[] files = null;
               
            files = directory.listFiles( new worldofzuul.IO.FileFilters.Files() );
               
            return files;
        }
        catch( Exception ex )
        {
               
        }
               
        return null;
    }
}
