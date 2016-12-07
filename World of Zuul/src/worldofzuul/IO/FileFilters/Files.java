/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.IO.FileFilters;

import java.io.*;

/**
 *
 * @author Fract
 */
public class Files implements FileFilter 
{
    public Files()
    {
        
    }
    
    @Override
    public boolean accept( File f )
    {
        return f.isFile();
    }
}
