/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author Fract
 */
public final class Content 
{
    
    
    // Root
    public final static String Directory_Config        = ".\\config";
    
        // Subdirectories
        public final static String Directory_Saved_Configs = Directory_Config + "\\saved";
    
            // User Highscores
            public final static String Directory_Highscore     = Directory_Saved_Configs + "\\highscore";
    
        // Props, Zombie names, etc
        public final static String Directory_Props         = Directory_Config + "\\props";
    
            public final static String Directory_Enemies   = Directory_Props + "\\Enemies\\";
            
                public final static String Directory_Enemies_Names   = Directory_Enemies + "\\names";
}
