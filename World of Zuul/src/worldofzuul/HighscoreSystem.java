/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.ArrayList;

/**
 *
 * @author Fract
 */

public class HighscoreSystem 
{
    // Internal Variables
    ArrayList<HighscoreContainer> ListOfPlayers = new ArrayList();
    
    // Tilføjer en spiller til playerlisten, retunere en id (pos. i array)
    public int AddPlayer( String Playername )
    {
        HighscoreContainer container = new HighscoreContainer( Playername );
        
        
        return -1;
    }
    
}
