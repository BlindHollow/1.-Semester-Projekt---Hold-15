/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.*;

/**
 *
 * @author Fract
 */

public class HighscoreSystem 
{
    HighscorePlayer CurrentPlayer = new HighscorePlayer();
    ArrayList<HighscorePlayer> ListOfPriorUserScores = new ArrayList();
    
    public HighscoreSystem( String CurrentPlayername )
    {
        CurrentPlayer.SetPlayerName( CurrentPlayername );
        CurrentPlayer.SetIsPlayer( true );
    }
    
    // Add Player's to compare againts
    public boolean AddPlayers( String Name )
    {
        return AddPlayers( Name, 0 );
    }
    
    public boolean AddPlayers( String Name, int Score )
    {
        HighscorePlayer player = new HighscorePlayer( Name, 
                                                      Score, 
                                                      false );
        
        if( ExactUser( Name ) == true )
        {
            return false;
        }
        else
        {
            ListOfPriorUserScores.add( player );
            return true;
        }
        
    }
    
    // Returns true if a player with the same name, show up. avoid duplicates
    private boolean ExactUser( String Name )
    {
        if( CurrentPlayer.GetPlayername() == CurrentPlayer.GetPlayername() )
            return true;
        
        for( HighscorePlayer current : ListOfPriorUserScores )
        {
            if( Name.toLowerCase() == current.GetPlayername().toLowerCase() )
            {
                return true;
            }
        }
        
        return false;
    }
    
    // Current Player
    public void AddPoints( int Number )
    {
        CurrentPlayer.SetPlayerScore( GetPoints() + Number );
        
    }
    
    public void RemovePoints( int Number )
    {
        CurrentPlayer.SetPlayerScore( GetPoints() - Number );
        
    }
    
    public int GetPoints()
    {
        return CurrentPlayer.GetPlayerScore();
    }
    
    // Retunere en sorteret list, med spilleren
    public ArrayList<HighscorePlayer> OrderedListOfPlayers()
    {
        // ReturnList
        ArrayList<HighscorePlayer> retList = new ArrayList();
        
        // Adds Everybody
        retList.addAll( ListOfPriorUserScores );
        retList.add( CurrentPlayer );
        
        // Sortere Listen
        Collections.sort( retList );
        
        return retList;
    }
    
    public void DebugInfo()
    {
        
    }
}
